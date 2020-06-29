package icu.shaoyayu.multithreading.chapter05;

/**
 * @author shaoyayu
 * @date 2020/6/29 10:58
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 * 停止掉线程
 */
public class StopThread2 {

    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker();

        worker.start();
        Thread.sleep(5_000);
        worker.interrupt();

    }

    static class Worker extends Thread{

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(1_000);
                    System.out.println("线程："+Thread.currentThread().getName()+System.currentTimeMillis());
                }catch (InterruptedException e){
                    break;
                }

            }
        }

    }

}
