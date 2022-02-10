package multi_thread;
import java.util.ArrayList;

public class MultiThreadJoin implements Runnable {

    int seq;

    public MultiThreadJoin(int seq) {
        this.seq = seq;
    }

    public void run() {
        System.out.println(this.seq + " thread start.");

        System.out.println(Thread.currentThread().getName());
        
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        System.out.println(this.seq + " thread end.");
    }

    public static void main(String[] args) {

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i <= 10 ; i++) { 
            Thread multiThread = new Thread(new MultiThreadJoin(i));
            multiThread.start();
            /*
            // 각 스레드마다 join() 시 각 스레드 종료 시까지 대기해 순서대로 출력
            // 혹은 그냥 run()을 사용해도 됨 -> 싱글 스레드로 처리
            try {
                multiThread.join();
            } catch(Exception e) {}
            */
            threads.add(multiThread);
        }

        // join() -> 스레드 종료 시까지 대기, "main end"와 threads를 동기화
        for (int i = 0; i < threads.size(); i++) {

            Thread multiThread = threads.get(i);
            try {
                multiThread.join();
            } catch(Exception e) {}

        }

        System.out.println("main end");

    }

}