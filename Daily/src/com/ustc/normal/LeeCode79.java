package com.ustc.normal;

import java.util.LinkedList;
import java.util.List;

public class LeeCode79 {
    public static void main(String[] args) {
        System.out.println(exist(new char[][]{
                                    {'A','B','C','E'},
                                    {'S','F','E','S'},
                                    {'A','D','E','E'}},"ABCESEEEFS"));
    }

    public static boolean exist(char[][] board, String word) {
        boolean result = false;
        int m = board.length, n = board[0].length;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]==word.charAt(0)){
                    List<Integer> path = new LinkedList<>(); // 避免重复使用单元格
                    result = result || backtracking(i,j,0,board,word,path);
                }
            }
        }
        return result;
    }

    // 返回以board[x][y]为起点能否匹配word.charAt(index)及其之后的字符
    public static boolean backtracking(int x, int y, int index, char[][] board, String word, List<Integer> path){
        boolean result = false; // 以board[x][y]的四个方向为起点能否匹配word.charAt(index+1)及其之后的字符
        int m = board.length, n = board[0].length;

        if(board[x][y] != word.charAt(index)) return false; // 起点都匹配不上，之后更匹配不上
        if(path.contains(x*n + y)) // 已经使用过这个单元格
            return false;
        if(index == word.length()-1) return true; // word全部都匹配上了


        path.add(x*n + y);
        // 回溯四个方向
        if(x>=1){
            result = result || backtracking(x-1, y, index+1, board, word, path);
        }
        if(y>=1){
            result = result || backtracking(x, y-1, index+1, board, word, path);
        }
        if(x<m-1){
            result = result || backtracking(x+1, y, index+1, board, word, path);
        }
        if(y<n-1){
            result = result || backtracking(x, y+1, index+1, board, word, path);
        }
        path.remove(path.size()-1); // 回溯
        return result;
    }
}
