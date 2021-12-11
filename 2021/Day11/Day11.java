package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11 {
    static List<String> input;
    static int[][] mat;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day11/input.txt");
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
        int m = input.size();
        int n = input.get(0).length();
        mat = new int[m][n];
        for(int i=0; i<m; i++){
            char[] c = input.get(i).toCharArray();
            for(int j=0; j<n; j++){
                mat[i][j] = Character.getNumericValue(c[j]);
            }
        }

        int flashCount = 0;
        for(int i=0; i<1000; i++){
            boolean[][] visited = new boolean[mat.length][mat[0].length];
            updateMatrix(visited);
            if(i==100){
                System.out.println("Part 1 count: "+flashCount);
            }
            // case for all flashing
            if(getVisitedCount(visited) == 100){
                System.out.println("Part 2 step: "+(i+1));
                break;
            }
            flashCount += getVisitedCount(visited);
        }
    }

    public static void updateMatrix(boolean[][] visited){
        for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat[0].length; j++){
                updateCell(i, j, visited);
            }
        }
    }

    public static void updateNeighborsOnFlash(int i, int j, boolean[][] visited){
        updateCell(i-1, j-1, visited);
        updateCell(i-1, j, visited);
        updateCell(i-1, j+1, visited);
        updateCell(i, j-1, visited);
        updateCell(i, j+1, visited);
        updateCell(i+1, j-1, visited);
        updateCell(i+1, j, visited);
        updateCell(i+1, j+1, visited);
    }

    public static void updateCell(int i, int j, boolean[][] visited){
        if(i>=0 && j>=0 && i<visited.length && j<visited.length){
            if(!visited[i][j]){
                mat[i][j]++;
                if(mat[i][j] > 9){
                    visited[i][j] = true;
                    mat[i][j] = 0;
                    updateNeighborsOnFlash(i, j, visited);
                }
            }   
        }
    }

    public static int getVisitedCount(boolean[][] visited){
        int cnt = 0;
        for(boolean[] v:visited){
            for(boolean val:v){
                if(val){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}