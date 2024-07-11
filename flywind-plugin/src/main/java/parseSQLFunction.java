import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class parseSQLFunction {
    public static void main(String[] args) {
        System.out.println(parseFunctionExpressions("@TIMESTAMPDIFF(day, createTime, 'NOW()')"));
    }

    public static List<String> parseFunctionExpressions(String sql) {
        List<String> functionCalls = new ArrayList();
        Stack<Integer> stack = new Stack();
        char[] chars = sql.toCharArray();
        boolean isInSingleQuotes = false;

        for(int i = 0; i < chars.length; ++i) {
            if (chars[i] == '\'') {
                isInSingleQuotes = !isInSingleQuotes;
            }

            if (!isInSingleQuotes) {
                if (chars[i] == '@') {
                    stack.push(i);
                } else if (chars[i] == ')' && stack.size() == 1) {
                    int startIndex = (Integer)stack.pop();
                    String functionCall = sql.substring(startIndex, i + 1);
                    functionCalls.add(functionCall);
                } else if (chars[i] == ')' && !stack.isEmpty()) {
                    stack.pop();
                }
            }
        }

        return functionCalls;
    }
}
