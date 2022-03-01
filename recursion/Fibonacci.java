package recursion;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibonacci {

    public static int calculator(int num) {

        if (num <= 1) {

            return num;
        
        } else {

            return calculator(num-1) + calculator(num-2);

        }         

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int number = Integer.parseInt(br.readLine());

        System.out.println(calculator(number));



    }
    
}
