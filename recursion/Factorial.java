package recursion;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Factorial {

    public static int factorial(int num) {
        
        if (num != 0) {
            return num * factorial(num-1); 
        } else {
            return 1;
        }
        
        /*
        int init = 1;

        for (int i = 1; i <= num; i++) {

            init *= i;

        }

        return init;
        */

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int number = Integer.parseInt(br.readLine());

        System.out.println(factorial(number));
        
    }



}