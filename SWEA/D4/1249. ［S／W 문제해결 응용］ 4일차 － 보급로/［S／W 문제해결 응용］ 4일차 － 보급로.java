import java.io.*;
import java.util.*;

public class Solution {
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[][] delta = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

            boolean[][] isvisited = new boolean[n][n];

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(0,0,0));
            isvisited[0][0] = true;


            loop:
            while (!pq.isEmpty()) {
                Node now = pq.poll();

                for (int d = 0; d < 4; d++) {
                    int y = now.y + delta[d][0];
                    int x = now.x + delta[d][1];
                    if (!checkSize(y, x)) continue;
                    if (isvisited[y][x]) continue;
                    isvisited[y][x] = true;
                    pq.add(new Node(x,y,board[y][x] + now.cost));

                    if (y == n - 1 && x == n - 1) {
                        sb.append(now.cost).append("\n");
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