package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day8 {
    static List<String> input;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day8/input.txt");
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
        int counter = 0;
        int sum = 0;
        for(String in:input){
            String[] s = in.split("\\|");
            String encodedStrings[] = s[0].split(" ");
            String outputs[] = s[1].trim().split(" ");
            
            Map<String, Integer> map = new HashMap<>();
            Map<Integer, String> reverseMap = new HashMap<>();

            // Map easier values first
            for(String encoded: encodedStrings){
                int val = -1;
                encoded = sortString(encoded);
                if(encoded.length() == 2){
                    val = 1;
                }
                else if(encoded.length() == 3){
                    val = 7;
                }
                else if(encoded.length() == 4){
                    val = 4;
                }
                else if(encoded.length() == 7){
                    val = 8;
                }

                if(val != -1){
                    map.put(encoded, val);
                    reverseMap.put(val,  encoded);
                }
            }

            // Map 0, 6, 9 because they can be derived from the easier ones
            for(String encoded: encodedStrings){
                int val = -1;
                encoded = sortString(encoded);

                if(encoded.length() == 6){
                    // 0, 6, 9
                    String four = reverseMap.get(4);
                    boolean is9 = checkDigitInOtherDigit(four, encoded);

                    if(is9){
                        val = 9;
                    }
                    else {
                        // 0, 6
                        String one = reverseMap.get(1);
                        boolean is0 = checkDigitInOtherDigit(one, encoded);
                        
                        if(is0){
                            val = 0;
                        }
                        else {
                            // 6
                            val = 6;
                        }
                    }
                }

                if(val != -1){
                    map.put(encoded, val);
                    reverseMap.put(val, encoded);
                }
            }


            // Map remaining values
            for(String encoded: encodedStrings){
                int val = -1;
                encoded = sortString(encoded);

                if(encoded.length() == 5){
                    // 2, 3, 5
                    String one = reverseMap.get(1);
                    boolean is3 = checkDigitInOtherDigit(one, encoded);

                    if(is3){
                        val = 3;
                    }
                    else{
                        // 2, 5
                        String six = reverseMap.get(6);
                        boolean is5 = checkDigitInOtherDigit(encoded, six);

                        if(is5){
                            val = 5;
                        }
                        else{
                            // 2
                            val = 2;
                        }
                    }
                }
        
                if(val != -1){
                    map.put(encoded, val);
                    reverseMap.put(val, encoded); // Not needed, just putting for consistency
                }
            }
            
            for(String outputString: outputs){
                if(is1_4_7_8(outputString)){
                    counter++;
                }
            }
            // System.out.println("Map:");
            // displayMap(map);

            String out = getDecodedVal(map, outputs);
            sum += Integer.parseInt(out);
        }

        System.out.println("Part 1 count: "+counter);
        System.out.println("Part 2 sum: "+sum);

    }
    public static boolean is1_4_7_8(String s){
        return s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7;
    }
    public static String getDecodedVal(Map<String, Integer> map, String[] outputs){
        StringBuilder sb = new StringBuilder();
        for(String output: outputs){
            output = sortString(output);
            sb.append(map.get(output));
        }
        return sb.toString();
    }

    public static <K,V> void displayMap(Map<K,V> map){
        for(K key:map.keySet()){
            System.out.println("k: "+key+" v: "+map.get(key));
        }
    }

    public static String sortString(String input){
        char[] in = input.toCharArray();
        Arrays.sort(in);
        return new String(in);
    }

    // Used to check if the the digit pattern of first is present in the other (eg: pattern of 1 is present in 7)
    public static boolean checkDigitInOtherDigit(String digit, String otherDigit){
        boolean flag = true;
        for(char c:digit.toCharArray()){
            if(otherDigit.indexOf(c) < 0){
                flag = false;
                break;
            }
        }
        return flag;
    }
}