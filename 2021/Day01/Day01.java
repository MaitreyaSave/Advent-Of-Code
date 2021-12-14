package Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day01{
    static List<Integer> input;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day01/input.txt");
        input = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                input.add(Integer.parseInt(sc.nextLine()));
                
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
        int previous = 0;
        int current = 0;
        int totInc = 0;
        for(int i:input){
            if(previous == 0){
                previous = i;
            }
            else{
                current = i;
                if(current > previous){
                    totInc++;
                }
                previous = current;
            }
        }
        System.out.println("Part 1 Total increase:"+totInc);
    }
    public static void part2(){
        int windowSize = 3;
        int prevSum = 0;
        int currSum = 0;
        int totInc = 0;
        for(int i=0; i<= input.size()-windowSize; i++){
            if (prevSum == 0){
                prevSum = input.get(i) + input.get(i+1) + input.get(i+2);
            }
            else{
                currSum = input.get(i) + input.get(i+1) + input.get(i+2);
                if(currSum > prevSum){
                    totInc++;
                }
                prevSum = currSum;
            }
        }
        System.out.println("Part 2 Total increase:"+totInc);
    }
}