package com.ustc.difficult;

import java.util.Map;
import java.util.TreeMap;

public class LeeCode715 {
    // 采用有序集合存放所有子区间，再进行合并操作，将所有区间按照左端点升序排序
    // 使用TreeMap存放{左端点, 右端点}, 默认按照键(左端点的值)进行升序排序
    static TreeMap<Integer, Integer> intervals;
    public static void main(String[] args) {
        intervals = new TreeMap<Integer, Integer>();
        addRange(5, 8);
        queryRange(3,4);
        removeRange(5, 6);
        removeRange(3,6);
        addRange(1,3);
        queryRange(2,3);
        addRange(4,8);
        queryRange(2,3);
        removeRange(4,9);
    }
    // firstKey() 和 lastKey()：分别返回 TreeMap 中的第一个和最后一个键
    // lowerKey(K key)：返回严格小于给定键的最大键
    // higherKey(K key)：返回严格大于给定键的最小键
    public static void addRange(int left, int right) {
        // 找出第一个键值(左端点)大于left的区间
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        Map.Entry<Integer, Integer> startCombine; // 第一个准备合并的区间

        if(entry==null){ // 不存在左端点大于left的区间
            startCombine = intervals.lastEntry(); // 获取最后一个区间
            if(startCombine==null){
                intervals.put(left, right);
                return;
            }
            if(startCombine.getValue() < left) // 区间不存在交集
                intervals.put(left, right);
            else
                intervals.put(left, Math.max(right, startCombine.getValue()));
        }else if(entry.equals(intervals.firstEntry())){ // entry区间就是第一个区间
            // 此后区间的左端点都会大于left
            startCombine = entry;
            while(startCombine!=null && startCombine.getKey() <= right){ // 存在交集
                left = left;
                right = Math.max(right, startCombine.getValue());
                intervals.remove(startCombine.getKey());
                startCombine = intervals.higherEntry(startCombine.getKey());
            }
            intervals.put(left, right);
        }else{ // entry区间前面还有一个区间A，区间A的左端点小于left
            startCombine = intervals.lowerEntry(entry.getKey());
            if(startCombine.getValue() >= right){ // 右端点大于right：包含了[left, right)集合
                return;
            }
            if(startCombine.getValue() >= left){ // 右端点位于[left, right)之间：存在重合部分
                left = startCombine.getKey(); // 合并startCombine区间
                right = right;
                // 移除startCombine区间
                intervals.remove(startCombine.getKey());
            }
            // 右端点位于[left, right)左边：不存在交集，执行之后的逻辑
            // 获取下一个区间，此后区间的左端点都会大于left
            startCombine = intervals.higherEntry(startCombine.getKey());
            while(startCombine!=null && startCombine.getKey() <= right){ // 存在交集
                left = left;
                right = Math.max(right, startCombine.getValue());
                intervals.remove(startCombine.getKey());
                startCombine = intervals.higherEntry(startCombine.getKey());
            }
            intervals.put(left, right);
        }

    }

    public static boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry == intervals.firstEntry()) {
            return false;
        }
        entry = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
        return entry != null && right <= entry.getValue();
    }

    public static void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry != intervals.firstEntry()) {
            Map.Entry<Integer, Integer> start = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
            if (start != null && start.getValue() >= right) {
                int ri = start.getValue();
                if (start.getKey() == left) {
                    intervals.remove(start.getKey());
                } else {
                    intervals.put(start.getKey(), left);
                }
                if (right != ri) {
                    intervals.put(right, ri);
                }
                return;
            } else if (start != null && start.getValue() > left) {
                if (start.getKey() == left) {
                    intervals.remove(start.getKey());
                } else {
                    intervals.put(start.getKey(), left);
                }
            }
        }
        while (entry != null && entry.getKey() < right) {
            if (entry.getValue() <= right) {
                intervals.remove(entry.getKey());
                entry = intervals.higherEntry(entry.getKey());
            } else {
                intervals.put(right, entry.getValue());
                intervals.remove(entry.getKey());
                break;
            }
        }
    }
}
