package icu.shaoyayu.multithreading.chapter05;

/**
 * @author shaoyayu
 * @date 2020/6/29 10:58
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 * 停止掉线程
 */
public class StopThread {

    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker();

        worker.start();
        Thread.sleep(1_000);
        worker.shutDown();

    }

    static class Worker extends Thread{
        private volatile boolean state = true;

        @Override
        public void run() {
            while (state){
                System.out.println("线程："+Thread.currentThread().getName()+System.currentTimeMillis());
            }
        }

        public void shutDown(){
            state = false;
        }
    }

}
