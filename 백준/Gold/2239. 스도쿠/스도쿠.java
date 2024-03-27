import java.util.*;
import java.io.*;

public class Main{
    static int[][] sdo;
    static ArrayList<int[]> req = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sdo = new int[9][9];

        for(int i=0;i<9;i++){
            String s = br.readLine();
            for(int j=0;j<9;j++){
                sdo[i][j] = s.charAt(j) - '0';
                if(sdo[i][j] == 0) req.add(new int[] {i,j});
            }
        }

        dfs(0);
    }

    static boolean dfs(int cnt){
        if(cnt >= req.size()){
            if (cnt == req.size()) print_sdo();
            return true;
        }

        int y = req.get(cnt)[0];
        int x = req.get(cnt)[1];
        for(int j=1;j<=9;j++){
            if(isNum(y,x,j)){
                sdo[y][x] = j;
                if (dfs(cnt+1)) return true;
                sdo[y][x] = 0;
            }
        }
        return false;
    }

    static boolean isNum(int y, int x, int num){
        int y_start = (y/3)*3;
        int x_start = (x/3)*3;

        for(int i=0;i<9;i++){
            // 가로 기준 탐색
            if(sdo[y][i]==num) return false;

            // 세로 기준 탐색
            if(sdo[i][x]==num) return false;

            // 사각형 기준 탐색
            if(sdo[y_start + i/3][x_start + i%3]==num) return false;
        }
        return true;
    }


    static void print_sdo(){
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<9;i++){
            for (int n: sdo[i]) {
                sb.append(n);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}