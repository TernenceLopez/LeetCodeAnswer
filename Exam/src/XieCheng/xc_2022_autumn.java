package XieCheng;

/*
游游定义一个字符串是“好串”，当且仅当该字符串相邻的字符不相等。
例如"arcaea"是好串，而"food"不是好串。
游游拿到了一个字符串，她可以将该字符串的各个字符顺序随意打乱。
她想知道一共可以生产多少种不同的好串？

    示例1
    输入例子：aab
    输出例子：1
    例子说明：只有"aba"这一种好串。

    示例2
    输入例子：arc
    输出例子：6
*/

import java.util.Scanner;

public class xc_2022_autumn {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            System.out.println(countGood(str));
        }
    }

    public static int result = 0;
    public static int countGood(String str){
        // 记录每个字符出现的次数
        int[] countChar = new int[26];
        for(int i=0; i<str.length(); i++)
            countChar[str.charAt(i)-'a']++;

        result = 0;
        dfs(countChar, 0, str.length(), -1);
        return result;
    }
    // lastChar：上一个字符  curLen：当前字符串长度  len：目标字符串长度
    public static void dfs(int[] countChar, int curLen, int len, int lastChar){
        if(curLen==len){ // 所有字符都用到了
            result++; return;
        }

        for(int i=0; i<26; i++){
            // 保证是不同字符
            if(i!=lastChar && countChar[i]>0){
                countChar[i]--;
                dfs(countChar, curLen+1, len, i);
                countChar[i]++;
            }
        }
    }
}
