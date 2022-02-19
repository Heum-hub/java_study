package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.io.BufferedWriter;
//import java.io.OutputStreamWriter;
import java.io.IOException;

public class WordCounter {
    
    public static void main(String[] args) throws IOException {
           
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine().trim();

        //공백만 있을 시 1 출력되는 거 방지
        if (str.isBlank()) {
            System.out.println(0);
        }
        else{
            String[] stringArray = str.split(" ");
                
            System.out.println(stringArray.length);
        }

        /*BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.flush(stringArray.length);
        bw.close();*/
            
        
    }
    
}