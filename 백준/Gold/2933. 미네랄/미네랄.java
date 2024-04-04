import java.io.*;
import java.util.*;

public class Main{
    static int cnt,r,c;
    static StringBuilder sb = new StringBuilder();
    static char[][] board;
    static int[][] delta = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    static int[] arr;
    static boolean[][] isvisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r+1][c];
        cnt = 0;
        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'x'){
                    cnt++;
                }
            }
        }
        Arrays.fill(board[r],'x');

        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            delete(i);
            check_down();
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void check_down() {
        // 띄워진 구간이 있다 == 내려 줘야한다
        isvisited = new boolean[r+1][c];

        if (cnt != bfs(r,0)){
            loop:
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (board[i][j] == 'x'){
                        if (isvisited[i][j]) continue;
                        down(i,j);
                        break loop;
                    }
                }
            }
        }
    }

    private static void down(int i, int j) {
        int[] arr = new int[c];
        
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {i,j});
        isvisited[i][j] = true;

        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[] {i,j});


        while (!q.isEmpty()){
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            if(arr[x] < y) arr[x] = y;

            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (!checkSize(dy,dx)) continue;
                if (isvisited[dy][dx]) continue;
                if (board[dy][dx] == '.') continue;
                q.add(new int[] {dy,dx});
                list.add(new int[] {dy,dx});
                isvisited[dy][dx] = true;
            }
        }

        int tmp = 1;
        loop:
        while (true){
            for (int k = 0; k < c; k++) {
                if (arr[k] == 0) continue;
                if (board[arr[k]+tmp][k] == 'x'){
                    break loop;
                }
            }
            tmp++;
        }

        for (int[] ints : list) {
            board[ints[0]][ints[1]] = '.';
        }
        for (int[] ints : list) {
            board[ints[0]+tmp-1][ints[1]] = 'x';
        }
    }

    private static int bfs(int i, int j){

        int cnt = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {i,j});
        isvisited[i][j] = true;

        while (!q.isEmpty()){
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            cnt++;

            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (!checkSize(dy,dx)) continue;
                if (isvisited[dy][dx]) continue;
                if (board[dy][dx] == '.') continue;

                q.add(new int[] {dy,dx});
                isvisited[dy][dx] = true;
            }
        }
        return cnt-c;
    }

    static boolean checkSize(int y, int x){
        return 0 <= y && y < r+1 && 0 <= x && x < c;
    }

    private static void delete(int i) {
        // 오른쪽에서
        int shoot = r - arr[i];
        if(i % 2 == 0){
            for (int j = 0; j < c; j++) {
                if (board[shoot][j] == 'x'){
                    board[shoot][j] = '.';
                    cnt--;
                    break;
                }
            }
        }
        // 왼쪽에서
        else {
            for (int j = c-1; j >= 0; j--) {
                if (board[shoot][j] == 'x'){
                    board[shoot][j] = '.';
                    cnt--;
                    break;
                }
            }
        }
    }

    static void printBoard(){
        for (char[] chars : board) {
            System.out.println(chars);
        }
    }

}