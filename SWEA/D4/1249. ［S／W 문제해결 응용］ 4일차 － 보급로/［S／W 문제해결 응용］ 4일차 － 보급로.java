import java.io.*;
import java.util.*;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[][] delta = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            sb.append("#").append(t).append(" ");

            n = Integer.parseInt(br.readLine());

            int[][] board = new int[n][n];
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < n; j++) {
                    board[i][j] = s.charAt(j) - '0';
                }
            }

            int[][] distance = new int[n][n];
            boolean[][] isvisited = new boolean[n][n];

//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if(i == 0 & j == 0) continue;
//                    distance[i][j] = Integer.MAX_VALUE;
//                }
//            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            pq.offer(new int[]{0, 0, 0});
            isvisited[0][0] = true;
            
            
            loop:
            while (!pq.isEmpty()) {
                int[] poll = pq.poll();
                int value = poll[0]; // distance[y][x]
                int y = poll[1];
                int x = poll[2];

//                if (isvisited[y][x]) continue;
//                isvisited[y][x] = true;

                for (int d = 0; d < 4; d++) {

                    int dy = y + delta[d][0];
                    int dx = x + delta[d][1];
                    if (!checkSize(dy, dx)) continue;
                    if (isvisited[dy][dx]) continue;
                    isvisited[dy][dx] = true;
//                    if (distance[dy][dx] <= distance[y][x] + board[dy][dx]) continue;
//                    distance[dy][dx] = distance[y][x] + board[dy][dx];
                    pq.offer(new int[]{value+board[dy][dx], dy, dx});

                    if (dy == n - 1 && dx == n - 1) {
                        sb.append(value).append("\n");
                        break loop;
                    }
                }
            }
        }
        System.out.println(sb);
    }

    static boolean checkSize(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}