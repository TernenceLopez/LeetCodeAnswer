package ProducerAndConsumer;

import javax.print.DocFlavor;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true){
                consume(); // 消费物品
            }
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public void consume() throws InterruptedException{
        synchronized (queue){
            if(queue.size() == 0){
                System.out.println("消息队列为空");
                queue.wait(); // 消费者线程等待，并释放锁对象
            }
            int i = queue.take();
            System.out.println("consume:"+i);
            queue.notifyAll(); // 唤醒所有等待的生产者线程
        }
    }
}
