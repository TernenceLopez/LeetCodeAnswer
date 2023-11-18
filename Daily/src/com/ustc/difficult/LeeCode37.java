package com.ustc.difficult;

public class LeeCode37 {
    public static void main(String[] args) {
        solveSudoku(new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}}
        );
    }
    // 原地修改board
    // 原地修改board
    public static void solveSudoku(char[][] board) {
        backTracking(board, 0, 0);
    }

    // row为当前递归的行，col为当前递归的列
    // row = [0 ~ 8], col = [0 ~ 8]
    public static boolean backTracking(char[][] board, int row, int col){
        for(int i=row; i<9; i++){ // 遍历每一行

            int j = col;
            if(i>row) j=0; //如果i从row自增为row+n后, 相当于递归换行了，j重新从0开始遍历
            // 特殊情况：假设某一行全都已经填满了数字，那么for(; j<9; j++)中必定会一直执行continue，直到跳出这个for循环。
            // 接着i++, 表示换行。此时j不能还是从col开始自加递归，必须指向换行之后的行首开始递归，即j=0.
            // for(j = col; j<9; j++){ // 报错
            for(j = i>row? 0: col; j<9; j++){ // 遍历每一列
                // 确定行列之后，判断[i,j]处是否已经有数字了
                if(board[i][j]!='.') continue;

                for(char k='1'; k<='9'; k++){ // 遍历1～9
                    if(isValid(board, i, j, k)==true){
                        board[i][j] = k;

                        if(j<8){
                            if(backTracking(board, i, j+1)==true) return true;
                        }else if(j==8 && i<8){ // 换行递归
                            if(backTracking(board, i+1, 0)==true) return true;
                        }else if(j==8 && i==8)
                            return true; // 找到叶子节点

                        board[i][j] = '.';
                    }
                }

                // 当前[i, j]位置填充1～9都不能满足要求，所以直接返回
                return false;
            }
        }

        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    // [row, col]表示要填充的元素位置，val表示填充元素的值
    public static boolean isValid(char[][] board, int row, int col, char val){
        // 同行是否重复
        for (int i = 0; i < 9; i++){
            if (board[row][i] == val){
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < 9; j++){
            if (board[j][col] == val){
                return false;
            }
        }
        // 9宫格内是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++){
            for (int j = startCol; j < startCol + 3; j++){
                if (board[i][j] == val){
                    return false;
                }
            }
        }

        return true;
    }
}

