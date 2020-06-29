package icu.shaoyayu.multithreading.chapter02;

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

    @Override
    public void run() {

        while(index <= MAX){
            System.out.println(Thread.currentThread().getName()+"'S number is "+(index++));
        }

    }
}
