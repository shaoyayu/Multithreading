package icu.shaoyayu.multithreading.chapter04;

import java.util.stream.IntStream;

/**
 * @author shaoyayu
 * @date 2020/6/29 9:35
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class ThreadJoin {

    public static void main(String[] args) {

        Thread th1 = new Thread(() -> {
            IntStream.range(1,50)
                    .forEach(i ->
                            System.out.println(Thread.currentThread().getName()+"-->"+i));
        });
        Thread th2 = new Thread(() -> {
            IntStream.range(1,50)
                    .forEach(i ->
                            System.out.println(Thread.currentThread().getName()+"-->"+i));
        });
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.range(1,50)
                .forEach(i ->
                        System.out.println(Thread.currentThread().getName()+"-->"+i));

    }

}
