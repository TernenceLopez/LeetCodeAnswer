package ProducerAndConsumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    private final BlockingQueue<Integer> queue; // 任务队列
    private final int MAX_SIZE = 5; // 缓冲区最大容量

    public Producer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        try {
            for(int i=0; i<10; i++) {
                produce(i);
                Thread.sleep(100);
            }
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public void produce(int i) throws InterruptedException{
        synchronized (queue){ // 加锁
            if(queue.size()==MAX_SIZE){
                System.out.println("缓冲区已满");
                queue.wait(); // 生产者等待
            }
            queue.put(i); // 将物品放进缓冲区
            System.out.println("produce:" + i);
            queue.notifyAll(); // 唤醒消费者队列
        }
    }
}
