package icu.shaoyayu.multithreading.chapter11;

/**
 * @author shaoyayu
 * @date 2020/9/29
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class ThreadGroupApi {

    public static void main(String[] args) {
        //
        ThreadGroup tg1 = new ThreadGroup("tg1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1,"tg2");

        Thread t2 = new Thread(tg2,"t2"){
            @Override
            public void run() {
                try {
                    System.out.println(getThreadGroup().getName());
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();
    }
}
