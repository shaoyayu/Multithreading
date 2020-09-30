package icu.shaoyayu.multithreading.chapter09;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author shaoyayu
 * @date 2020/8/31 9:10
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        final BooleanLock booleanLock =new BooleanLock();
        Stream.of("T1","T2","T3","T4")
                .forEach(name ->
                        new Thread(()->{
                            try{
                                booleanLock.lock();
                                Optional.of(Thread.currentThread().getName()+" have the lock Monitor")
                                        .ifPresent(System.out::println);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }finally {
                                booleanLock.unlock();
                            }
                        },name).start()
                        );
        Thread.sleep(100);
        booleanLock.unlock();
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName()+" is working")
                .ifPresent(System.out::println);
        Thread.sleep(3_000);
    }
}
