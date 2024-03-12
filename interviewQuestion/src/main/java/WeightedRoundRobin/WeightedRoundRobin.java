package WeightedRoundRobin;

import java.util.List;

public class WeightedRoundRobin {
    // 服务器列表
    private List<Server> servers;
    // 索引
    private int currentIndex;

    public WeightedRoundRobin(List<Server> servers){
        this.servers = servers;
        this.currentIndex = 0;
    }

    // 根据服务器的权重选择下⼀个服务器
    public Server getNextServer() {
        int totalWeight = calculateTotalWeight();
        int maxWeight = findMaxWeight();
        int gcd = calculateGCD();
        // 按照最⼤权重值进⾏遍历
        while (true) {
            currentIndex = (currentIndex + 1) % servers.size();
            if (currentIndex == 0) {
                maxWeight -= gcd;
                if (maxWeight <= 0) {
                    maxWeight = findMaxWeight();
                    if (maxWeight == 0) {
                        return null; // 所有服务器的权重都为0，⽆法分配请求
                    }
                }
            }
            if (servers.get(currentIndex).getWeight() >= maxWeight) {
                return servers.get(currentIndex);
            }
        }
    }

    // 计算所有服务器的总权重值
    private int calculateTotalWeight() {
        int totalWeight = 0;
        for (Server server : servers) {
            totalWeight += server.getWeight();
        }
        return totalWeight;
    }

    // 找到所有服务器中的最⼤权重值
    private int findMaxWeight() {
        int maxWeight = 0;
        for (Server server : servers) {
            if (server.getWeight() > maxWeight) {
                maxWeight = server.getWeight();
            }
        }
        return maxWeight;
    }

    // 计算所有权重值的最⼤公约数
    private int calculateGCD() {
        int weightsLength = servers.size();
        int[] weights = new int[weightsLength];
        for (int i = 0; i < weightsLength; i++) {
            weights[i] = servers.get(i).getWeight();
        }
        return findGCD(weights, weightsLength);
    }

    // ⽤于找到多个权重值的最⼤公约数
    private int findGCD(int[] weights, int n) {
        int result = weights[0];
        for (int i = 1; i < n; i++) {
            result = gcd(result, weights[i]);
        }
        return result;
    }
    /**
     * 计算两个数的最⼤公约数的辅助⽅法：欧几里德算法
     * 1. 用较大数除以较小数，得到余数。
     * 2. 将较小数作为新的被除数，余数作为新的除数。
     * 3. 重复上述过程，直到余数为0。
     * 4. 最后的除数即为最大公约数。
     * */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
