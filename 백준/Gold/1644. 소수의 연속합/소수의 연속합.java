import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static int res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        int tmp = 0;

        boolean[] dp = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            if (!dp[i]) {
                int j = 1;
                while (j * i <= n) {
                    dp[j++ * i] = true;
                }
                arr.add(arr.get(tmp++) + i);
            }
        }

        int start = 0, end = 1, sum = 0;

        while (start <= end && end < arr.size()){
            sum = arr.get(end) - arr.get(start);

            if (sum < n) {
                end++;
            } else if (sum > n) {
                start++;
            } else {
                res++;
                end++;
            }
        }
        System.out.println(res);
    }

}