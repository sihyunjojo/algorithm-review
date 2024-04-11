import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable {
        int from, to, value;

        public Node(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", value=" + value +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            Node o1 = (Node) o;
            return value - o1.value;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static int[] parents;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        int[][] plants = new int[n][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            plants[i] = new int[]{x, y, z, i};
        }

        PriorityQueue<Node> list = new PriorityQueue<>();

        // x,y,z
        for (int i = 0; i < 3; i++) {
            int tmp = i;
            Arrays.sort(plants, Comparator.comparingInt(p -> p[tmp]));
            // 모든 경우
            for (int j = 0; j < n-1; j++) {
                list.offer(new Node(plants[j][3], plants[j + 1][3], plants[j + 1][i] - plants[j][i]));
            }
        }
        boolean[] isvisited = new boolean[n];
//        for (Node node : list) {
//            System.out.println(node);
//        }
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        int res = 0;

        while (!list.isEmpty()) {
            Node poll = list.poll();
            int from = poll.from;
            int to = poll.to;
            int value = poll.value;
            if (union(from,to)) {
                res += value;
//                System.out.println(value + " " + res);
            }
            if (isConnect()) break;
//            System.out.println(Arrays.toString(parents));
        }
        System.out.println(res);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        // 같은 부모임.
        if (a == b) return false;
        parents[a] = b;
        return true;
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean isConnect() {
        for (int i = 0; i < n-1; i++) {
            if (find(i) != find(i+1)) return false;
        }
        return true;
    }

}