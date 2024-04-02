import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main {
    static int res;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 2];
        long[] dp2 = new long[n + 2];
        long mod = 1000000007;
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;

        for (int i = 3; i < n + 1; i++) {
            dp2[i] = (dp[i - 3] + dp2[i - 1]) % mod;
            dp[i] = (dp[i - 1] * 2 + dp[i - 2] * 3 + dp2[i] * 2) % mod;
        }

        System.out.println(dp[n]);
    }

}