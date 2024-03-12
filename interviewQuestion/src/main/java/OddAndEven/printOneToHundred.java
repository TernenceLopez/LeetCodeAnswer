package OddAndEven;

public class printOneToHundred {
    public static void main(String[] args) {
        ParityPrinter parityPrinter = new ParityPrinter(100);
        // 创建两个线程，分别传⼊不同的 Runnable 对象
        Thread thread1 = new Thread(parityPrinter::printOdd);
        Thread thread2 = new Thread(parityPrinter::printEven);

        // 启动两个线程
        thread1.start();
        thread2.start();
    }
}
