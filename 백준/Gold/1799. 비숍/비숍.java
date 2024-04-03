import java.io.*;
import java.util.*;

public class Main {
    static class Queen {
        int x, y, minus, plus, index, in;

        public Queen(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.minus = x - y + n - 1;
            this.plus = x + y;
        }

        @Override
        public String toString() {
            return "Queen{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static StringBuilder sb = new StringBuilder();
    static int[][] board;
    static int[] plus, minus;
    static int n, res1, res2, size;
    static ArrayList<Queen> list1, list2, now_list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        size = (2 * n) - 1;
        board = new int[n][n];
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        res1 = 0;
        res2 = 0;

        int index = 1;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    Queen queen = new Queen(j, i, ++index);
//                    list1.add(queen);
                    if ((i + j) % 2 == 0) {
                        list1.add(queen);
                    } else {
                        list2.add(queen);
                    }
                }
            }
        }

        plus = new int[(2 * n) - 1];
        minus = new int[(2 * n) - 1];
        dfs(0, 0);
        plus = new int[(2 * n) - 1];
        minus = new int[(2 * n) - 1];
        dfs2(0, 0);
        System.out.println(res1 + res2);

    }

    static void dfs(int index, int cnt) {
        if (res1 >= cnt + list1.size() - index) return;
        if (index == list1.size()) {
            if (cnt > res1) res1 = cnt;
            return;
        }
        Queen queen = list1.get(index);
        if (checkThis(queen)) {
            dfs(index + 1, cnt + 1);
            minus[queen.minus]--;
            plus[queen.plus]--;
        }
        // 여기 무조건 해줘야하나?
        dfs(index + 1, cnt);
    }

    static void dfs2(int index, int cnt) {
        if (res2 >= cnt + list2.size() - index) return;
        if (index == list2.size()) {
            if (cnt > res2) res2 = cnt;
            return;
        }
        Queen queen = list2.get(index);
        if (checkThis(queen)) {
            dfs2(index + 1, cnt + 1);
            minus[queen.minus]--;
            plus[queen.plus]--;
        }
        // 여기 무조건 해줘야하나?
        dfs2(index + 1, cnt);
    }


    static boolean checkThis(Queen q) {
        if (minus[q.minus] > 0) return false;
        if (plus[q.plus] > 0) return false;
        minus[q.minus]++;
        plus[q.plus]++;
        return true;
    }
}


/**
 * 1부터 10까지 숫자중ㅇ ㅔ3개를 뽑ㅇ르거야 뭐가 최적인지 몰라
 * 구해보고 조건이 맞으면 땡큐
 * 123 124 125 ..1210
 * 134 135 ... 1310
 * 1910
 * 234
 */