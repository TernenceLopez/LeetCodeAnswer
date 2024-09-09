package TencentMusic;

import java.util.*;

public class Exam002 {
    /**
     * 一个长度为m的数组和一个长度为n的数组，存在公共前缀和公共后缀，将公共前缀和后缀提取出来进行拼接后输出。
     * 比如{1 2 2 3 4}{1 2 1 2 3 3 4}的公共前后缀拼接结果为{1 2 3 4}
     * */
    public static void main(String[] args) {
        // {1 2 2 3 4}{1 2 1 2 3 3 4}
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = new ListNode(2);
        a.next.next.next = new ListNode(3);
        a.next.next.next.next = new ListNode(4);

        ListNode b = new ListNode(1);
        b.next = new ListNode(2);
        b.next.next = new ListNode(1);
        b.next.next.next = new ListNode(2);
        b.next.next.next.next = new ListNode(3);
        b.next.next.next.next.next = new ListNode(3);
        b.next.next.next.next.next.next = new ListNode(4);

        ListNode.printListNode(mergeList(a, b));
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param a ListNode类
     * @param b ListNode类
     * @return ListNode类
     */
    public static ListNode mergeList (ListNode a, ListNode b) {
        List<Integer> aVal = new ArrayList<>();
        List<Integer> bVal = new ArrayList<>();
        ListNode temp = a;
        while (temp != null) {
            aVal.add(temp.val);
            temp = temp.next;
        }
        temp = b;
        while (temp != null) {
            bVal.add(temp.val);
            temp = temp.next;
        }
        int m = aVal.size(), n = bVal.size();
        ListNode dummy = new ListNode(1);
        ListNode last = dummy;
        boolean finishFlag = false;
        // 先找前缀
        for (int i = 0; i < Math.min(m, n); i++) {
            if (aVal.get(i) == bVal.get(i)) {
                ListNode preNode = new ListNode(aVal.get(i));
                last.next = preNode;
                last = last.next;
                if (i == m - 1 || i == n - 1) finishFlag = true;
                continue;
            } else {
                break;
            }
        }

        if (finishFlag) return dummy.next;

        // 找后缀
        List<Integer> subValList = new ArrayList<>();
        for (int i = 0; i < Math.min(m, n); i++) {
            if (aVal.get(m - 1 - i) == bVal.get(n - 1 - i)) {
                subValList.add(aVal.get(m - 1 - i));
            }else break;
        }

        for (int i = subValList.size() - 1; i >= 0; i--) {
            ListNode subNode = new ListNode(subValList.get(i));
            last.next = subNode;
            last = last.next;
        }

        return dummy.next;
    }
}
