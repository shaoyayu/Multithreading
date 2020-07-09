package icu.shaoyayu.multithreading.chapter08;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author shaoyayu
 * @date 2020/7/9 22:33
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class CaptureServer {

    final static private LinkedList<Control> CONTROLS = new LinkedList<>() ;
    private final static int MAX_WORKER = 5;

    public static void main(String[] args) {
        List<Thread> worker = new LinkedList<>();
        Arrays.asList("Task0","Task1","Task2","Task3","Task4","Task5","Task6","Task7","Task8","Task9").stream()
                .map(CaptureServer::createCaptureThread)
                .forEach(thread -> {
                    thread.start();
                    worker.add(thread);
                });
        worker.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("Al1 of capture work finished").ifPresent(System.out::println);
    }

    public static Thread createCaptureThread(String name){
        return new Thread(()->{
            Optional.of("The worker ["+Thread.currentThread().getName()+"] begin capture data").ifPresent(System.out::println);
            synchronized (CONTROLS){
                while (CONTROLS.size() > MAX_WORKER){
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }
            Optional.of("The worker ["+Thread.currentThread().getName()+"] is working......").ifPresent(System.out::println);
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLS){
                Optional.of("The worker ["+Thread.currentThread().getName()+"] END capture data").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        },name);
    }

    public static class Control{

    }
}
