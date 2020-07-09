package icu.shaoyayu.multithreading.chapter07;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * @author shaoyayu
 * @date 2020/6/30 11:45
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 * 生成者消费者
 */
public class Consumer {

    private final Object LOCK = new Object();

    private final int SIZE = 50;

    private int pond = 0;

    private int sum = 100;

    private int count = 0;


    public void produce(){
        synchronized (LOCK){
            if (pond<SIZE){
                //生产
                pond++;
                count++;
                System.out.println("["+ Thread.currentThread().getName()+"]生产产品："+pond+",已经生产了："+count);
                //通知
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOCK.notify();
            }else {
                //进入休眠
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void consume(){
        synchronized (LOCK){
            if (pond>0){
                //可以消耗
                System.out.println("["+Thread.currentThread().getName()+"]消费产品:"+pond);
                pond--;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOCK.notify();
                LOCK.notifyAll();
            }else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        Producer producer = new Producer(consumer);
        Thread t1 = new Thread(producer, "生产者1号");
        Thread t2 = new Thread(producer, "生产者2号");
        Thread t3 = new Thread(producer, "生产者3号");
        t1.start();
        t2.start();
        t3.start();
        Consumers consumers = new Consumers(consumer);
        Thread c1 = new Thread(consumers,"消费者1号");
        Thread c2 = new Thread(consumers,"消费者1号");
        c1.start();
        c2.start();
    }
}
class Producer implements Runnable{

    private Consumer consumer;

    public Producer(Consumer consumer){
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true){
            consumer.produce();
        }

    }
}

class Consumers implements Runnable {
    private Consumer consumer;
    public Consumers(Consumer consumer){
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            consumer.consume();
        }
    }
}