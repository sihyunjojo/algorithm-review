import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main{
    static int res;
    static StringBuilder sb;
    static int MAX = 10000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int tc = Integer.parseInt(br.readLine());

        int[] dp = new int[MAX+1];
        Arrays.fill(dp,1);
        for (int i = 2; i < MAX+1; i++) {
            dp[i] += dp[i-2];
        }
        for (int i = 3; i < MAX+1; i++) {
            dp[i] += dp[i-3];
        }

        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}