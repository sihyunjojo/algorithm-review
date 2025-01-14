
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n,d,result;
    static int[] a,b,c,t;
    static int[][] dp;
    static ArrayList<Integer>[] possible_cloths; 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        t = new int[d];
        a = new int[n];
        b = new int[n];
        c = new int[n];
        possible_cloths = new ArrayList[d];
        dp = new int[d][n];

        for (int i = 0; i < d; i++) {
            t[i] = Integer.parseInt(br.readLine());
            possible_cloths[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            
            for (int j = 0; j < d; j++) {
                if (a[i] <= t[j] && t[j] <= b[i]) {
                    possible_cloths[j].add(i);
                }
            }

        }

        br.close();

        solve();

        System.out.println(sb);
    }

    // 현재 입고 있는 옷 제외하면 더 빠를듯.
    static void solve(){
        for (int i = 0; i < possible_cloths[0].size(); i++) {
            // dfs(1,0,possible_cloths[0].get(i));    
        }
        for (int i = 1; i < d; i++) {
            for (int j = 0; j < possible_cloths[i].size(); j++) { // 현재
                for (int j2 = 0; j2 < possible_cloths[i-1].size(); j2++) { // 과거
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j2] + calculate(possible_cloths[i].get(j), possible_cloths[i-1].get(j2)));
                }
            }
        }
    
        for (int i = 0; i < n; i++) {
            if (result < dp[d-1][i]) {
                result = dp[d-1][i];    
            }
        }

        // for (int i = 0; i <d; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        // for (int i = 0; i <d; i++) {
        //     System.out.println(possible_cloths[i]);
        // }
        sb.append(result);
    }

    // static void dfs(int cnt, int score, int past_cloth){
    //     if (cnt == d) {
    //         if (result < score) {
    //             result = score;
    //         }
    //         return;
    //     }

    //     for (int i = 0; i < possible_cloths[cnt].size(); i++) {
    //         int next_cloth = possible_cloths[cnt].get(i);
    //         int now_score = score + calculate(past_cloth, next_cloth);
    //         dfs(cnt+1, now_score, next_cloth);
    //     }
    // }

    static int calculate(int cloth1, int cloth2) {
        return Math.abs(c[cloth1] - c[cloth2]);
    }
        
 }