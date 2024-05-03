import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int res;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];

        dfs(0);
        System.out.println(res);
    }

    static void dfs(int cnt) {
        if (cnt == n * m) {
            res++;
            return;
        }
        int y = cnt / m + 1;
        int x = cnt % m + 1;

        if (board[y - 1][x] == 1 && board[y][x - 1] == 1 && board[y - 1][x - 1] == 1) {
            // 현재 놓을 수 없는 곳
            dfs(cnt + 1);
        } else {
            dfs(cnt + 1); // 선택안하고 다음꺼 본 경우
            board[y][x] = 1;
            dfs(cnt + 1);// 선택하고 다음꺼 본 경우
            board[y][x] = 0;
        }

    }
}