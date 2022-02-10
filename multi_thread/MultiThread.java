package multi_thread;
public class MultiThread implements Runnable {

    int seq;

    public MultiThread(int seq) {
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

        for (int i = 0; i <= 10 ; i++) { 
            Thread multiThread = new Thread(new MultiThread(i));
            // start()는 run()과 달리 멀티스레드, 네이티브 영역에서 스레드를 생성 후 시작되면 run() 실행
            multiThread.start();
        }
        // 비동기적으로 먼저 실행됨
        System.out.println("main end");

    }

}