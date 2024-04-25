package com.ustc.normal;

import java.util.ArrayList;
import java.util.List;

public class LeeCode1146 {
    public static void main(String[] args) {
        SnapshotArray sa = new SnapshotArray(3);
        sa.set(0,5);
        sa.snap();
        sa.set(0,6);
        sa.get(0,0);
    }
}

// 只创建一个列表，新值不断覆盖旧值，直到创建快照
class SnapshotArray {
    List<List<Integer>> arr = new ArrayList<>();
    int snapCount = -1;

    public SnapshotArray(int length) {
        for(int i=0; i<length; i++){
            arr.add(new ArrayList<Integer>());
            arr.get(i).add(0); // 初始化为0
        }
    }

    public void set(int index, int val) {
        arr.get(index).set(arr.get(index).size()-1, val);
    }

    public int snap() {
        for(int i=0; i<arr.size(); i++){
            arr.get(i).add(arr.get(i).get(arr.get(i).size()-1)); // 创建备份
        }
        snapCount++;
        return snapCount;
    }

    public int get(int index, int snap_id) {
        return arr.get(index).get(snap_id);
    }
}
