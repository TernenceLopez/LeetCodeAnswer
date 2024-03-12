package OddAndEven;

public class ParityPrinter {
    // 线程共享变量，总打印次数
    private int max;

    // 线程共享变量，当前打印的数字
    private int number = 1;

    // 线程共享变量，表示当前是否需要打印奇数
    private boolean odd = true;

    public ParityPrinter(int max){
        this.max = max;
    }

    /**
     * 打印奇数：锁对象是ParityPrinter对象
     * */
    public synchronized void printOdd(){
        while(number<max){
            while(!odd){ // odd=true：当前需要打印奇数，则不wait()
                try {
                    this.wait();
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":"+number);
            number++;
            odd = false;
            this.notify(); // 唤醒打印偶数的线程
        }
    }

    /**
     * 打印偶数：锁对象是ParityPrinter对象
     * */
    public synchronized void printEven(){
        while(number<max){
            while(odd){ // odd=true：当前需要打印奇数，则wait()等待
                try{
                    this.wait();
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+number);
            number++;
            odd = true;
            this.notify(); // 唤醒打印奇数的线程
        }
    }
}
