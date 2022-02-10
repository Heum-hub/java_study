package multi_thread;
public class SynchTest {
    
    public static void main(String[] args) {

        Synch synch = new Synch();

        for (int i = 0; i < 10 ; i++) {
            Thread t = new Thread(synch);
            t.start();
            /* join() 혹은 run()과 synchronized의 차이?
            try {
                t.join();
            } catch(Exception e) {}
            */
        }

    }

}

class Synch implements Runnable {

    private int c = 0;

    // synchronized는 블록 방식 선호
    public void increment() {
        synchronized(this) {
            try{
                Thread.sleep(1000);
                c++;
                System.out.println(Thread.currentThread().getName());
                System.out.println(c);
            } catch (Exception e) {}
        }
    }

    public void run() {
        increment();
    }


}