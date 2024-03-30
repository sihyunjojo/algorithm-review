import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.setOut;


// 10: 53 11: 11
public class Main {
    static class Node{
        int i1, i2, dis;

        public Node(int i1, int i2, int dis) {
            this.i1 = i1;
            this.i2 = i2;
            this.dis = dis;
        }



        @Override
        public String toString() {
            return "Node{" +
                    "i1=" + i1 +
                    ", i2=" + i2 +
                    ", dis=" + dis +
                    '}';
        }
    }

    static int n, k,is_num;
    static StringBuilder sb;
    static int[][] board;
    static int[][] delta = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] isvisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int landNum = make_island_num();

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0){
                    int answer = make_bridge(i,j);
                    if (answer < res) res = answer;
                }
            }
        }

        System.out.println(res);
    }


    private static int make_bridge(int sy, int sx) {
        int cnt = Integer.MAX_VALUE;
        isvisited = new boolean[n][n];
        int this_num = board[sy][sx];

        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sy,sx,0});
        isvisited[sy][sx] = true;

        while (!q.isEmpty()){
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            int dis = poll[2];

            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (!checkSize(dy,dx)) continue;
                if (isvisited[dy][dx]) continue;
                if (board[dy][dx] == 0){
                    q.add(new int[] {dy,dx,dis+1});
                    isvisited[dy][dx] =true;
                } else if (board[dy][dx] != this_num){
                    if (cnt > dis){
                        cnt = dis;
                    }
                }
            }
        }
        return cnt;
    }

    static int make_island_num() {
        isvisited = new boolean[n][n];
        int number = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1 && !isvisited[i][j]) {
                    this_island(i, j, ++number);
                }
            }
        }
        return number;
    }

    static void this_island(int y, int x, int n) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{y, x});
        isvisited[y][x] = true;


        while (!q.isEmpty()) {
            int[] poll = q.poll();
            y = poll[0];
            x = poll[1];
            board[y][x] = n;

            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (!checkSize(dy, dx)) continue;
                if (isvisited[dy][dx]) continue;
                if (board[dy][dx] == 1) {
                    q.add(new int[]{dy, dx});
                    isvisited[dy][dx] = true;
                }
            }
        }
    }

    private static boolean checkSize(int dy, int dx) {
        return 0 <= dy && dy < n && 0 <= dx && dx < n;
    }
}

