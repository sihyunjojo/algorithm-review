import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main{
//    static int res;
    static StringBuilder sb;
    static int r,c,res,time;
    static int[][] board;
    static boolean[][] isvisited;
    static boolean[][] v;
    static int[] start;
    static int[][] delta = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    static ArrayDeque<int[]> q_water = new ArrayDeque<>();
    static ArrayDeque<int[]> q_go = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        start = new int[2];

        board = new int[r][c];
        isvisited = new boolean[r][c];
        v = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (charArray[j] == 'D') {
                    board[i][j] = 2;
                    isvisited[i][j] = true;
                }
                else if (charArray[j] == '.') board[i][j] = 2000;
                else if (charArray[j] == 'S') {
                    board[i][j] = 1;
                    q_go.add(new int[]{i, j});
                    v[i][j] = true;
                }
                else if (charArray[j] == '*'){
                    board[i][j] = 1000;
                    q_water.add(new int[] {i,j});
                    isvisited[i][j] = true;
                }
                else {
                    isvisited[i][j] = true;
                }
            }
        }

        bfs_water();

       
        time = 0;
        while (true) {
            time++;
            if (bfs_go()) {
                System.out.println(time);
                break;
            }
            if (q_go.isEmpty()){
                System.out.println("KAKTUS");
                break;
            }
        }


    }
    static boolean bfs_go(){
        ArrayDeque<int[]> q = new ArrayDeque<>();

        while (!q_go.isEmpty()) {
            int[] poll = q_go.poll();
            int y = poll[0];
            int x = poll[1];


            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (!checkSize(dy, dx) || v[dy][dx]) continue;
                if (board[dy][dx] == 2) return true;
                if (board[dy][dx] > time + 1000) {
                    q.add(new int[]{dy, dx});
                    v[dy][dx] = true;
                }
            }
        }
        q_go = q;
        return false;
    }

    static void bfs_water(){
        while (!q_water.isEmpty()) {
            int[] poll = q_water.poll();
            int y = poll[0];
            int x = poll[1];


            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (!checkSize(dy, dx) || isvisited[dy][dx]) continue;
                if (board[dy][dx] == 2000) {
                    board[dy][dx] = board[y][x] + 1;
                    q_water.add(new int[]{dy, dx});
                    isvisited[dy][dx] = true;
                }
            }
        }
    }

    static boolean checkSize(int y, int x){
        return 0 <= y && y < r && 0<= x && x < c;
    }

}