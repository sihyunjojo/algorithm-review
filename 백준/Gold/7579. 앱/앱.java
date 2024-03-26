import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.in;
import static java.util.Comparator.naturalOrder;

public class Main {
    static int res;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[2][n+1];

        // 바이트 수
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[0][i] = Integer.parseInt(st.nextToken());
        }

        // 비용
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            arr[1][i] = Integer.parseInt(st.nextToken());
            sum += arr[1][i];
        }

        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (arr[1][i] <= j){
                    if (arr[0][i] + dp[i-1][j - arr[1][i]] > dp[i-1][j]){
                        dp[i][j] = arr[0][i] + dp[i-1][j-arr[1][i]];
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        loop:
        for (int i = 0; i <= sum; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[j][i] >= m) {
                    System.out.println(i);
                    break loop;
                }
            }
        }
    }
}