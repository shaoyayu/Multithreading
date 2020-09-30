package icu.shaoyayu.multithreading.chapter09;

import java.util.Collection;
import java.util.concurrent.TimeoutException;

/**
 * @author shaoyayu
 * @date 2020/8/31 8:36
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public interface Lock {

    class TimeOutException extends Exception {

        public TimeOutException(String message){
            super(message);
        }

    }

    /**
     * 上锁，
     * @throws InterruptedException 允许中断
     */
    void lock() throws InterruptedException;

    /**
     * 等待多少时间以后自行的退出
     * @param mills
     * @throws InterruptedException
     * @throws TimeOutException
     */
    void lock(long mills) throws InterruptedException, TimeOutException, TimeoutException;

    /**
     * 释放锁
     */
    void unlock();

    /**
     * 等待当前锁的线程
     * @return
     */
    Collection<Thread> getBlockedThread();

    /**
     * 获取当前等待线程的打下
     * @return
     */
    int getBlockedSize();

}
