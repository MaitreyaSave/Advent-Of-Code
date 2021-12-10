package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Day10 {
    static List<String> input;
    static Map<Character, Integer> incorrectCloseMap;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day10/input.txt");
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

        incorrectCloseMap = new HashMap<>();
        incorrectCloseMap.put(')', 0);
        incorrectCloseMap.put(']', 0);
        incorrectCloseMap.put('}', 0);
        incorrectCloseMap.put('>', 0);

        List<Long> incompleteScores = new ArrayList<>();

        int line = 1;
        for(String in:input){
            line++;
            char[] vals = in.toCharArray();
            Stack<Character> stk = new Stack<>();
            boolean isIncomplete = true;
            for(char c:vals){
                boolean isIncorrectClosing = false;
                switch(c){
                    case '(':
                        stk.add(')');
                        break;
                    case ')':
                        isIncorrectClosing = updateClosingCharacter(stk,')');
                        break;
                    case '[':
                        stk.add(']');  
                        break;
                    case ']':
                        isIncorrectClosing = updateClosingCharacter(stk,']');
                        break;
                    case '{':
                        stk.add('}');
                        break;
                    case '}':
                        isIncorrectClosing = updateClosingCharacter(stk,'}');
                        break;
                    case '<':
                        stk.add('>');
                        break;
                    case '>':
                        isIncorrectClosing = updateClosingCharacter(stk,'>');                     
                        break;
                    default:
                        // do nothing
                }

                if(isIncorrectClosing){
                    isIncomplete = false;
                    break;
                }
            }
            if (isIncomplete){
                long score = 0;

                while(!stk.isEmpty()){
                    char c = stk.pop();
                    int val = 0;

                    switch(c){
                        case ')':
                            val = 1;
                            break;
                        case ']':
                            val = 2;
                            break;
                        case '}':
                            val = 3;
                            break;
                        case '>':
                            val = 4;
                            break;
                        default:
                            // do nothing
                    }

                    score = 5 * score + val;

                }
                // System.out.println("score: "+score);
                incompleteScores.add(score);
            }
        }

        int b1 = incorrectCloseMap.get(')');
        int b2 = incorrectCloseMap.get(']');
        int b3 = incorrectCloseMap.get('}');
        int b4 = incorrectCloseMap.get('>');

        int product = 3*b1 + 57*b2 + 1197*b3 + 25137*b4;

        System.out.println("Part 1 product: "+product);

        Collections.sort(incompleteScores);
        int len = incompleteScores.size();

        System.out.println("Part 2 middle: "+incompleteScores.get(len/2));

    }

    public static boolean updateClosingCharacter(Stack<Character> stk, Character c){
        boolean isIncorrectCharacter = false;

        if(stk.peek() == c){
            stk.pop();
        }
        else {
            isIncorrectCharacter = true;
            incorrectCloseMap.put(c, incorrectCloseMap.get(c) + 1);
        }

        return isIncorrectCharacter;
    }
}