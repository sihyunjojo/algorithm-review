
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, min_dif;
    static int[][] board;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (i < j) {
                    board[i][j] += tmp;
                } else {
                    board[j][i] += tmp;
                }
            }
        }

        // for (int[] i : board) {
            // System.out.println(Arrays.toString(i));
        // }
        visited = new boolean[n+1];
        min_dif = Integer.MAX_VALUE;
        // n/2
        for (int i = 0; i <= n/2; i++) {
            dfs(1,0,i); // 1부터 시작해서 0개 선택한거부터 i개를 선택할때까지 해라.    
        }
        
        System.out.println(min_dif);
    }
    static void dfs(int a, int cnt, int max_cnt){
        if (cnt == max_cnt) {
            int now_dif = calculate();
            // System.out.println("cal = " + now_dif);
            // System.out.println(Arrays.toString(visited));
            if (min_dif > now_dif){
                min_dif = now_dif;
            }
        }
        for (int i = a; i <= n; i++) {
            visited[i] = true;
            dfs(i+1, cnt+1, max_cnt);
            visited[i] = false;
        }
    }

    static int calculate() {
        ArrayList<Integer> tmp1 = new ArrayList<>();
        ArrayList<Integer> tmp2 = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (visited[i]) tmp1.add(i);
            else tmp2.add(i);
        }
        // System.out.println("tmp2 =  " + tmp2);
        return Math.abs(ddfs(tmp1) - ddfs(tmp2));
    }

    static int ddfs(ArrayList<Integer> array){
        int result = 0;
        for (int i = 0; i < array.size(); i++) {
            for (int j = i; j < array.size(); j++) {
                // System.out.println(i + "  " + j);
                result += board[array.get(i)][array.get(j)];
            }
        }
        // System.out.println("result = " + result);
        return result;
    }
 }