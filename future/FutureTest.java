package future;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.time.LocalTime;
// import java.util.concurrent.Callable;

// Future -> 비동기적 연산 결과 표현, 멀티 스레드 환경에서 처리된 어떤 데이터를 다른 스레드에 전달 가능
// 스레드 세이프하므로 synchronized block을 사용하지 않아도 됨
public class FutureTest {

    public static void main(String[] args) {

        // 스레드 풀을 생성하고 알아서 태스크 큐를 스케줄링 해줌
        ExecutorService executor
         = Executors.newSingleThreadExecutor();

        // static Worker worker = new Worker();

        // 리턴값이 있는 경우는 Callable, 없는 경우는 Runnable 인터페이스를 상속한 스레드 클래스 사용
        // Generics로 구현된 Future 객체는 submit() 호출 시 리턴 그러나 값이 설정되지 않음  
        Future<Integer> future = executor.submit(() -> {
    
            System.out.println(LocalTime.now() + " Starting callable");
            Integer sum = 1 + 1;
            Thread.sleep(3000);
    
            return sum;
        });


        try{ 
            System.out.println(LocalTime.now() + " Waiting the task done");
            // 결과값을 받을 때까지 기다리는 블로킹 메서드
            // Interrupted Error, Unhandled Error 발생
            Integer result = future.get();
            System.out.println(LocalTime.now() + " Result : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}

/*
class Worker implements Callable<Integer> {

    public Integer call() throws Exception {

        System.out.println(LocalTime.now() + " Starting callable");
        Integer sum = 1 + 1;
        Thread.sleep(3000);
        
        return sum;

    }

}
*/