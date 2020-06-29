package icu.shaoyayu.multithreading.chapter01;

/**
 * @author shaoyayu
 * @date 2020/6/27 12:46
 * @E_Mail
 * @Version ：1.0.0
 * @detail ：
 * Java 多线程的创建
 */
public class TryConcurrencty {

    public static void main(String[] args) {
        Thread thread = new Thread("Th 1"){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread "+getName()+" print data"+i);
                    rest(1000L);
                }
            }
        };
        thread.start();
        rest(500L);
        System.out.println("Thread main thread print data");

        Thread2 thread1 = new Thread2();

        Thread thread2 = new Thread(thread1);
        thread2.start();
    }

    public static void rest(long period){
        try {
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Thread2 implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread implement  Runnable print data"+i);
                rest(1000L);
            }
        }
    }
}

