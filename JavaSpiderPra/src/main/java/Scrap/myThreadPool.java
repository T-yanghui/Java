package Scrap;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName myThreadPool
 * Description Fixed count of tread,block task if there is no free thread
 * Author yang
 * @Date 2021/6/6 下午9:09
 * Version 1.0
 **/
public class myThreadPool {
    //线程池线程数量
    private int ThreadNum = 1;
    //当前线程数目
    private AtomicInteger threadAlive = new AtomicInteger(0);
    private ExecutorService executorService;
    //Lock,when ThreadPool has no free thread,block;
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();
    public myThreadPool(int TheadNum){
        this.ThreadNum = TheadNum;
        this.executorService = Executors.newFixedThreadPool(TheadNum);
    }
    public void execute(Runnable runnable){
        if(getThreadAlive() >= ThreadNum){
            try{
                reentrantLock.lock();
                while (getThreadAlive() >= ThreadNum){
                    try {
                        condition.await();
                    }catch (Exception e){}
                }
            }finally {
                reentrantLock.unlock();
            }
        }
        threadAlive.getAndIncrement();
        executorService.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        try{
                                            runnable.run();
                                        }finally {
                                            try{
                                                reentrantLock.lock();
                                                threadAlive.decrementAndGet();
                                                condition.signal();
                                            }finally {
                                                reentrantLock.unlock();
                                            }
                                        }
                                    }
                                });
    }
    public int getThreadAlive(){
        return threadAlive.get();
    }
    public void shutdown(){
        executorService.shutdown();
    }
    public boolean isshutdown(){
        return executorService.isShutdown();
    }
}
