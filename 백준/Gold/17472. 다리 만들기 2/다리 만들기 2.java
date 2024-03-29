import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;


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

    static int n, m, k,is_num;
    static StringBuilder sb;
    static int[][] board;
    static int[][] delta = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] isvisited;
    static ArrayList<Node> nodes;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        is_num = make_island_num();

        nodes = new ArrayList<>();
        make_bridge();

        nodes.sort(Comparator.comparingInt(n -> n.dis));
        parent = new int[is_num+1];
        for (int i = 1; i < is_num+1; i++) {
            parent[i] = i;
        }
        System.out.println(mst());
    }

    static int mst(){
        int sum = 0;
        for (Node node : nodes) {
            if (union(node.i1, node.i2)){
                sum += node.dis;
            }
        }
        for (int i = 2; i < is_num+1; i++) {
            if (find(i) != find(i-1)) return -1;
        }
        return sum;
    }

    static boolean union(int i1, int i2){
        int a = find(i1);
        int b = find(i2);
        if (a == b){
            return false;
        } else {
            parent[a] = b;
            return true;
        }
    }

    static int find(int i1){
        if (parent[i1] == i1) return i1;
        return  parent[i1] = find(parent[i1]);
    }


    private static void make_bridge() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 0) {
                    int this_num = board[i][j];
                    loop:
                    for (int d = 0; d < 4; d++) {
                        int len = 0;
                        int dy = i + delta[d][0];
                        int dx = j + delta[d][1];
                        while (true) {
                            if (!checkSize(dy,dx)) continue loop;
                            // 같은 섬이면 취소
                            if (this_num == board[dy][dx]) continue loop;
                            // 다른 섬 도착하면 나오기
                            if (board[dy][dx] != 0) break;
                            len++;
                            dy += delta[d][0];
                            dx += delta[d][1];
                        }
                        if (len > 1) {
                            nodes.add(new Node(this_num, board[dy][dx], len));
                        }
                    }
                }
            }
        }
    }

    static int make_island_num() {
        isvisited = new boolean[n][m];
        int number = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
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
        return 0 <= dy && dy < n && 0 <= dx && dx < m;
    }
}


//8 8
//1 1 1 1 1 1 1 1
//1 0 0 0 0 0 0 1
//1 0 0 0 0 0 0 1
//1 0 0 1 1 0 0 1
//1 0 0 0 0 0 0 1
//1 0 0 0 0 0 0 1
//1 0 0 0 0 0 0 1
//1 1 1 1 1 1 1 1
