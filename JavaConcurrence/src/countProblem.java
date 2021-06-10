import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class countProblem {
    private static int count = 0;

    public static int getCount() {
        return count;
    }

    public static void addCount() {
        count++;
    }

    static class NameThreadFactory implements ThreadFactory{
        private final AtomicInteger mThreadNum = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable runnable) {
            Thread t = new Thread(runnable,"Thread-"+mThreadNum.getAndIncrement());
            System.out.println(t.getName()+"is created.");
            return t;
        }
    }
    static class rejectPolicy implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            dolog(runnable,threadPoolExecutor);
        }
        private void dolog(Runnable runnable,ThreadPoolExecutor threadPoolExecutor){
            System.out.println(runnable.toString()+" rejected.");
            System.out.println("completedNum: "+threadPoolExecutor.getCompletedTaskCount());
        }
    }
    static class myTask implements Runnable{
        private String name;
        public myTask(String name){
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println(this.toString()+" is runnig."+"Thread: "+Thread.currentThread().getName()+" Count: "+count);
            addCount();
          //  System.out.println(this.toString()+" is runnig."+"Thread: "+Thread.currentThread().getName()+" Count: "+count);

//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        public String toString(){
            return "myTask"+name.toString();
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        int corePoolSize = 50;
        int maxPoolSize = 100;
        long keepAliveTime = 10;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(2);
        NameThreadFactory nameThreadFactory = new NameThreadFactory();
        rejectPolicy handler = new rejectPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,timeUnit,blockingQueue,nameThreadFactory,handler);
        //threadPoolExecutor.prestartAllCoreThreads();
        for(int i=1;i<=100;i++){
            myTask task = new myTask(String.valueOf(i));
            threadPoolExecutor.execute(task);
        }

        threadPoolExecutor.shutdown();
        //Thread.sleep(1000);
        System.out.println(count);
    }
}