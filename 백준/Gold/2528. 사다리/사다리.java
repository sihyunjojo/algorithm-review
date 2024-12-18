
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, L, now_time;
    static int[][] line_info;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        line_info = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            line_info[i][0] = Integer.parseInt(st.nextToken());
            line_info[i][1] = Integer.parseInt(st.nextToken());
        }
        
        now_time = 0;
        func();
        System.out.println(now_time);
    }

    static void func() {
        for (int i = 0; i < N - 1; i++) {
            while (!is_upfloor(now_line(i), now_line(i+1))){
                now_time++;
            }
            // System.out.println("result = = " + now_time);
        }
    }
    static int[] now_line(int floor){
        // System.out.println("floor = " + floor);
        // System.out.println(Arrays.toString(line_info[floor]));
        if (L == line_info[floor][0]) return new int[] {0, L};
        int tmp1 = now_time % (L - line_info[floor][0]);
        int tmp2 = now_time / (L - line_info[floor][0]);
        // System.out.println(tmp2 + line_info[floor][1]);
        if ((tmp2 + line_info[floor][1]) % 2 == 0) {
            // System.out.println(tmp1);
            // System.out.println(line_info[floor][0] + tmp1);
            return new int[] {tmp1, line_info[floor][0] + tmp1};
        } 
        // System.out.println(L-tmp1-line_info[floor][0]);
        // System.out.println(L-tmp1);
        return new int[] {L-tmp1-line_info[floor][0], L-tmp1};
    }

    static boolean is_upfloor(int[] now_line, int[] next_line) {
        for (int i = now_line[0]; i <= now_line[1]; i++) {
            for (int j = next_line[0]; j <= next_line[1]; j++) {
                if (i == j) return true;
            }
        }
        return false;
    }
 }