import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int n, k, result;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[2][k + 1];// 중요도 | 공부시간
        for (int i = 1; i < k + 1; i++) {
            st = new StringTokenizer(br.readLine());
            arr[0][i] = Integer.parseInt(st.nextToken()); // 중요도
            arr[1][i] = Integer.parseInt(st.nextToken()); // 공부시간
        }

        // knapsack
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i < k + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (j >= arr[1][i]) {
                    dp[i][j] = Math.max(arr[0][i] + dp[i - 1][j - arr[1][i]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[k][n]);
    }
}