package icu.shaoyayu.multithreading.chapter05;

/**
 * @author shaoyayu
 * @date 2020/6/29 11:57
 * @E_Mail
 * @Version 1.0.0
 * @readme ：
 */
public class ThreadService {

    /**
     * 需要一个子线程
     */
    private Thread threadOfExecution;

    /**
     * 设置一个是否执行完的状态
     */
    private boolean isFinished = false;

    /**
     * 执行任务
     * @param task
     */
    public void startTask(Runnable task){
        threadOfExecution = new Thread(){
            //把threadOfExecution当父线程启动一个子线程任务
            @Override
            public void run() {
                Thread threadTask = new Thread(task);
                //设置threadOfExecution为ThreadTask的守护线程
                threadTask.setDaemon(true);
                //启动任务线程ThreadTask
                threadTask.start();
                //设置threadOfExecution等待线程threadTask执行完毕再执行
                try {
                    threadTask.join();
                    //当threadTask执行完毕的
                    isFinished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //启动父线程threadOfExecution
        threadOfExecution.start();
    }

    /**
     * 设置多少时间后关闭线程
     * @param mills
     */
    public void shutDown(long mills){
        //设置一个计时器
        long timing = System.currentTimeMillis();
        while (!isFinished){
            try {
                if ((System.currentTimeMillis()-timing) >= mills){
                    //代表任务执行完毕，关闭线程
                    System.err.println("任务执行完毕，关闭线程");
                    threadOfExecution.interrupt();
                    break;
                }
            }catch (Exception e){
                break;
            }

        }
        isFinished = false;
    }

}
