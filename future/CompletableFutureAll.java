package future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;
import java.util.concurrent.Callable;


// Future + CompletionStage(비동기 연산 Step을 제공해서 계속 체이닝 형태로 조합 가능, 결과 값 조합 및 에러 처리)
// 직접 스레드를 생성하지 않고 async로 작업 처리, 여러 Future 병렬 처리
public class CompletableFutureAll {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();
        System.out.println(mainThread.getName());

        // 스레드 풀 생성
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // 일반적인 체이닝 예시(supply = Callable, run = Runnable)
        // Executor 명시 안해도 ForkJoinPool.commonPool()을 사용
        // 다른 Executor를 넘길려면 각 메서드의 2번째 인자로 넘겨야 함
        CompletableFuture<Object> chainedFuture = CompletableFuture
            .supplyAsync(() -> {
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName());
                System.out.println("Starting Chained future");
                return "Hello";}
                , executor
                // thenApply -> 리턴 값을 인자로 받아서 리턴 값이 있는 다른 작업 수행
            ).thenApplyAsync(s -> {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName());
                System.out.println("thenApplyAsync : Hello");
                return s + " World";
                // thenAccept -> 리턴 값을 인자로 받아서 리턴 값이 없는 다른 작업 수행
            }).thenAcceptAsync(s -> {
                System.out.println("thenAccept: Hello World");
                throw new RuntimeException();
                // handle로 예외 처리
            }).handle((s, t) -> s != null ? s : "CANCEL ERROR!");
    
        // theCompose -> 여러 CompletableFuture 객체를 하나의 객체처럼 Chaining
        CompletableFuture<String> composedFuture = CompletableFuture
            .supplyAsync(() -> {
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName());
                System.out.println("Starting Composed future");
                return "Hello";}
                , executor)
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> {
                return s + " World";}))
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> {
                return s + " Java";}));
    
        // thenCombine -> 여러 CompletableFuture를 병렬로 처리, 모든 처리가 완료되고 그 결과를 하나로 합침
        // supplyAsync()에서 풀을 할당한 후 후속 작업 진행(할당안하면 ForkJoinPool 할당)
        // Async 없을 경우 -> 처음 진행한 스레드가 이어서 함
        // Async 있고 스레드 풀 할당 안할 경우 -> ForkJoinPool 생성해서 새로운 스레드에서 처리
        // Async 있고 스레드 풀 할당 할 경우 -> 동일 스레드 풀의 새로운 스레드를 생성하여 처리
        CompletableFuture<String> future1 = CompletableFuture
            .supplyAsync(() -> "Future1", executor)
            .thenApply(s -> {
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName());
                System.out.println("Starting Combined future1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return s + "!";
            });

        CompletableFuture<String> future2 = CompletableFuture
            .supplyAsync(() -> "Future2", executor)
            .thenApply(s -> {
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName());
                System.out.println("Starting Combined future2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return s + "!";
            });
            
        CompletableFuture<Object> combinedFuture = future1.thenCombine(future2, (s1, s2) -> s1 + " + " + s2)
        .thenApply(s -> { 
            return s; } );        
        
        
        // combined chaining
        try {
            System.out.println(combinedFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // compose chaininig
        try {
            System.out.println(composedFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // chained future
        try {
            System.out.println(chainedFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }        
        
        
        /*      
        // Runnable Future
        CompletableFuture.runAsync(() -> {
            System.out.println("runAsync");
        });
        */
    }
}