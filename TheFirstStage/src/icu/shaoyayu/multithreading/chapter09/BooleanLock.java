package icu.shaoyayu.multithreading.chapter09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 * @author shaoyayu
 * @date 2020/8/31 8:53
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class BooleanLock implements Lock {

    /**
     * 是否锁住
     */
    private boolean initValue;

    /**
     * 记录当期线程被执行
     */
    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    /**
     * 记录锁的线程
     */
    private Thread currentThread;

    public BooleanLock(){
        this.initValue = false;
    }

    @Override
    public synchronized void  lock() throws InterruptedException {
        //当前线程被锁住
        while (initValue){
            //把没有能执行的线程添加
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }

        //删除掉锁
        blockedThreadCollection.remove(Thread.currentThread());
        //锁住
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeoutException {
        if(mills<=0){
            lock();
        }
        long hasRemaining =mills;
        long endTime = System.currentTimeMillis()+ mills;
        while(initValue){
            if(hasRemaining <= 0) {
                throw new TimeoutException("Time out");
            }
            blockedThreadCollection.add (Thread.currentThread());
            this.wait (mills);
            hasRemaining = endTime - System.currentTimeMillis() ;
        }
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (this.currentThread==Thread.currentThread()){
            //释放当前的锁
            this.initValue = false;
            Optional.of(Thread.currentThread().getName()+" release the lock monitor")
                    .ifPresent(System.out::println);
            this.notifyAll();
        }

    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
