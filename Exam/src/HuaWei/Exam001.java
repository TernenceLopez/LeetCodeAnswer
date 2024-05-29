package HuaWei;

import java.util.*;

// DFS
/*
输入样例：
        3 3
        S 5 6
        1 C 1
        1 E 1
* */
public class Exam001 {
    // We have imported the necessary tool classes.
// If you need to import additional packages or classes, please import here.
    static int minPath = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int[] start = new int[2];
        int[] end = new int[2];
        ArrayList<int[]> clist = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String mn_s = s.nextLine();
        String[] mn_a = mn_s.split(" ");
        int m = Integer.parseInt(mn_a[0]), n = Integer.parseInt(mn_a[1]);

        String[][] grid = new String[m][n];

        for(int i=0; i<m; i++){
            String line = s.nextLine();
            String[] line_a = line.split(" ");
            for(int j=0; j<line_a.length; j++){
                if(line_a[j].equals("S")){
                    start[0] = i;
                    start[1] = j;
                }else if(line_a[j].equals("C")){
                    clist.add(new int[]{i,j});
                }else if(line_a[j].equals("E")){
                    end[0] = i;
                    end[1] = j;
                }
                grid[i][j] = line_a[j];
            }
        }
        int result = Integer.MAX_VALUE;
        for(int[] temp : clist){
            minPath = Integer.MAX_VALUE;
            int[][] getFlag = new int[m][n];
            for(int i=0; i<m; i++){
                Arrays.fill(getFlag[i], -1);
            }
            getFlag[start[0]][start[1]] = 1;
            backtracking(grid, getFlag, start, temp, 0);
            int min1 = minPath;
            minPath = Integer.MAX_VALUE;
            for(int i=0; i<m; i++){
                Arrays.fill(getFlag[i], -1);
            }
            getFlag[temp[0]][temp[1]] = 1;
            backtracking(grid, getFlag, temp, end, 0);
            int min2 = minPath;
            result = Math.min(result, min1+min2);
        }
        System.out.println(result);
    }

    public static void backtracking(String[][] grid, int[][] getFlag, int[] cur, int[] end, int sum){
        int m = grid.length, n = grid[0].length;
        if(cur[0]==end[0] && cur[1]==end[1]){
            minPath = Math.min(minPath, sum);
            return;
        }
        int i = cur[0], j = cur[1];
        if(i>1 && !grid[i-1][j].equals("B") && getFlag[i-1][j] != 1){
            getFlag[i-1][j] = 1;
            if(grid[i-1][j].equals("C") || grid[i-1][j].equals("E"))
                backtracking(grid, getFlag, new int[]{i-1, j}, end, sum);
            else backtracking(grid, getFlag, new int[]{i-1, j}, end, sum+Integer.parseInt(grid[i-1][j]));
            getFlag[i-1][j] = -1;
        }
        if(j>1 && !grid[i][j-1].equals("B") && getFlag[i][j-1] != 1){
            getFlag[i][j-1] = 1;
            if(grid[i][j-1].equals("C") || grid[i][j-1].equals("E"))
                backtracking(grid, getFlag, new int[]{i, j-1}, end, sum);
            else backtracking(grid, getFlag, new int[]{i, j-1}, end, sum+Integer.parseInt(grid[i][j-1]));
            getFlag[i][j-1] = -1;
        }
        if(i<m-1 && !grid[i+1][j].equals("B") && getFlag[i+1][j] != 1){
            getFlag[i+1][j] = 1;
            if(grid[i+1][j].equals("C") || grid[i+1][j].equals("E"))
                backtracking(grid, getFlag, new int[]{i+1, j}, end, sum);
            else backtracking(grid, getFlag, new int[]{i+1, j}, end, sum+Integer.parseInt(grid[i+1][j]));
            getFlag[i+1][j] = -1;
        }
        if(j<n-1 && !grid[i][j+1].equals("B") && getFlag[i][j + 1] != 1) {
            getFlag[i][j + 1] = 1;
            if (grid[i][j + 1].equals("C") || grid[i][j + 1].equals("E"))
                backtracking(grid, getFlag, new int[]{i, j + 1}, end, sum);
            else backtracking(grid, getFlag, new int[]{i, j + 1}, end, sum + Integer.parseInt(grid[i][j + 1]));
            getFlag[i][j+1] = -1;
        }
    }
}
