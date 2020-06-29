package icu.shaoyayu.multithreading.chapter05;

/**
 * @author shaoyayu
 * @date 2020/6/29 10:16
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class ThreadInterrupt {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1_000);
                    System.out.println(Thread.currentThread().getName()+"当前时间"+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("中断线程前----"+System.currentTimeMillis()+"，线程状态："+thread.isInterrupted());
        thread.interrupt();
        System.out.println("中断线程后----"+System.currentTimeMillis()+"，线程状态："+thread.isInterrupted());
    }

}
