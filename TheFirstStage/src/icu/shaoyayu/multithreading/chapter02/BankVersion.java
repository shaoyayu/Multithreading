package icu.shaoyayu.multithreading.chapter02;

/**
 * @author shaoyayu
 * @date 2020/6/28 9:01
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class BankVersion {
    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();

        Thread tw1 = new Thread(ticketWindow,"Window 1");
        Thread tw2 = new Thread(ticketWindow,"Window 2");
        Thread tw3 = new Thread(ticketWindow,"Window 3");

        tw1.start();
        tw3.start();
        tw2.start();

    }
}
