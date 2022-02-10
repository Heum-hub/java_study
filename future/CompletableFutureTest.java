package future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;
import java.util.concurrent.Callable;


// Future + CompletionStage(비동기 연산 Step을 제공해서 계속 체이닝 형태로 조합 가능, 결과 값 조합 및 에러 처리)
// 직접 스레드를 생성하지 않고 async로 작업 처리, 여러 Future 병렬 처리
public class CompletableFutureTest {


    public static void main(String[] args) {

        // Future객체 생성
        CompletableFuture<Integer> future = new CompletableFuture<>();
        // 스레드 풀 생성
        ExecutorService executor = Executors.newCachedThreadPool();

        executor.submit(() -> {

            System.out.println(LocalTime.now() + " Doing something");
            Integer sum = 1 + 1;
            Thread.sleep(3000);
            // 다른 스레드로 전달할 데이터 저장
            future.complete(sum);
            
            return null;
        
        });

        try{ 
        
            System.out.println(LocalTime.now() + " Waiting the task done");
            // 일정 시간 내에 응답이 없으면 리턴하여 다음 작업 처리하도록 할 수 있음
            // Timeout Exception 발생 예외 처리 필요
            Integer result = null;
            
            try {
                result = future.get(4000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                System.out.println(LocalTime.now() + " Time out");
                result = 0;
            }

            System.out.println(LocalTime.now() + " Result : " + result);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}