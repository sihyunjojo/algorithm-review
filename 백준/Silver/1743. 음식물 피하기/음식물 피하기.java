import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


public class Main {
    static int[][] graph;
    static int n,m,max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        max = 0;

        // 음식물들은 근처에 있는 것끼리 뭉치게되서 큰 음식물이 된다.
        graph = new int[n][m];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[r-1][c-1] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1){
                    bfs(i,j);
                }
            }
        }
        System.out.println(max);
    }

    private static void bfs(int i, int j) {
        Deque<int[]> q = new ArrayDeque<>();
        int[][] delta = new int[][] {{1,0},{-1,0},{0,-1},{0,1}};
        q.offer(new int[]{i,j});
        graph[i][j] = 0;

        int cnt = 1;

        while (!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            
            for (int k = 0; k < 4; k++) {
                int dx = x + delta[k][0];
                int dy = y + delta[k][1];
                if (isSize(dx,dy) && graph[dx][dy] == 1){
                    graph[dx][dy] = 0;
                    q.add(new int[] {dx,dy});
                    cnt++;
                }
            }
        }

        if (max < cnt) max = cnt;
    }

    private static boolean isSize(int dx, int dy) {
        return 0 <= dx && dx < n && 0 <= dy && dy < m;
    }
}