package icu.shaoyayu.multithreading.chapter05;

/**
 * @author shaoyayu
 * @date 2020/6/29 12:18
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class ThreadTest {

    public static void main(String[] args) {

        ThreadService threadService = new ThreadService();

        threadService.startTask(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"时间："+System.currentTimeMillis());
                }
            }
        });
        long startingTime = System.currentTimeMillis();
        threadService.shutDown(5_000);
        long endTime = System.currentTimeMillis();

        System.out.println("线程耗时："+(endTime-startingTime));

    }
}
