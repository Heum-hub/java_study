package future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;
import java.util.concurrent.Callable;


// Future + CompletionStage(비동기 연산 Step을 제공해서 계속 체이닝 형태로 조합 가능, 결과 값 조합 및 에러 처리)
// 직접 스레드를 생성하지 않고 async로 작업 처리, 여러 Future 병렬 처리
public class CompletableFutureChaining {    

    public static void main(String[] args) {
        
        // 일반적인 체이닝 예시(supply = Callable, run = Runnable)
        // Executor 명시 안해도 ForkJoinPool.commonPool()을 사용 -> get()시 자동으로 스레드 닫음
        // 다른 Executor를 넘길려면 각 메서드의 2번째 인자로 넘겨야 함
        CompletableFuture<Object> chainedFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return "Hello";
        // thenApply -> 리턴 값을 인자로 받아서 리턴 값이 있는 다른 작업 수행
        }).thenApplyAsync(s -> {
            System.out.println(String.format("thenApplyAsync : %s", s));
            return s + " World";
        // thenAccept -> 리턴 값을 인자로 받아서 리턴 값이 없는 다른 작업 수행
        }).thenAcceptAsync(s -> {
            System.out.println(String.format("thenAccept: %s", s));
            throw new RuntimeException();
        // handle로 예외 처리
        }).handle((s, t) -> s != null ? s : "CANCEL ERROR!");


        // chained future
        try {
            System.out.println(chainedFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}