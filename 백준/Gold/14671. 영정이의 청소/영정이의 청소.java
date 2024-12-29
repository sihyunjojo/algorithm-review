
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static boolean[][] board; 
    static int n,m,k;
    static String result = "NO";
    static int[][] delta = new int[][] {{-1,-1},{1,1},{-1,1},{1,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new boolean[m+1][n+1];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[y][x] = true;
        }
        
        solve();
        
        System.out.println(result);
        
    }
    static void solve(){
        boolean tmp1 = false;
        boolean tmp2 = false;
        boolean tmp3 = false;
        boolean tmp4 = false;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (board[i][j] == true) {
                    if (i % 2 == 0) {
                        if ((i +j) % 2 == 0){
                            tmp1 = true;
                        } else tmp2 = true;
                    }
                    else {
                        if ((i +j) % 2 == 0){
                            tmp3 = true;
                        } else tmp4 = true;
                    }
                }
            }
        }

        if (tmp1 && tmp2 && tmp3 && tmp4) result = "YES";
    }
 }