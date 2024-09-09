package LianXiang;

import java.util.Scanner;

/*
    题目描述：
        小明今年儿童节获得了妈妈送的一个可爱的字符串S。而这个字符串也是一个数字串，S仅由数字0，1，…9构成。
        小明在课上记住了如下定义：记字符串S的长度为|S|，其中第i位字符为S[i],一个子串由相邻不间断的字符构成，字符S[i],S[li+1]..S[j]构成其中一个子串，表示为S[i, j](1≤i≤j≤|S|)。
        例如，'abcdefg'有子串'abc''bcde'，但'abd‘不是其子串。
        小明想象着如同切蛋糕一样切分字符串。小明会确定两个端点i,j(1≤i≤j≤|S|，将子串S[i,j]取出。
        小明对数字有着独特的品味，特别的，他希望切出来的这个子串能被4整除。小明想知道他有多少种选择端点的方案，使得他的品味得到满足。
        注意切出的子串允许出现前导0，即”0123“数值上与"123“相同。
        两种选择端点的方案不同当前仅当在两个方案中左端点或者右端点不相同，或者两个端点均是不同的。

    输入描述:
        第一行一个字符串S，表示小明的字符串。
        对于100%的数据，1≤|S|≤50000，其中S中仅包含数学。

    输出描述
        输出一行一个整数表示答案。
    样例输入
        104
    样例输出
        4
    提示：
        S[1,3]=104,可以被4整除
        S[2,3]=04,可以被4整除
        S[2,2]=0,可以被4整除
        S[3,3]=4,可以被4整除
*/
public class Exam002 {
    /**
     * 解体思路：n位数的后两位如果能被4整除，那么整个n位数一定可以被4整除
     * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        scanner.close();

        System.out.println(countDivisibleBy4Substrings(S));
    }

    public static int countDivisibleBy4Substrings(String S) {
        int n = S.length();
        int count = 0;

        // Check single digit substrings
        for (int i = 0; i < n; i++) {
            if ((S.charAt(i) - '0') % 4 == 0) {
                count++;
            }
        }

        // Check two digit substrings
        for (int i = 0; i < n - 1; i++) {
            int num = Integer.parseInt(S.substring(i, i + 2));
            if (num % 4 == 0) {
                count += (i + 1);  // Every substring ending at i+1 can be valid
            }
        }

        return count;
    }
}
