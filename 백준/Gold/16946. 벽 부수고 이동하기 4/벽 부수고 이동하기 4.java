import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 하... sb 안써서 ... 안된거 같아
public class Main {
    static int n, m, k;
    static StringBuilder sb = new StringBuilder();
    static int[][] board;
    static int[][] delta = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] isvisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        isvisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0 && !isvisited[i][j]){
                    blackBfs(i,j);
                }
            }
        }

        for (int[] ints : board) {
            for (int anInt : ints) {
                sb.append(anInt%10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void blackBfs(int sy, int sx) {
        int cnt = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sy,sx});
        isvisited[sy][sx] = true;

        HashSet<Integer> list1 = new HashSet<>();

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];

            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (!checkSize(dy, dx)) continue;
                if (isvisited[dy][dx]) continue;
                if (board[dy][dx] == 0) {
                    q.add(new int[]{dy, dx});
                    isvisited[dy][dx] = true;
                    cnt++;
                }
                if (board[dy][dx] != 0){
                    list1.add(dy*1000+dx);
                }
            }
        }

        for (int n : list1) {
            board[n / 1000][n % 1000] += cnt;
        }
    }


    private static boolean checkSize(int dy, int dx) {
        return 0 <= dy && dy < n && 0 <= dx && dx < m;
    }

}

