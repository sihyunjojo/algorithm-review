import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int res;
    static int[] temp_list;
    static int[][] init_board, board;
    static boolean[][] isvisited;
    static int n, m, tmp_cnt;
    static ArrayList<Integer> black_list;
    static int[][] delta = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        init_board = new int[n][m];
        isvisited = new boolean[n][m];
        temp_list = new int[2];

        black_list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                init_board[i][j] = Integer.parseInt(st.nextToken());
                if (init_board[i][j] == 0) {
                    black_list.add(i * m + j);
                }
            }
        }

        res = 0;
        board = new int[n][m];
        combi(0, 0);

        System.out.println(res);
    }

    static void combi(int start, int count) {
        if (count == 2) {
            back_board();
            isvisited = new boolean[n][m];

            for (int rock : temp_list) {
                int y = rock / m;
                int x = rock % m;

                board[y][x] = 1;
                isvisited[y][x] = true;
            }

//            int cnt = 0;
//            isvisited = new boolean[n][m];

//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (board[i][j] == 2 && !isvisited[i][j]) {
//                        isvisited[i][j] = true;
//                        // 성공하면
//                        if (delete_rock_dfs(i,j,1)){
//                            cnt += tmp_cnt;
//                            return;
//                        }
//                        // 실패하면
//                        isvisited[i][j] = false;
//                    }
//                }
//            }
//            delete_rock_dfs(0,0,1);

            delete_rock();
            return;
        }
        for (int i = start; i < black_list.size(); i++) {
            temp_list[count] = black_list.get(i);
            combi(i + 1, count + 1);
        }
    }

    static boolean delete_rock_dfs(int y, int x, int count) {
        for (int d = 0; d < 4; d++) {
            int dy = y + delta[d][0];
            int dx = x + delta[d][1];
            if (check_size(dy, dx)) {
                if (board[dy][dx] == 0) {
                    return false;
                }
                if (board[dy][dx] == 2 && !isvisited[dy][dx]) {
                    isvisited[dy][dx] = true;
                    if (tmp_cnt < count) tmp_cnt = count + 1;
                    delete_rock_dfs(dy, dx, count++);
                    isvisited[dy][dx] = false;
                }
            }
        }
        return true;
    }

    // 돌 2개 넣어주고
    // 2 돌 하나 찾아서
    // 그 돌 리스트에 두고
    // 1 또는 2 있으면 패스 // 2가 있으면 리스트에 넣고 + 큐에 추가
    // 0이 하나라도 있으면 브레이크 안됨.
    static void delete_rock() {
        int count = 0;
        int y = 0;
        int x = 0;
//        for (int rock : temp_list) {
//            y = rock / m;
//            x = rock % m;
//
//            board[y][x] = 1;
//        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//        System.out.println(
//                "temp " + Arrays.toString(temp_list));

        loop:
        for (int rock : temp_list) {
            int i = rock / m;
            int j = rock % m;

//            if(board[i][j] == 2 && !isvisited[i][j]){
//            int tmp_count = count;

//            Queue<int[]> q = new ArrayDeque<>();
//                Queue<int[]> aq = new ArrayDeque<>();
            for (int d = 0; d < 4; d++) {
                int dy = i + delta[d][0];
                int dx = j + delta[d][1];
//                System.out.println(dy + " " + dx);
//                for (boolean[] booleans : isvisited) {
//                    System.out.println(Arrays.toString(booleans));
//                }
//                print_board();

                if (check_size(dy,dx) && board[dy][dx] == 2 && !isvisited[dy][dx]) {
                    isvisited[dy][dx] = true;
                    count += bfs(dy,dx);
                }
            }

//            q.add(new int[]{i, j});
//                aq.add(new int[] {i,j});
//            isvisited[i][j] = true;
//            count++;


            continue loop;
        }


//            }
//        }

        if (res < count) res = count;
//        System.out.println("count = " + count);
//        return count;
    }

    private static int bfs(int y, int x) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {y,x});
        int count = 1;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            y = poll[0];
            x = poll[1];
            for (int d = 0; d < 4; d++) {
                int dy = y + delta[d][0];
                int dx = x + delta[d][1];
                if (check_size(dy, dx)) {
                    if (board[dy][dx] == 0 && !isvisited[dy][dx]) {
//                                for (int[] ints : aq) {
//                                    isvisited[ints[0]][ints[1]] = false;
//                                }
//                        System.out.println(dy + " " + dx);
                        return 0;
                    }
                    if (board[dy][dx] == 2 && !isvisited[dy][dx]) {
                        q.add(new int[]{dy, dx});
//                                aq.add(new int[] {dy,dx});
                        isvisited[dy][dx] = true;
                        count++;
                    }
                }
            }
//            System.out.println("count = " + count + "y = " + y + " " + x);;
//                }
        }

        return count;
    }

    static boolean check_size(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    static void back_board() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = init_board[i][j];
            }
        }
    }

    static void print_board() {
        for (int[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
