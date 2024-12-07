
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int cnt, result = 0;
    static int[] combi = new int[2];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cnt = Integer.parseInt(br.readLine());

        for (int i = cnt / 3; i <= cnt / 2; i++) {
            if (i < cnt - i) {
                // 1 ~ i 중에서 2개를 넣는데, 둘의 합이 cnt -i 여야함.
                int j = 0;
                // i - j 과 (cnt - i) - (i - j)
                while (i >= i - j && i >= cnt - i - i + j && i - j >= cnt - i - i + j){
                    // System.out.println(String.format("%d  %d  %d", i, i-j , cnt -i - i +j));
                    result++;
                    j++;
                }
            }
        }
        System.out.println(result);
        
    }
 }