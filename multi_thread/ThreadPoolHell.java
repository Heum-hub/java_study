package multi_thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolHell {

    public static void main(String[] args) {

        // 스레드 풀 생성
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (int i = 0; i <= 10 ; i++) { 
            
            executor.submit(() -> {
    
                System.out.println(Thread.currentThread().getName());
                
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            });
            
            
        }

    }

}