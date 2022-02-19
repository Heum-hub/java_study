package basic_math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SquareMinDistance {
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");

        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[1]);
        int rightX = Integer.parseInt(arr[2]);
        int ceilY = Integer.parseInt(arr[3]);

        int distance1 = rightX - x;
        int distance2 = ceilY - y;
        int distance3 = x;
        int distance4 = y;

        System.out.println(Math.min(distance1, Math.min(distance2, Math.min(distance3, distance4))));

    }

}
