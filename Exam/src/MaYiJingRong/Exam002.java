package MaYiJingRong;

import java.util.*;

public class Exam002 {

    /**
     * 本质就是找出矩阵中的两条线
     * */
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 本质就是寻找矩阵中的两条线
//        int q = in.nextInt();
//        for(int i=0; i<q; i++){
//            int row = in.nextInt();
//            int col = in.nextInt();
//            char[][] grid = new char[row][col];
//            for(int j=0; j<row; j++){
//                String line = in.next();
//                for(int k=0; k<col; k++){
//                    grid[j][k] = line.charAt(k);
//                }
//            }
//            System.out.println(judgeTorL(row, col, grid));
//        }
        System.out.println(judgeTorL(3, 3,
                new char[][]{{'.','#','#'},
                             {'.','#','.'},
                             {'.','#','.'}}));
    }

    public static String judgeTorL(int row, int col, char[][] grid){
        Map<Integer, List<int[]>> samex = new HashMap<>();
        Map<Integer, List<int[]>> samey = new HashMap<>();
        for(int j=0; j<row; j++){
            for(int k=0; k<col; k++){
                if(grid[j][k]=='.') continue;
                if(samex.containsKey(j)){
                    samex.get(j).add(new int[]{j,k});
                }else{
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[]{j,k});
                    samex.put(j, list);
                }
                if(samey.containsKey(k)){
                    samey.get(k).add(new int[]{j,k});
                }else{
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[]{j,k});
                    samey.put(k, list);
                }
            }
        }

        // 获取两条线上的所有点（line1一定是横着的，line2一定是竖着的）
        List<int[]> line1 = null, line2=null;
        for(Map.Entry<Integer, List<int[]>> entry: samex.entrySet()){
            List<int[]> val = entry.getValue();
            if(val.size()>1){
                line1 = val;
                break;
            }
        }
        for(Map.Entry<Integer, List<int[]>> entry: samey.entrySet()){
            List<int[]> val = entry.getValue();
            if(val.size()>1){
                line2 = val;
                break;
            }
        }
        // 将两条线上的点进行排序(line1按照y的大小进行排序，line2按照x的大小进行排序)
        Collections.sort(line1, (a, b)->b[1]-a[1]);
        Collections.sort(line2, (a,b)->b[0]-a[0]);

        // line1的端点与line2的端点进行比较
        int[] line1Start = line1.get(0);
        int[] line2Start = line2.get(0);
        int[] line1End = line1.get(line1.size()-1);
        int[] line2End = line2.get(line2.size()-1);

        // 获取两条线的端点，判断端点是否有重合，重合的话一定是L形状
        if((line1Start[0]==line2Start[0] && line1Start[1]==line2Start[1])
        || (line1Start[0]==line2End[0] && line1Start[1]==line2End[1])
        || (line1End[0]==line2Start[0] && line1End[1]==line2Start[1])
        || (line1End[0]==line2End[0] && line1End[1]==line2End[1])){
            return "L";
        }

        return "T";
    }
}

