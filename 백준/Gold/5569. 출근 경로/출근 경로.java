import java.io.*;
import java.util.*;

// 출근 경로
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int w,h,result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        // 어느 쪽에서 왔는지, 이전 교차로에서 꺽어서 왔는지 ,w,h
        int[][][][] dp = new int[2][2][w][h];

        // 동쪽으로 갈거고, 꺽을 수 있음
        for (int i = 0; i < w; i++) {
            dp[0][0][i][0] = 1;
        }

        // 북쪽으로 갈거고, 꺽을 수 있음
        for (int i = 0; i < h; i++) {
            dp[1][0][0][i] = 1;
        }


        for (int i = 1; i < w; i++) {
            for (int j = 1; j < h; j++){
                // 동쪽으로 갈것이고 꺽을 수 없음.
                dp[0][1][i][j] += dp[1][0][i-1][j] % 100000;
                dp[1][1][i][j] += dp[0][0][i][j-1] % 100000;

                // 동쪽으로 갈것이고 꺽을 수 있음
                dp[0][0][i][j] += (dp[0][1][i-1][j] + dp[0][0][i-1][j]) % 100000;
                dp[1][0][i][j] += (dp[1][0][i][j-1] + dp[1][1][i][j-1]) % 100000;

            }
        }
        
        int result = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result += dp[i][j][w-1][h-1];
            }
        }
        System.out.println(result % 100000);
    }
}