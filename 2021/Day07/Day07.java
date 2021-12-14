package Day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day07 {
    static List<String> input;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day07/input.txt");
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
        part1_2();
    }
    public static void part1_2(){
        String [] vals = input.get(0).split(",");
        int[] in = new int[vals.length];
        int max = 0;
        for (int i=0; i<in.length; i++){
            in[i] = Integer.parseInt(vals[i]);
            if (in[i] > max){
                max = in[i];
            }
        }

        int min = Integer.MAX_VALUE;
        int minCum = Integer.MAX_VALUE;
        for (int i=0; i<max; i++){
            int sum = 0;
            int cumSum = 0;
            for (int j=0; j<in.length; j++){
                sum += Math.abs(i - in[j]);
                cumSum += cumSum(Math.abs(i - in[j]));
            }
            if (sum < min){
                min = sum;
            }
            if (cumSum < minCum){
                minCum = cumSum;
            }
        }

        System.out.println("Part 1 min: "+min);
        System.out.println("Part 2 min: "+minCum);

    }
    public static int cumSum(int i){
        return i*(i+1)/2;
    }
}