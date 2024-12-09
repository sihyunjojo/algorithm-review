
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[][] board = new int[5][5];
    static int x,y;
    static int[][] delta = new int[][] {{1,0},{-1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        if (board[y][x] == 1) {
            board[y][x] = -1;
            System.out.println(dfs(x,y,0,1) ? 1 : 0 ); 
        } else{
            board[y][x] = -1;
            System.out.println(dfs(x,y,0,0) ? 1 : 0 ); 
        }
        
    }
    static boolean dfs(int mx, int my, int move_cnt, int apple_cnt){
        if (move_cnt == 4) return false;
        if (apple_cnt == 2) return true;
        
        
        for (int i = 0; i < 4; i++) {
            int dx = mx + delta[i][0];
            int dy = my + delta[i][1];
            if (0 <= dx && dx <= 4 && 0 <= dy && dy <= 4 && board[dy][dx] != -1) {
                if (board[dy][dx] == 1) {
                    board[dy][dx] = -1;
                    if (dfs(dx, dy, move_cnt+1, apple_cnt+1)) return true;
                    board[dy][dx] = 1;
                } else if(board[dy][dx] == 0){
                    board[dy][dx] = -1;
                    if (dfs(dx, dy, move_cnt+1, apple_cnt)) return true;
                    board[dy][dx] = 0;
                }
            }
        }

        return false;
    }

    static void print(){
        for (int[] is : board) {
            System.out.println(Arrays.toString(is));
        }
    }
 }