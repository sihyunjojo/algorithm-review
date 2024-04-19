import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

// 단지번호 붙이기
public class Main{
    static int res;
    static int[][] board;
    static StringBuilder sb = new StringBuilder();
    static int[][] delta = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j) -'0';
            }
        }


        ArrayList<Integer> list= new ArrayList();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1){
                    list.add(bfs(i,j));
                }
            }
        }
        list.sort(Comparator.naturalOrder());

        System.out.println(list.size());
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    private static int bfs(int i, int j) {
        int cnt = 1;
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.add(new int[] {i,j});
        board[i][j] = 0;
        while (!stack.isEmpty()){
            int[] poll = stack.poll();
            int y = poll[0];
            int x = poll[1];

            for (int d = 0; d < 4; d++) {
                int dy = y+delta[d][0];
                int dx = x+delta[d][1];

                if (!checkSize(dy,dx)) continue;
                if (board[dy][dx] == 0) continue;
                cnt++;
                board[dy][dx] = 0;
                stack.add(new int[] {dy, dx});
            }

        }
        return cnt;
    }

    private static boolean checkSize(int i, int j) {
        return 0 <= i && i < board.length && 0 <= j && j < board[0].length;
    }
}