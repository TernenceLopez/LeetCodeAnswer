package WeightedRoundRobin;

import java.util.List;
import java.util.ArrayList;

public class TryServerSelect {
    public static void main(String[] args) {
        List<Server> servers = new ArrayList<>();
        servers.add(new Server("Server1", 3));
        servers.add(new Server("Server2", 2));
        servers.add(new Server("Server3", 1));
        WeightedRoundRobin wrr = new WeightedRoundRobin(servers);
        // 模拟请求分发
        for (int i = 0; i < 10; i++) {
            Server server = wrr.getNextServer();
            System.out.println("Request " + (i+1) + " sent to: " +
                    server.getName());
        }
    }
}
