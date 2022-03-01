package recursion;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Star {

    public static String star(int num) {
        
        if (num == 3) {
            
            return "*".repeat(num) + "\n" + "*" + " ".repeat(num/3)
             + "*" + "\n".repeat(num);
        
        } else {

            return star(num/3).repeat(num/3);

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());

        System.out.println(star(number));


    }
    
}
