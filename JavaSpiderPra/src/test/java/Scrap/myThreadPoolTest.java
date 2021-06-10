package Scrap;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class myThreadPoolTest {

    @Test
    void execute() {
        AtomicInteger count = new AtomicInteger(0);
        class testTask implements Runnable{
            @Override
            public void run(){
                for(int i=0;i<10;i++){
                    count.getAndIncrement();
                    System.out.println(Thread.currentThread().getName()+" "+count.get());
                }
            }
        }
        myThreadPool myThreadPool = new myThreadPool(5);
        for(int i=0;i<5;i++){
            myThreadPool.execute(new testTask());
        }
    }

    @Test
    void getThreadAlive() {
    }

    @Test
    void shutdown() {
    }

    @Test
    void isshutdown() {
    }
}