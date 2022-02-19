package basic_math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Snail {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().trim().split(" ");

        double up = Integer.parseInt(arr[0]);
        double down = Integer.parseInt(arr[1]);
        double height = Integer.parseInt(arr[2]);

        double day = Math.ceil((height-up)/(up-down)) + 1;

        System.out.println((int) day);


    }
    
}
