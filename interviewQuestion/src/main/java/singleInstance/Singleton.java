package singleInstance;

public class Singleton {
    private static Singleton uniqueInstance;

    // 私有化构造方法
    private Singleton(){}

    public static Singleton getInstance(){
        // 先判读对象是否已经实例化过，如果没有才进入加锁代码
        if(uniqueInstance==null){
            // 类对象加锁
            synchronized (Singleton.class){
                if(uniqueInstance!=null) return uniqueInstance;
                else uniqueInstance = new Singleton();
            }
        }

        return uniqueInstance;
    }
}
