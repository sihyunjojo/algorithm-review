import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static int res,n,m,tmp_res;
    static StringBuilder sb;
    static char[][] board;
    static int[] blue, red, first, second, tmp_dirs,init_red,init_blue;
    static boolean isRedFirst;
    static int[][] delta = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        blue = new int[3];
        init_blue = new int[3];
        red = new int[3];
        init_red = new int[3];
        first = new int[3];
        second = new int[3];
        tmp_dirs = new int[10];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'B'){
                    blue[0] = i;
                    blue[1] = j;
                    // 추후에 순서에 따라서 나눌때 파란색 체크하기 위해서
                    blue[2] = 0;
                    init_blue[0] = i;
                    init_blue[1] = j;
                    init_blue[2] = 0;

                }
                if (board[i][j] == 'R'){
                    red[0] = i;
                    red[1] = j;
                    red[2] = 1;
                    init_red[0] = i;
                    init_red[1] = j;
                    init_red[2] = 1;

                }
            }
        }

        res = 11;
        combi(0);
        if (res == 11) System.out.println(-1);
        else System.out.println(res);
    }

    private static void combi(int count) {
        if (count == 10) {
            red[0] = init_red[0];
            red[1] = init_red[1];
            blue[0] = init_blue[0];
            blue[1] = init_blue[1];
            //[2, 1, 3, 1, 2]
//            System.out.println(Arrays.toString(tmp_dirs));
            go();
            return;
        }

        for (int i = 0; i < 4; i++) {
            tmp_dirs[count] = i;
            combi(count+1);
        }

    }

    private static void go() {
        tmp_res = 100;
        int[] target = {2, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        for (int i = 0; i < 10; i++) {
            whosFirst(tmp_dirs[i]);
            // 파란색이 들어가거나, 안되는 상황이면 false를 넘겨주자.
            if(!moveBalls(i)) return;
            if (res <= i) return;
//            if (same(tmp_dirs,target)){
//                System.out.println("true");
//            }
        }
    }

    private static void whosFirst(int dir) {
        switch (dir){
            case 0:
                if (red[0] > blue[0]) isRedFirst = false;
                else  isRedFirst = true;
                break;
            case 1:
                if (red[0] < blue[0]) isRedFirst = false;
                else  isRedFirst = true;
                break;
            case 2:
                if (red[1] < blue[1]) isRedFirst = true;
                else  isRedFirst = false;
                break;
            case 3:
                if (red[1] > blue[1]) isRedFirst = true;
                else  isRedFirst = false;
                break;
        }
        if (isRedFirst) {
            first = red;
            second = blue;
        } else {
            first = blue;
            second = red;
        }
    }


    private static boolean moveBalls(int index) {
        int[] target = {2, 3, 3, 3, 3, 3, 3, 3, 3, 3};

        if (!moveBall(index, first)) return false;
        if (!moveBall(index, second)) return false;
        // 별일 없이 일 마침.

        if (res > tmp_res) res = tmp_res;
        return true;
    }

    private static boolean moveBall(int index, int[] ball) {
        int[] target = {2, 3, 3, 3, 3, 3, 3, 3, 3, 3};
//        System.out.println(Arrays.toString(target));
        int dir = tmp_dirs[index];

        int ball_y = ball[0];
        int ball_x = ball[1];
        int ball_color = ball[2];
        while (true){
            ball_y += delta[dir][0];
            ball_x += delta[dir][1];

//            if (same(tmp_dirs,target)){
//                System.out.println(ball_y + " "+ ball_x + " " + Arrays.toString(ball));
//            }

            if(ball[2] == 0 && board[ball_y][ball_x] == 'O'){
//                System.out.println("false");
                return false;
            }

            if(ball == second && first[0] == ball_y && first[1] == ball_x){
                ball_y -= delta[dir][0];
                ball_x -= delta[dir][1];
                ball[0] = ball_y;
                ball[1] = ball_x;
                break;
            }
            if (board[ball_y][ball_x] == '#'){
                ball_y -= delta[dir][0];
                ball_x -= delta[dir][1];
                ball[0] = ball_y;
                ball[1] = ball_x;
                break;
            }

            if(ball[2] == 1 && board[ball_y][ball_x] == 'O'){
//                System.out.println(Arrays.toString(tmp_dirs));
                tmp_res = index+1;
                ball[0] = ball_y;
                ball[1] = ball_x;
                return true;
            }

        }
//        if (same(tmp_dirs,target)){
//            System.out.println("true");
//        }
        return true;
    }
    private static boolean same(int[] a, int[] b){
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}