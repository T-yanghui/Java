public class interruptExample {
    static class myTask implements Runnable{
        private int num = 0;
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(++num);
                System.out.println(Thread.currentThread().toString());
            }
            System.out.println("myThread is Interrupt.");
            System.out.println(Thread.currentThread().toString());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        myTask task = new myTask();
        Thread thread = new Thread(task);
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(1000);
        thread.interrupt();

    }
}
