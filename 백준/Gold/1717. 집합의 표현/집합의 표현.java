import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//  유파 경로 압축
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switch (command){
                case 0:
                    union(a,b);
                    break;
                default:
                    System.out.println(checkUnion(a,b));
                    break;
            }
        }

    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (checkUnion(a,b).equals("YES")) return;
        parent[a] = b;

    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);

    }

    private static String checkUnion(int a, int b) {
        if (find(a) == find(b)) return "YES";
        return "NO";
    }
}


