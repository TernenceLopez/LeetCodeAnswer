package WeightedRoundRobin;

import java.lang.reflect.WildcardType;

/**
 * 服务器对象
 * */
public class Server {
    private String name;
    private int weight;

    public Server(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    public String getName(){
        return this.name;
    }

    public int getWeight(){
        return this.weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }
}
