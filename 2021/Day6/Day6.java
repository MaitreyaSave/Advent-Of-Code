package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    static List<String> input;
    static int total;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day6/input.txt");
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
    }
    public static void part1(){
        // Update this value for part 1 and part 2 respectively
        int days = 256;

        String[] vals = input.get(0).split(",");
        long[] counters = new long[9];

        for (String val:vals){
            counters[Integer.parseInt(val)]++;
        }
        while (days > 0){
            counters = updateCounters(counters);
            days--;
        }
    
        System.out.println("Part 1 + 2 size: "+getArraySum(counters));

    }
    public static long[] updateCounters(long[] a){
        long temp = a[0];
        for (int i=0; i<a.length-1; i++){
            a[i] = a[i+1];
        }
        a[a.length -1] = temp;
        a[6] += temp;
        return a;
    } 
    public static long getArraySum(long[] a){
        long sum = 0;

        for (long i:a){
            sum += i;
        }
        return sum;
    }
}