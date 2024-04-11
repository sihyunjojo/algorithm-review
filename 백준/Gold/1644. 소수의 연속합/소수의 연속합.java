import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
    static int start,end,sum,res;
    static boolean[] dp;
    static ArrayList<Integer> arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        dp = new boolean[n + 1];
        dp[0] = dp[1] = true;
        makePrime(n);

        while (true) {
            if (n <= sum) sum -= arr.get(start++);
            else if (end == arr.size()) break;
            else sum += arr.get(end++);
            if (sum == n) res++;
        }

        System.out.println(res);
    }

    private static void makePrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (!dp[i]) {
                for (int j = i * i; j <= n; j += i) {
                    dp[j] = true;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!dp[i]) arr.add(i);
        }

    }
}