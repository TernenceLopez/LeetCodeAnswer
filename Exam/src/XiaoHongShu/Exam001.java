package XiaoHongShu;

import java.util.Scanner;

/*
    题目描述：
        小红书的冒险家们！今天，我们要进入一个充满挑战的高科技迷宫。
        这是一张由小红书科技部最新研发的网格地图，每个格子都藏着秘密——它们内置了自动滑行带！
        这些滑行带会让所有进入它们的机器人自动朝一个特定方向滑行。
        具体来说，一张n*m的网格地图，左上角为（1,1)，右下角为（n，m),每个格子有一个滑行带,前进方向为L,R,U,D，分别表示左右上下四个方向前进。
        ·如果第时刻，机器人位于（i,j)，（i,j)滑行带前进方向为L，则第t+1时刻机器人位于（i,j-1)。
        ·如果第t时刻，机器人位于（i,j)，（i,j)滑行带前进方向为R，则第t+1时刻机器人位于(i,j+1)。
        ·如果第t时刻，机器人位于（i,j)，（i,j)滑行带前进方向为U，则第t+1时刻机器人位于（i-1,j)。
        ·如果第t时刻，机器人位于（i.j)，（1,j)滑行带前进方向为D，则第 t+1时刻机器人位于(i+1,j)。
        机器人走出地图后就会毁坏，一个格子可以容纳多个机器人。
        第0时刻，每个位置都有一个机器人，问：第10＾8时刻，地图上还剩下多少个机器人？
    输入描述
        第一行两个整数n m(1≤n*m≤5*10＾3），表示地图大小。
        接下来n行，每行一个包含m个字符的字符串，表示每个格子滑行带的方向
    输出描述
        输出一行一个整数，表示第108时刻，地图上剩下机器人的数量。
*/
public class Exam001 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dir = {'U', 'D', 'L', 'R'};
    static int m, n;
    static char[][] grid;
    static int[][] status; // 0：还没有访问到这个节点；1 正在被访问；2 安全；3 不安全

    /**
     * DFS搜索
     * */
    public static boolean isSafe(int x, int y){
        if(status[x][y]!=0){
            if(status[x][y]==1 || status[x][y]==2){
            // status[x][y]==1说明已经找到闭环
                return true;
            }else{
                return false;
            }
        }
        // 标记已经被访问过了
        status[x][y] = 1;

        int nextX = x, nextY = y;
        switch(grid[x][y]){
            case 'U': nextX = x-1; break;
            case 'D': nextX = x+1; break;
            case 'L': nextY = y-1; break;
            case 'R': nextY = y+1; break;
        }
        // 出界
        if(nextX<0 || nextX>=n || nextY<0 || nextY >=m){
            status[x][y] = 3;
            return false;
        }
        // 递归
        if(isSafe(nextX, nextY)){
            status[x][y] = 2;
            return true;
        }else{
            // 否则标记为不安全
            status[x][y] = 3;
            return false;
        }
    }

    public static void main(String[] args) {
        // m*n < 10^7 < t=10^8，所以只要没有形成闭环，就一定可以走出地图
        // 所以就是相当于统计grid上闭环节点的个数
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        grid = new char[n][m];
        status = new int[n][m];

        // 读取地图
        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        int result = 0;

        // 遍历每一个格子，检查其最终是否安全
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isSafe(i, j)) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
