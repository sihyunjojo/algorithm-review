import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main {
    static int res,n,m;
    static StringBuilder sb;
    static char[][] board;
    static int[] start;
    static int[][] delta = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        start = new int[2];
        res = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '0') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        bfs();
        if (res == Integer.MAX_VALUE) res = -1;
        System.out.println(res);
    }
    static void bfs(){
        boolean[][][] isvisited = new boolean[64][n][m];
        Queue<int[]> q = new ArrayDeque<>();
        // 0 은 열쇄 bitmask로 나타내자. 0000000
        // 그 다음은 이동 거리
        // 보드위치
        q.add(new int[] {start[0], start[1], 0, 1});
        isvisited[0][start[0]][start[1]] = true;

        loop:
        while(!q.isEmpty()){
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            int key = poll[2];
            int dis = poll[3];


            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (!check_size(dy,dx)) continue;
                if (isvisited[key][dy][dx]) continue;
                if (board[dy][dx] == '1'){
                    res = dis;
                    break loop;
                }

                if (board[dy][dx] == '.' || board[dy][dx] == '0'){
                    isvisited[key][dy][dx] = true;
                    q.add(new int[] {dy, dx, key, dis+1});
                } else if (board[dy][dx] == '#') {
                    continue;
                } // 열쇠
                else if ((int)board[dy][dx] >= 97){
                    if (!isKey(key,board[dy][dx])) {
                        int key_num = 1 << (board[dy][dx] - 97);
                        // System.out.println(key_num);
                        q.add(new int[]{dy, dx, key + key_num, dis + 1});
                        isvisited[key + key_num][dy][dx] = true;
                    } else {
                        q.add(new int[]{dy,dx,key,dis+1});
                        isvisited[key][dy][dx] = true;
                    }
                }
                // 문
                else {
                    if (isKey(key,board[dy][dx])) {
                        isvisited[key][dy][dx] = true;
                        q.add(new int[]{dy, dx, key, dis + 1});
                    }
                }
            }
        }
    }

    static boolean isKey(int key, char door){
        // 문
        int door_num = door - 65;
        // 열쇄
        if (door_num < 0){
            door_num = door - 97;
        }

        return ((key >> door_num) & 1) == 1;
    }

    static boolean check_size(int y,int x){
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}