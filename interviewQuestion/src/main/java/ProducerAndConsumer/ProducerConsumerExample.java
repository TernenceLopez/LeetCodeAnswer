package ProducerAndConsumer;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        // 创建缓冲区
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // 创建生产者、消费者
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();
    }
}
