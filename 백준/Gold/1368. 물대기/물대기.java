import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        Queue<int[]> list = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int w = Integer.parseInt(st.nextToken());
                if (i == j) list.offer(new int[]{0, j, arr[i]});
                if (i < j) list.offer(new int[]{i, j, w});
            }
        }

        int costs = 0;
        int cnt = 0;
        
//        for (int j = 0; j < list.size(); j++) {
        while (!list.isEmpty()) {
            int[] poll = list.poll();
            int n1 = poll[0];
            int n2 = poll[1];
            int cost = poll[2];

            if (union(n1, n2)) {
                costs += cost;
                if (++cnt == n) {
                    System.out.println(costs);
                    break;
                }
            }
        }

    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

}