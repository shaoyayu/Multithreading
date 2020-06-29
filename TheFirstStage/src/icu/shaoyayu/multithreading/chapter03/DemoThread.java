package icu.shaoyayu.multithreading.chapter03;

/**
 * @author shaoyayu
 * @date 2020/6/29 8:50
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class DemoThread {

    public static void main(String[] args) {

        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" thread start");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+" thread end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 设置一个守护线程,则ThreadGroup结束的时候，线程也会自动的结束，在这里则是父线程main结束的时候，子线程也会跟着结束。需要在启动之前设置
        th.setDaemon(true);
        th.start();
        System.out.println(Thread.currentThread().getName()+" thread");

    }

}
