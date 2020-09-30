package icu.shaoyayu.multithreading.chapter11;

/**
 * @author shaoyayu
 * @date 2020/9/29
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class ThreadGroupCreate {

    public static void main(String[] args) {

        //main线程的信息
        System.out.println(Thread.currentThread().getName());
        //输出： main
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        //输出的信息 main

        
    }

}
