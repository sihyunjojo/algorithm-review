
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int a,b,S;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        loop:
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());    
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            int[] result1 = call(a, b);
            int[] result2 = call(b, a);

            int result1_sum = 0;
            int result2_sum = 0;

            for (int j = 0; j < result1.length; j++) {
                result1_sum += result1[j];
            }
            for (int j = 0; j < result2.length; j++) {
                result2_sum += result2[j];
            }

            if (result1[0] + result1[1] + result2[0] + result2[1] == 0){
                sb.append("Impossible").append("\n");
            } else if (result1_sum > result2_sum){
                sb.append(result2[0] + " " + result2[1] +"\n");
            } else if (result1_sum <= result2_sum){
                sb.append(result1[0] + " " + result1[1] +"\n");
            }
        }
        System.out.println(sb);
    }

    static int[] call(int min_money, int max_money){
        boolean[] visited = new boolean[min_money];

        int expensive_money_cnt = S / max_money;

        while(expensive_money_cnt >= 0) {
            int charge = (S - (expensive_money_cnt * max_money)) % min_money;
            if (visited[charge]) {
                return new int[] {0,0};
            }
            if (charge == 0) {
                if (a == max_money) return new int[] {expensive_money_cnt,(S - (expensive_money_cnt * max_money)) / min_money};
                else if(b == max_money) return new int[] {(S - (expensive_money_cnt * max_money)) / min_money, expensive_money_cnt};
            }
            visited[charge] = true;
            expensive_money_cnt--;
        }
        return new int[] {0,0};
    }
 }