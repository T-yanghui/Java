public class printABC {
    static class myTask implements Runnable{
        private String ThreadName;
        //调用wait(),等待前一个线程唤醒
        private Object pre;
        //调用notify(),叫醒下一个线程
        private Object self;
        public myTask(String ThreadName,Object pre,Object self){
            this.ThreadName = ThreadName;
            this.pre = pre;
            this.self = self;
        }
        @Override
        public void run() {
            int count = 10;
            while(count>0){
                synchronized (pre){
                    synchronized(self){
                        System.out.println("Thread-"+ThreadName+" is Print "+ThreadName);
                        count--;
                        self.notify();
                    }
                    try {
                        pre.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object A = new Object();
        Object B = new Object();
        Object C = new Object();

        myTask taskA = new myTask("A",C,A);
        myTask taskB = new myTask("B",A,B);
        myTask taskC = new myTask("C",B,C);

        Thread threadA = new Thread(taskA);
        Thread threadB = new Thread(taskB);
        Thread threadC = new Thread(taskC);

        /*主线程休眠100ms,确保A，B,C依次创建，保证初始顺序*/
        threadA.start();
        Thread.sleep(100);
        threadB.start();
        Thread.sleep(100);
        threadC.start();
    }
}
