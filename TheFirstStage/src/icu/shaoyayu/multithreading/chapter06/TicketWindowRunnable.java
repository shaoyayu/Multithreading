package icu.shaoyayu.multithreading.chapter06;

/**
 * @author shaoyayu
 * @date 2020/6/28 8:55
 * @E_Mail
 * @Version 1.0.0
 * @readme ：实现一个售票的窗口
 */
public class TicketWindowRunnable implements Runnable {

    private int index = 0;

    private static final int MAX = 50;

    private final Object MONINOR = new Object();

    @Override
    public void run() {

        while(true){
            synchronized (MONINOR){
                if (index>MAX) {
                    break;
                }
                System.out.println(Thread.currentThread().getName()+"'S number is "+(index++));
            }
            try {
                Thread.sleep(1_00);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
