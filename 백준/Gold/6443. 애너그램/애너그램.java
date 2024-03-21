import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int size;
    static boolean[] isvisited;
    static String[] split;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            split = s.split("");
            Arrays.sort(split);
            isvisited = new boolean[split.length];

            String[] tmp_arr = new String[split.length];
            madePermutation(0, tmp_arr);
        }
        System.out.println(sb);
    }
    static void madePermutation(int cnt, String[] arr) {
        if(cnt == split.length) {
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
            }sb.append("\n");
            return;
        }
        int duplication = 0;
        for (int i =0; i < split.length; i++) {
            if(!isvisited[i]) {
                // 다음 차례랑 비교해서 같다면 다음으로 넘김
                if(duplication == split[i].charAt(0)) continue;
                arr[cnt] = split[i];
                isvisited[i] = true;
                madePermutation(cnt+1, arr);
                isvisited[i] = false;
                duplication = split[i].charAt(0);
            }
        }
    }
}
