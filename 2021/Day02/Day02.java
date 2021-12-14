package Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day02 {
    static List<String> input;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day2/input.txt");
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
        int x = 0;
        int y = 0;

        for(String s:input){
            String[] vals = s.split(" ");
            String dir = vals[0];
            int val = Integer.parseInt(vals[1]);

            switch(dir){
                case "forward":
                    x += val;
                    break;
                case "down":
                    y += val;
                    break;
                case "up":
                    y -= val;
                    break;
            }
        }

        System.out.println("Part 1 product: "+x*y);
    }

    public static void part2(){
        int x = 0;
        int y = 0;
        int aim = 0;

        for(String s:input){
            String[] vals = s.split(" ");
            String dir = vals[0];
            int val = Integer.parseInt(vals[1]);

            switch(dir){
                case "forward":
                    x += val;
                    y += aim*val;
                    break;
                case "down":
                    aim += val;
                    break;
                case "up":
                    aim  -= val;
                    break;
            }
        }


        System.out.println("Part 2 product: "+x*y);
    }
}
