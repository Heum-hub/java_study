package basic_math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PrimeFactorization {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        if(num == 1) {
            ;
        }
        else if(num <= 3) {
            System.out.println(num);
        } else {
            for (int i = 2; i*i <= num; i++) {

                while (num%i == 0) {
                    System.out.println(i);
                    num /= i;
                }

            }

            if (num != 1) {
                System.out.println(num);
            }

        }


        

    }
    
}
