import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] in = new int[n + 1];

        // n개의 문제를 전부 풀어야함.
        // 1번 문제가 가장 쉬운 문제임. -> 같은 조건일때는 숫자가 작은게 먼저 나와야함.
        ArrayList<Integer>[] list = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 진입 차수 계산
            in[to]++;
            list[from].add(to);
        }

        for (int i = 1; i < n + 1; i++) {
            list[i].sort(Comparator.naturalOrder());
        }

        Queue<Integer> q = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = 1; i < n + 1; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }

        // 위상정렬하면됨.
        while (!q.isEmpty()) {
            int now = q.poll();

            sb.append(now).append(" ");
            for (Integer i : list[now]) {
                in[i]--;
                if (in[i] == 0) q.offer(i);
            }
        }
        System.out.println(sb);
    }
}