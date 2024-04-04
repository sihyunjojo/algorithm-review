import java.io.*;
import java.util.*;

public class Main {
    static int n, end;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] list;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
//        System.out.println();
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            arr = new int[n + 1];
            for (int j = 1; j < n + 1; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            list = new ArrayList[n + 1];
            int[] condition_make = new int[n + 1];
            for (int j = 0; j < n + 1; j++) {
                list[j] = new ArrayList<>();
            }

            int[] dis = new int[n + 1];
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list[from].add(to);
                dis[to]++;
            }

            end = Integer.parseInt(br.readLine());

            Queue<int[]> q = new ArrayDeque<>();

            for (int j = 1; j < n+1; j++) {
                if (dis[j] == 0){
                    q.add(new int[] {j});
                }
            }

            int[] dp = new int[n+1];
            boolean[] isvisited = new boolean[n+1];

            while (!q.isEmpty()){
                int[] now = q.poll();
                int now_point = now[0];

//                if (isvisited[now_point]) continue;
//                isvisited[now_point] = true;

                for (int next : list[now_point]) {
                    if (dp[next] >= arr[now_point] + dp[now_point]) continue;
                    dp[next] = arr[now_point] + dp[now_point];
                    q.add(new int[] {next});
                }
            }

            sb.append(dp[end]+arr[end]).append("\n");
        }
        System.out.println(sb);
    }
}