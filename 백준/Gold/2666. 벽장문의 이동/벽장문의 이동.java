import java.io.*;
import java.util.*;

public class Main {
    static int a, b, n, m, res;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());


        res = Integer.MAX_VALUE;

        m = Integer.parseInt(br.readLine());
        arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dfs(a, b, 0, 0);

        System.out.println(res);
    }

    static void dfs(int a, int b, int cnt, int ans) {
        if (cnt == m) {
            if (res > ans) res = ans;
            return;
        }
        dfs(arr[cnt], b, cnt + 1, Math.abs(arr[cnt] - a) + ans);
        dfs(a, arr[cnt], cnt + 1, Math.abs(arr[cnt] - b) + ans);
    }
}