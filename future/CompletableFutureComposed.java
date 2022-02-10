package future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;
import java.util.concurrent.Callable;


// Future + CompletionStage(비동기 연산 Step을 제공해서 계속 체이닝 형태로 조합 가능, 결과 값 조합 및 에러 처리)
// 직접 스레드를 생성하지 않고 async로 작업 처리, 여러 Future 병렬 처리
public class CompletableFutureComposed {

    public static void main(String[] args) {
        
        // thenCompose -> 여러 CompletableFuture 객체를 하나의 객체처럼 Chaining
        CompletableFuture<String> composedFuture = CompletableFuture
        .supplyAsync(() -> "Hello")
        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"))
        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Java"));

        // compose chaininig
        try {
            System.out.println(composedFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}