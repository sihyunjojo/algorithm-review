import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;


// 10: 53 11: 11
public class Main {
    static int n, m, k;
    static StringBuilder sb;
    static char[][] board;
    static int[][] delta = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][][] isvisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        board = new char[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        isvisited = new boolean[k + 1][n][m];
        if (n==1&&m==1){
            System.out.println(1);
        } else {
            System.out.println(bfs());
        }
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, k, 1});

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            int cnt_brake = poll[2];
            int dis = poll[3];

            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (!checkSize(dy, dx)) continue;
                if (dy == n - 1 && dx == m - 1) {
                    return dis + 1;
                }
                if (isvisited[cnt_brake][dy][dx]) continue;
                if (board[dy][dx] == '1' && cnt_brake > 0) {
                    isvisited[cnt_brake - 1][dy][dx] = true;
                    isvisited[cnt_brake][dy][dx] = true;
                    q.add(new int[]{dy, dx, cnt_brake - 1, dis + 1});
                } else if (board[dy][dx] == '0') {
                    isvisited[cnt_brake][dy][dx] = true;
                    q.add(new int[]{dy, dx, cnt_brake, dis + 1});
                }
            }
        }
        return -1;
    }

    private static boolean checkSize(int dy, int dx) {
        return 0 <= dy && dy < n && 0 <= dx && dx < m;
    }
}

