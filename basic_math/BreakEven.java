package basic_math;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BreakEven {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");

        int fixedCost = Integer.parseInt(arr[0]);
        int perNoteCost = Integer.parseInt(arr[1]);
        int notePrice = Integer.parseInt(arr[2]);

        int noteSale = 0;

        //Division by Zero도 여기서 처리
        if (notePrice <= perNoteCost && fixedCost >= 0) {
            
            System.out.println(-1);
        
        } else {
            
            //for문말고 수학으로 풀어낼 수 있는지부터 검토!
            noteSale = fixedCost/(notePrice - perNoteCost);

            System.out.println(noteSale+1);        
        
        }

    }
    
}
