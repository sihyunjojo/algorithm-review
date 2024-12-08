
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n,z,m,step;
    static int[] fail_array;
    static boolean[] visitied;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        fail_array = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            fail_array[i] = Integer.parseInt(st.nextToken());
        }


        // 입력 끝
        step = 1;
        visitied = new boolean[n+1];
        while (!dfs(1)) {
            visitied = new boolean[n+1];
            step++;
        }
        System.out.println(step);

    }
    
    static boolean dfs(int now_block) {
        if (now_block == z) return true;
        if (visitied[now_block]) return false;
        for (int fail_num : fail_array) {
            if (now_block == fail_num) return false;
        }
        visitied[now_block] = true;
        return dfs(((now_block + step) % n) == 0 ? n : ((now_block + step) % n));
    }
 }