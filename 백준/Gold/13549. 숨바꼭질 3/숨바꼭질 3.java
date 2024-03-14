import java.awt.desktop.QuitEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int res,k,n;
    static int[] dp;
    static int MAX_NUM = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        dp = new int[MAX_NUM+1];
        for (int i = 0; i < MAX_NUM+1; i++) dp[i] = Integer.MAX_VALUE;

        bfs();

        res = Integer.MAX_VALUE;
        System.out.println(dp[k]);
    }

    static void bfs(){
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {n,0});

        while (!q.isEmpty()){
            int[] poll = q.poll();
//            System.out.println(poll[0]);
            if (dp[poll[0]] <= poll[1]) continue;
            dp[poll[0]] = poll[1];
//            System.out.println(poll[0]);

            int tmp_num = poll[0] * 2;
            while (cheek_size(tmp_num) && tmp_num != 0){
                q.add(new int[] {tmp_num, poll[1]});
                tmp_num *= 2;
            }

            if(cheek_size(poll[0]+1)){
                q.add(new int[] {poll[0]+1, poll[1]+1});
            }
            if(cheek_size(poll[0]-1)){
                q.add(new int[] {poll[0]-1, poll[1]+1});
            }
//            if(cheek_size(poll[0]*2)){
//                q.add(new int[] {poll[0] * 2, poll[1]});
//            }
        }
    }
    static void dfs(int x, int ans){
        if (dp[x] <= ans) return;
//        System.out.println(Arrays.toString(dp));

        dp[x] = ans;

        if (cheek_size(2*x)){
            dfs(x*2,ans);
        }

        if (cheek_size(x+1)) {
            dfs(x+1,ans+1);
        }
        if (cheek_size(x-1)){
            dfs(x-1,ans+1);
        }

    }

    static boolean cheek_size (int x){
//        System.out.println("t   " + x);
        return 0 <= x && x < MAX_NUM+1;
    }
}

