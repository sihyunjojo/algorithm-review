import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] isvisited, isfisish;
    static ArrayList<Integer> tmp_list;
    static int res;
    static int[] partners;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            res = 0;
            partners = new int[n + 1];
            isvisited = new boolean[n + 1];
            isfisish = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                partners[i] = Integer.parseInt(st.nextToken());
                if (i == partners[i]) {
                    isvisited[i] = true;
                    res++;
                }
            }

            for (int i = 1; i <= n; i++) {
                dfs(i);
            }

            sb.append(n - res).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int a) {
        if (isvisited[a]) return;
        if (isfisish[a]) {
            isvisited[a] = true;
            res++;
        }
        isfisish[a] = true;
        dfs(partners[a]);
        isfisish[a] = false;

        // 사이클이 아닌놈들 검사 끝났으니 이제 여기 들어오는 애들은 할 수 없음.
        isvisited[a] = true;
    }
}