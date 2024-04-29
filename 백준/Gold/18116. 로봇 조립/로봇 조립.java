import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


// union find의 개수를 세리는 문제

public class Main {
    static int res;
    static StringBuilder sb = new StringBuilder();
    static int[] parent, cnts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        final int MAX = 1000000;
        parent = new int[MAX + 1];
        for (int i = 0; i < MAX+1; i++) {
            parent[i] = i;
        }
        cnts = new int[MAX + 1];
        Arrays.fill(cnts, 1);
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("I")) {
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                union(n1, n2);
            } else {
                int num = Integer.parseInt(st.nextToken());
                sb.append(cnts[find(num)]).append("\n");
//                System.out.println(cnts[find(num)]);
            }
        }
        System.out.println(sb);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        if (a < b) {
            parent[b] = a;
            cnts[a] += cnts[b];
        } else {
            parent[a] = b;
            cnts[b] += cnts[a];
        }
    }

    static int find(int a) {
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}