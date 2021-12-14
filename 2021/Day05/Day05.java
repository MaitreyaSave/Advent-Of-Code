package Day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day05 {
    static List<String> input;
    static int maxSize = 1000;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day05/input.txt");
        input = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                input.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // Program
        part1();
        part2();
    }
    public static void part1(){

        int x1,x2,y1,y2;
        int[][] m = new int[maxSize][maxSize];


        for (String s: input){
            String[] vals = s.split(" ");

            x1 = Integer.parseInt(vals[0].split(",")[0]);
            y1 = Integer.parseInt(vals[0].split(",")[1]);
            x2 = Integer.parseInt(vals[2].split(",")[0]);
            y2 = Integer.parseInt(vals[2].split(",")[1]);

            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);

            // Horizontal + Vertical check
            if (x1 == x2 || y1 == y2){
                for (int i=minX; i<=maxX; i++){
                    for (int j=minY; j<= maxY; j++){
                        m[j][i]++;
                    }
                }
            }
        }

        int result = getCountMoreThan(m, 1);
        System.out.println("Part 1 sum: "+result);

    }
    public static void part2(){
        int x1,x2,y1,y2;
        int[][] m = new int[maxSize][maxSize];


        for (String s: input){
            String[] vals = s.split(" ");

            x1 = Integer.parseInt(vals[0].split(",")[0]);
            y1 = Integer.parseInt(vals[0].split(",")[1]);
            x2 = Integer.parseInt(vals[2].split(",")[0]);
            y2 = Integer.parseInt(vals[2].split(",")[1]);

            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);

            boolean horizontalVerticalCheck = (x1 == x2 || y1 == y2);
            if (horizontalVerticalCheck){
                for (int i=minX; i<=maxX; i++){
                    for (int j=minY; j<= maxY; j++){
                        m[j][i]++;
                    }
                }
            }

            // Diagonal check
            else if ((y2 -y1) / (x2 -x1) == 1){
                int j = minY;
                for (int i=minX; i<= maxX; i++){
                    m[j][i]++;
                    j++;
                }
            }

            // Reverse diagonal check
            else if ((y2-y1) / (x2-x1) == -1){
                int j = maxY;
                for (int i=minX; i<=maxX; i++){
                    m[j][i]++;
                    j--;
                }
            }
        }

        int result = getCountMoreThan(m, 1);
        System.out.println("Part 2 sum: "+result);
    }

    public static int getCountMoreThan(int[][] mat, int moreThanVal){
        int count = 0;

        for (int[] i:mat){
            for (int val: i){
                if (val > moreThanVal){
                    count++;
                }
            }
        }

        return count;
    }
}