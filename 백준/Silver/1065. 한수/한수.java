import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n <= 99) System.out.println(n);
        else {
            int cnt = 99;
            for (int i = 100; i <= n; i++) {
                if ( (i/100) + (i%10) == ((i%100)/10)*2 ){
                    cnt += 1;
                }
            }
            System.out.println(cnt);
        }
    }

    private static int parseInt(String a){
        return Integer.parseInt(a);
    }
}