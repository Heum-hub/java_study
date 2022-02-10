package future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;
import java.util.concurrent.Callable;


// Future + CompletionStage(비동기 연산 Step을 제공해서 계속 체이닝 형태로 조합 가능, 결과 값 조합 및 에러 처리)
// 직접 스레드를 생성하지 않고 async로 작업 처리, 여러 Future 병렬 처리
public class CompletableFutureCombined {

    // thenCombine -> 여러 CompletableFuture를 병렬로 처리, 모든 처리가 완료되고 그 결과를 하나로 합침

    public static void main(String[] args) {
        
        // supplyAsync()에서 풀을 할당한 후 후속 작업 진행(할당안하면 ForkJoinPool 할당)
        // Async 없을 경우 -> 처음 진행한 스레드가 이어서 함
        // Async 있고 스레드 풀 할당 안할 경우 -> ForkJoinPool 생성해서 새로운 스레드에서 처리
        // Async 있고 스레드 풀 할당 할 경우 -> 동일 스레드 풀의 새로운 스레드를 생성하여 처리
        CompletableFuture<String> future1 = CompletableFuture
        .supplyAsync(() -> "Future1")
        .thenApply(s -> {
            try {
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName());
                Thread.sleep(5000);
                System.out.println("Starting future1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "!";
        });

        
        CompletableFuture<String> future2 = CompletableFuture
        .supplyAsync(() -> "Future2")
        .thenApply(s -> {
            try {
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName());
                Thread.sleep(4000);
                System.out.println("Starting future2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "!";
        });

        CompletableFuture<Object> combinedFuture = future1.thenCombine(future2, (s1, s2) -> s1 + " + " + s2)
        .thenApply(s -> { return s; } );

        // combined chaining
        try {
            System.out.println(combinedFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}