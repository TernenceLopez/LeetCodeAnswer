package DeadLock;

public class DeadLockDemo {
    private static Object source1 = new Object();
    private static Object Source2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (source1){
                System.out.println("线程1获取了source1");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                   ex.printStackTrace();
                }

                System.out.println("线程1准备获取source2");
                synchronized (Source2){
                    System.out.println("线程1获取了source2");
                }
            }
        }, "thread1").start();

        new Thread(()->{
            synchronized (source1){
                System.out.println("线程2获取了source1");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }

                System.out.println("线程2准备获取了source2");
                synchronized (Source2){
                    System.out.println("线程2获取了source2");
                }
            }
        }, "thread2").start();
    }
}
