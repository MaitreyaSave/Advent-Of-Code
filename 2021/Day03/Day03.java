package Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day03 {
    static List<String> input;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day03/input.txt");
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
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        int length = input.get(0).length();
        for (int i=0; i<length; i++){
            int one_count = 0;
            int zero_count = 0;
            for (String in:input){
                if (in.charAt(i) == '0'){
                    zero_count++;
                }
                else{
                    one_count++;
                }
            }
            if (one_count > zero_count){
                gamma.append("1");
                epsilon.append("0");
            }
            else{
                gamma.append("0");
                epsilon.append("1");
            }
        }
        int gamma_int = Integer.parseInt(gamma.toString(),2);
        int epsilon_int = Integer.parseInt(epsilon.toString(),2);
        System.out.println("Part 1 Product: " + gamma_int * epsilon_int);
    }
    public static void part2(){
        List<String> oxygenGeneratorList = new ArrayList<>(input);
        List<String> co2ScrubberList = new ArrayList<>(input);

        int bitPos = 0;
        while (oxygenGeneratorList.size() > 1){
            
            int zero_count = 0;
            int one_count = 0;
            char mostCommon = '0';
            List<String> removableStrings = new ArrayList<>();
            for (String s:oxygenGeneratorList){
                if (s.charAt(bitPos) == '0'){
                    zero_count++;
                }
                else{
                    one_count++;
                }
            }
            if (one_count >= zero_count){
                mostCommon = '1';
            }
            else{
                mostCommon = '0';
            }
            
            for(String s:oxygenGeneratorList){
                if (s.charAt(bitPos) != mostCommon){
                    removableStrings.add(s);
                }
            }
            for (String r:removableStrings){
                oxygenGeneratorList.remove(r);
            }
            
            bitPos++;
        }
        bitPos = 0;
        while (co2ScrubberList.size() > 1){
            int zero_count = 0;
            int one_count = 0;
            char leastCommon = '0';
            List<String> removableStrings = new ArrayList<>();
            for (String s:co2ScrubberList){
                if (s.charAt(bitPos) == '0'){
                    zero_count++;
                }
                else{
                    one_count++;
                }
            }
            if (one_count >= zero_count){
                leastCommon = '0';
            }
            else{
                leastCommon = '1';
            }
            
            for(String s:co2ScrubberList){
                if (s.charAt(bitPos) != leastCommon){
                    removableStrings.add(s);
                }
            }
            for (String r:removableStrings){
                co2ScrubberList.remove(r);
            }
            bitPos++;
        }
        
        int oxygenGenerator_int = Integer.parseInt(oxygenGeneratorList.get(0),2);
        int co2Scrubber_int = Integer.parseInt(co2ScrubberList.get(0),2);
        System.out.println("Part 2 Product: " + oxygenGenerator_int * co2Scrubber_int);
    }
}