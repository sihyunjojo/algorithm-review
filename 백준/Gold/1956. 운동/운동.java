import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int v,e,res;
    static ArrayList<int[]>[] arr;
    static boolean[] isused;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        res = Integer.MAX_VALUE;

        arr = new ArrayList[v+1];
        for (int i = 0; i < v+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[from].add(new int[] {to,w});
        }

        for (int i = 1; i < v; i++) {
            daik( i);
        }
        if (res == Integer.MAX_VALUE) res = -1;
        System.out.println(res);

    }
    static void daik(int start){
        isused = new boolean[v+1];
        int[] distance = new int[v+1];
        Arrays.fill(distance,Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int now_point = poll[0];
            int now_dis = poll[1];

            if (isused[now_point] && now_point != start) continue;
            isused[now_point] = true;

            for (int[] next : arr[now_point]) {
                int next_point = next[0];
                int next_dis = next[1];
                if (distance[next_point] > now_dis + next_dis) {
                    distance[next_point] = now_dis + next_dis;
                    pq.offer(new int[] {next_point, distance[next_point]});
                }
            }
        }

        if (res > distance[start]) res = distance[start];
    }
}