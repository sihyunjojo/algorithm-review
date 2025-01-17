
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Long n = Long.valueOf(s);
        int num_cnt = s.length();

        Long min = n;
        for (int i = 0; i < num_cnt; i++) {
            min -= 9;
        }

        Long result = Long.MAX_VALUE;

        for (long i = min; i <= n; i++) {
            long tmp = i;
            long answer = i;

            while (tmp > 0) {
                answer += tmp % 10;
                tmp /= 10;
            }
            answer += tmp % 10;

            // System.out.println(i + " " + answer);

            if (answer == n) {
                result = i;
                System.out.println(result);
                return;
            }
        }

        
        System.out.println(0);

    }
 }