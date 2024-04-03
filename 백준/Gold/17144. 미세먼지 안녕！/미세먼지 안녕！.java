import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main{
    static int res,r,c,t;
    static StringBuilder sb;
    static int[][] board,temp_board;
    static int[][] wind = new int[2][1];
    static int[][] delta = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        int index = 0;
        board = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1){
                    wind[index++][0] = i;
                }
            }
        }

        for (int i = 0; i < t; i++) {
            go();
        }
        System.out.println(countDuty()+2);
    }

    private static void go() {
        spread();
//        printBoard();
        wind();
//        printBoard();
    }

    private static void wind() {
        for (int i = wind[0][0]; i > 0; i--) {
            board[i][0] = board[i-1][0];
        }
        board[0][0] = board[0][1];
        board[wind[0][0]][0] = -1;

        for (int i = 1; i < c-1; i++) {
            board[0][i] = board[0][i+1];
        }
        board[0][c-1] = board[1][c-1];

        for (int i = 1; i < wind[0][0]; i++) {
            board[i][c-1] = board[i+1][c-1];
        }

        board[wind[0][0]][c-1] = board[wind[0][0]][c-2];
        for (int i = c-1; i > 1; i--) {
            board[wind[0][0]][i] = board[wind[0][0]][i-1];
        }
        board[wind[0][0]][1] = 0;

        // 아래
        for (int i = wind[1][0]; i < r-1; i++) {
            board[i][0] = board[i+1][0];
        }
        board[r-1][0] = board[r-1][1];
        board[wind[1][0]][0] = -1;

        for (int i = 0; i < c-1; i++) {
            board[r-1][i] = board[r-1][i+1];
        }
        board[r-1][c-2] = board[r-1][c-1];

        for (int i = r-1; i > wind[1][0]; i--) {
            board[i][c-1] = board[i-1][c-1];
        }

        board[wind[1][0]][c-1] = board[wind[1][0]][c-2];

        for (int i = c-2; i > 1; i--) {
            board[wind[1][0]][i] = board[wind[1][0]][i-1];
        }
        board[wind[1][0]][1] = 0;

    }

    private static void spread() {
        temp_board = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 0) continue;
                spreadDuty(i,j);
            }
        }
        board = temp_board;
    }

    private static void spreadDuty(int y, int x){
        int cnt = board[y][x];
        int spread_duty_cnt = board[y][x]/5;

        for (int d = 0; d < 4; d++) {
            int dy = y + delta[d][0];
            int dx = x + delta[d][1];
            if (!check_size(dy,dx)) continue;
            if (board[dy][dx] == -1) continue;
            temp_board[dy][dx] += spread_duty_cnt;
            cnt -= spread_duty_cnt;
        }

        temp_board[y][x] += cnt;
    }

    static boolean check_size(int y, int x){
        return 0 <= y && y < r && 0 <= x && x < c;
//         && !(y == wind[0][0] && x == 0) && !(y == wind[1][0] && x == 0)
    }

    static void printBoard(){
        for (int[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
    }

    static int countDuty(){
        int cnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cnt += board[i][j];
            }
        }
        return cnt;
    }
}