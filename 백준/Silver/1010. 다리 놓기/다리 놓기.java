import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp = new int[30][30];
    static int result, n, m;
   public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        

        StringTokenizer st;
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            
            // result = 0;        
            // dfs(0,0);
            // sb.append(result).append("/n");

            sb.append(combi(m,n)).append(("\n"));
        }
        System.out.println(sb);
    }

    static int combi(int n, int r){
        
		if(dp[n][r] > 0) {
			return dp[n][r]; 
		}
		
		if(n == r || r == 0) {
			return dp[n][r] = 1;
		}
		
		return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
    }
    static void dfs(int start, int cnt){
        if (cnt == n){
            result += 1;
            return;
        }

        for (int i = start; i < m; i++) {
            dfs(i + 1, cnt + 1);
        }
        

    }

    
}