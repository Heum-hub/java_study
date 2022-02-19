package basic_math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {

    public static boolean isPrimeNum(Integer num) {

        for (int i = 2; i*i <= num; i++) {

            if(num%i == 0) {
                return false;
            }
        
        }

        if (num==1) {
            return false;
        } else if(num == 2) {
            return true;
        } else {
            return true;
        }


    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().trim().split(" ");

        Integer floor = Integer.parseInt(arr[0]);
        Integer ceil = Integer.parseInt(arr[1]);

        List<Integer> primeNumArr = new ArrayList<Integer>();

        for (int i = floor; i <= ceil; i++) {

            if(isPrimeNum(i)) {
                primeNumArr.add(i);
            }
        }

        String str = "";

        for (Integer i: primeNumArr) {

            str += i + "\n";

        }

        System.out.println(str);


    }
    
}
