package com.ustc.normal;

import java.util.*;

public class LeeCode690 {

    private static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public static void main(String[] args) {
        // [[1,5,[2,3]],[2,3,[4]],[3,4,[]],[4,1,[]]]
        List<Employee> employees = new ArrayList<>();
        Employee employee001 = new Employee();
        employee001.id = 1;
        employee001.importance = 5;
        employee001.subordinates = new ArrayList<>();
        employee001.subordinates.add(2); employee001.subordinates.add(3);

        Employee employee002 = new Employee();
        employee002.id = 2;
        employee002.importance = 3;
        employee002.subordinates = new ArrayList<>();
        employee002.subordinates.add(4);

        Employee employee003 = new Employee();
        employee003.id = 3;
        employee003.importance = 4;
        employee003.subordinates = new ArrayList<>();

        Employee employee004 = new Employee();
        employee004.id = 4;
        employee004.importance = 1;
        employee004.subordinates = new ArrayList<>();

        employees.add(employee001); employees.add(employee002); employees.add(employee003); employees.add(employee004);
        System.out.println(getImportance(employees, 1));
    }

    /**
     * handler: 构造数结构 + 递归求和
     * */
    public static int getImportance(List<Employee> employees, int id) {
        // 记忆化搜索
        int[] memo = new int[2001]; Arrays.fill(memo, 0);
        int[] valueQuery = new int[2001];
        employees.forEach(employee -> {
            valueQuery[employee.id] = employee.importance;
        });
        Map<Integer, List<Integer>> treeGraph = new HashMap<>();
        employees.forEach(each -> {
            if(treeGraph.containsKey(each.id)){
                treeGraph.get(each.id).addAll(each.subordinates);
            }else{
                treeGraph.put(each.id, each.subordinates);
            }
        });
        return traverseTreeGraph(treeGraph, id, memo, valueQuery);
    }

    /**
     * 获取id节点的所有下属节点的值之和
     * */
    public static int traverseTreeGraph(Map<Integer, List<Integer>> treeGraph, int id, int[] memo, int[] valueQuery) {
        if(treeGraph.get(id).size()==0){
            return valueQuery[id];
        }
        if(memo[id]!=0) return memo[id];

        int sum = valueQuery[id];
        for(Integer subId: treeGraph.get(id)){
            sum += traverseTreeGraph(treeGraph, subId, memo, valueQuery);
        }

        memo[id] = sum;
        return sum;
    }
}


