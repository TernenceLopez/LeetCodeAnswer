package com.ustc.normal;

import java.util.Arrays;

public class LeeCode200 {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        numIslands(grid);
    }
    public static int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(m*n); // 0和1都看作一个节点，都被记为一个独立的连通分量

        int count = 0; // 先假设所有岛屿都是独立的，统计grid中1的个数
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                // 岛屿编号 index = i*n+j; 右边岛屿编号 r = i*n+j+1; 下边岛屿编号 d = (i+1)*n+j;
                int cur = i*n+j, r = i*n+j+1, d=(i+1)*n+j;
                if(i==m-1 && j!=n-1 && (grid[i][j]=='1' && grid[i][j+1]=='1')){
                    uf.unite(cur, r);
                }else if(i!=m-1 && j==n-1 && (grid[i][j]=='1' && grid[i+1][j]=='1')){
                    uf.unite(cur, d);
                }else if(i!=m-1 && j!=n-1){
                    if(grid[i][j]=='1' && grid[i][j+1]=='1') uf.unite(cur, r);
                    if(grid[i][j]=='1' && grid[i+1][j]=='1') uf.unite(cur, d);
                }
                if(grid[i][j]=='1') count++;
            }
        }
        return uf.setCount - (m*n - count);
    }
}

class UnionFind{
    int[] parent; // 记录每个节点的父节点(但这个父节点不一定是顶级父节点)
    int[] size;   // 记录每个节点对应的连通分量中的节点总数
    int n;        // 记录总节点数目
    int setCount; // 当前独立的连通分量数目 (初始化n个节点，有n个独立的连通分量)

    public UnionFind(int n){
        this.parent = new int[n];
        this.size = new int[n];
        this.n = n;
        this.setCount = n; // 初始化还没有任何连通关系，每个节点对应一个独立的连通分量.(一共n个连通分量)

        Arrays.fill(size, 1);
        for(int i=0; i<n; i++){
            parent[i] = i; // 初始化每个节点的父亲就是自己
        }
    }

    // 查询节点x的顶级父节点
    public int find(int x){
        if(parent[x] == x) return x; // 当前节点的父节点就是自己，则当前节点就是顶级父节点
        else{
            // 路径压缩：当前节点父节点parent[x] = 当前节点父节点parent[x]的顶级父节点
            //         从而将路径上的所有节点都统一指向了顶级父节点
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    // 合并两节点 (返回是否合并成功)
    public boolean unite(int x, int y){
        int x_father = find(x); // x 的顶级父节点
        int y_father = find(y); // y 的顶级父节点

        if(x_father==y_father) return false;

        // 建立连接关系（路径压缩）
        if(size[x_father] < size[y_father]){ // y对应的连通分量大
            parent[x_father] = y_father; // x_parent父节点设置为y_parent：小集合合并到大集合中
            size[y_father] += size[x_father]; // 只需要维护顶级父节点的size即可
        }else{
            parent[y_father] = x_father; // y_parent父节点设置为x_parent
            size[x_father] += size[y_father];
        }

        setCount--; // 连通分量总数减一
        return true;
    }

    // 判断两个节点是否相连（等价于判读两个节点的顶级父节点是否相同）
    public boolean isConnected(int x, int y){
        int x_father = find(x);
        int y_father = find(y);
        return x_father == y_father;
    }
}
