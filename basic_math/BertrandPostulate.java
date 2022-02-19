package basic_math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class BertrandPostulate {

    public static boolean isPrimeNum(int num) {

        if (num == 1) {
            return false;
        } else if(num <= 3) {
            return true;
        } else {

            for (int i = 2; i*i <= num; i++) {
                
                if(num%i == 0) {
                    return false;
                }

            }

            return true;

        }


    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num;

        List<Integer> list = new ArrayList<Integer>();


        while ((num = Integer.parseInt(br.readLine()))!=0) {

            int primeNumCount = 0;

            if (num == 1) {
                primeNumCount++;
            }

            for (int i = 1; i < num; i++) {

                if (isPrimeNum(num+i)) {
                    primeNumCount ++;
                }
                

            }

            list.add(primeNumCount);

        }

        for (int i : list) {

            System.out.println(i);

        }





    }
    
}
