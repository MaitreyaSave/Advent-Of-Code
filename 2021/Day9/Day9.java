package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


import Day4.Day4;

public class Day9 {
    static List<String> input;
    static int[][] mat;
    static int m,n;
    public static void main(String[] args) {
        // Input
        File file = new File("2021/Day9/input.txt");
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

        initMatrix();
        int sum = 0;

        for(int i=1; i<m-1; i++){
            for(int j=1; j<n-1; j++){
                if(isLowPoint(i, j, mat)){
                    sum += mat[i][j] + 1;
                }
            }
        }
        System.out.println("Part 1 risk sum: " + sum);
    }
    public static void part2(){
        initMatrix();

        boolean[][] v = new boolean[m][n];
        
        // Mark all highest points as visited
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] > 8){
                    v[i][j] = true;
                }
            }
        }

        List<Integer> sizes = new ArrayList<>();

        // continue while there are unvisited nodes
        while(!isEverythingVisited(v)){
            int[]xy = getNextUnvisitedNode(v);
            int x = xy[0];
            int y = xy[1];

            int basinSize = floodFill(x, y, v);
            sizes.add(basinSize);
        }

        Collections.sort(sizes, Collections.reverseOrder());
        int product = 1;
        for(int i=0; i<3; i++){
            product *= sizes.get(i);
        }
        System.out.println("Part 2 product: "+product);
    }


    public static void initMatrix(){
        // + 2 for padding on both sides
        n = input.get(0).length() + 2;
        m = input.size() + 2;
        mat = new int[m][n];
        initializeMatrixWithValue(10, mat);

        for(int i=0; i<input.size(); i++){
            char[] row = input.get(i).toCharArray();
            for(int j=0; j<row.length; j++){
                mat[i+1][j+1] = Character.getNumericValue(row[j]);
            }
        }       
    }

    public static int floodFill(int x, int y, boolean[][] v){
        if(v[x][y]){
            return 0;
        }
        v[x][y] = true;
        return floodFill(x+1, y, v) + floodFill(x, y+1, v) + floodFill(x-1, y, v) + floodFill(x, y-1, v) + 1;
    }

    public static int[] getNextUnvisitedNode(boolean[][] v){
        int x = -1, y = -1;

        for(int i=0; i<v.length; i++){
            for(int j=0; j<v[i].length; j++){
                if(!v[i][j]){
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        int[] xy = {x,y};
        return xy;
    }

    public static boolean isEverythingVisited(boolean[][] v){
        boolean result = true;

        for(boolean[] v1: v){
            for(boolean val: v1){
                if(!val){
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    public static boolean isLowPoint(int x, int y, int[][] m){
        int val = m[x][y];
        if(val < m[x-1][y] && val < m[x+1][y] && val < m[x][y-1] && val < m[x][y+1])
            return true;
        else
            return false;
    }
    public static void initializeMatrixWithValue(int n, int[][] mat){
        for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat[i].length; j++){
                mat[i][j] = n;
            }
        }
    }
}