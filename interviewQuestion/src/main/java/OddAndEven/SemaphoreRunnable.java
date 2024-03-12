package OddAndEven;
import java.util.concurrent.Semaphore;

class SemaphoreRunnable {
    private static Semaphore semOdd = new Semaphore(1);  // 控制奇数打印的信号量，初始值为1
    private static Semaphore semEven = new Semaphore(0); // 控制偶数打印的信号量，初始值为0

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddPrinter());
        Thread evenThread = new Thread(new EvenPrinter());

        oddThread.start();
        evenThread.start();
    }

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i += 2) {
                try {
                    semOdd.acquire(); // 获取奇数打印的信号量
                    System.out.println(Thread.currentThread().getName()+":"+i);
                    semEven.release(); // 释放偶数打印的信号量，偶数打印的许可数目+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 2; i <= 100; i += 2) {
                try {
                    semEven.acquire(); // 获取偶数打印的信号量
                    System.out.println(Thread.currentThread().getName()+":"+i);
                    semOdd.release(); // 释放奇数打印的信号量，奇数打印的许可数目+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
