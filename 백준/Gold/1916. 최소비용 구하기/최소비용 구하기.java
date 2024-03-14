import javax.swing.text.html.ListView;
import java.awt.desktop.QuitEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int res,m,n;
    static int[] dp;
    static int MAX_NUM = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        ArrayList<int[]>[] arr = new ArrayList[n+1];

        for (int i = 0; i < n+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[from].add(new int[] {to,w});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dis = new int[n+1];
        boolean[] isvisited = new boolean[n+1];

        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.offer(new int[] {start, 0});

        while (!pq.isEmpty()){
            int[] poll = pq.poll();

            int now_point = poll[0];
            int now_dis = poll[1];

            if(isvisited[now_point]) continue;
            isvisited[now_point] = true;

            for (int[] next : arr[now_point]) {
                int next_point = next[0];
                int next_dis = next[1];

                if (dis[next_point] <= dis[now_point] + next_dis)  continue;
                if (isvisited[next_point]) continue;

                dis[next_point] = dis[now_point] + next_dis;
                pq.add(new int[] {next_point, dis[next_point]});
            }

        }

        System.out.println(dis[end]);
    }
}

