package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    static List<String> input;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day4/input.txt");
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
        // Convert draws input to int[]
        String[] drawStrings = input.get(0).split(",");
        int[] draws = new int[drawStrings.length];
        for (int i=0; i<draws.length; i++){
            draws[i] = Integer.parseInt(drawStrings[i]);
        }


        int rowCount = 0;
        List<int[][]> matrixList = new ArrayList<>();
        int[][] matrix = new int[5][5];
        for (int i=2; i<input.size(); i++){
            String s = input.get(i);

            if (rowCount == 0){
                matrix = new int[5][5];
            }

            if (!s.equals("")){
                String[] vals = s.split(" ");
                int valPos = 0;
                for (int j=0; j<5; j++, valPos++){
                    while(vals[valPos].equals("")){
                        valPos++;
                    }
                    matrix[rowCount][j] = Integer.parseInt(vals[valPos]);
                }
            }

            rowCount++;
            if (rowCount == 5){
                rowCount = 0;
                matrixList.add(matrix);
                i++;
            }
        }

        int lastDraw = -1;
        int lastSum = 0;
        int pos = 0;
        while (pos < draws.length){
            lastDraw = draws[pos];

            for (int i=0; i< matrixList.size(); i++){
                int[][] m = matrixList.get(i);
                m = markNumberInMatrix(m, lastDraw);
                matrixList.set(i, m);

                // System.out.println("last: " + lastDraw);
                // displayMatrix(m);

                if (isWinner(m)){
                    displayMatrix(m);
                    pos = draws.length;
                    System.out.println("Winner!!");
                    lastSum = getUnMarkedSum(m);
                    break;
                }
            }
            pos++;
        }
        
        System.out.println("Part 1 product: " + lastDraw * lastSum);

    }
    public static void part2(){
        // Convert draws input to int[]
        String[] drawStrings = input.get(0).split(",");
        int[] draws = new int[drawStrings.length];
        for (int i=0; i<draws.length; i++){
            draws[i] = Integer.parseInt(drawStrings[i]);
        }


        int rowCount = 0;
        List<int[][]> matrixList = new ArrayList<>();
        int[][] matrix = new int[5][5];
        for (int i=2; i<input.size(); i++){
            String s = input.get(i);

            if (rowCount == 0){
                matrix = new int[5][5];
            }

            if (!s.equals("")){
                String[] vals = s.split(" ");
                int valPos = 0;
                for (int j=0; j<5; j++, valPos++){
                    while(vals[valPos].equals("")){
                        valPos++;
                    }
                    matrix[rowCount][j] = Integer.parseInt(vals[valPos]);
                }
            }

            rowCount++;
            if (rowCount == 5){
                rowCount = 0;
                matrixList.add(matrix);
                i++;
            }
        }

        int lastDraw = -1;
        int lastSum = 0;
        int pos = 0;
        while (pos < draws.length){
            lastDraw = draws[pos];

            for (int i=0; i< matrixList.size(); i++){
                int[][] m = matrixList.get(i);
                m = markNumberInMatrix(m, lastDraw);
                matrixList.set(i, m);

                // System.out.println("last: " + lastDraw);
                // displayMatrix(m);

                if (isWinner(m)){
                    if (isLastWinner(matrixList)){
                        pos = draws.length;
                        System.out.println("Last Winner!!");
                        lastSum = getUnMarkedSum(m);
                        break;
                    }
                    
                }
            }
            pos++;
        }

        System.out.println("Part 2 product: " + lastDraw * lastSum);

    }

    public static int[][] markNumberInMatrix(int[][] m, int num){
        for (int i=0; i<m.length; i++){
            for (int j=0; j<m[i].length; j++){
                if (m[i][j] == num){
                    m[i][j] = -1;
                }
            }
        }
        return m;
    }
    public static int getUnMarkedSum(int[][] m){
        int sum = 0;

        for (int[] r:m){
            for (int v:r){
                if (v!=-1){
                    sum+=v;
                }
            }
        }

        return sum;
    }
    public static boolean isWinner(int[][] m){
        boolean result = false;

        
        for (int i=0; i<5; i++){
            int rowCheck = 0;
            int colCheck = 0;
            for (int j=0; j<5; j++){
                rowCheck += m[i][j];
                colCheck += m[j][i];
            }
            if (rowCheck == -5 || colCheck == -5){
                result = true;
                break;
            }
        }

        return result;
    }
    public static void displayMatrix(int[][] m){
        for(int i=0; i<m.length; i++){
            for(int j=0; j<m[i].length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static boolean isLastWinner(List<int[][]> mList){
        boolean result = true;
        for (int[][] m: mList){
            if (!isWinner(m)){
                result = false;
                break;
            }
        }
        return result;
    }
}