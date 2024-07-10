import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] t = new int[n];
        int[] p = new int[n];

        StringTokenizer st;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+5];
        for (int i = n-1; i >= 0; i--) {
            if (n < i + t[i]){
                dp[i] = dp[i+1];
            } else {
                dp[i] = Math.max(dp[i+1],p[i]+dp[i+t[i]]);
            }
        }
        System.out.println(dp[0]);
    }
}