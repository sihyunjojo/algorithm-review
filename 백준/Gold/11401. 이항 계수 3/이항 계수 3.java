import java.io.*;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main{
    static int res;
    static StringBuilder sb;
    static int MOD = 1000000007;
    static int MAX = 4000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[MAX + 1];

        arr[0] = 1;
        for (int i = 1; i < MAX + 1; i++) {
            arr[i] = (arr[i-1] * i) % MOD;
        }

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        long res = arr[n] * (pow((arr[k] * arr[n - k]) % MOD, MOD - 2));
        System.out.println(res % MOD);


    }

    static long pow(long n, long k){
        if (k == 1){
            return n;
        }
        long l = pow(n,k/2) % MOD;
        if (k % 2 == 0) return l * l % MOD;
        else return ( (l * l) % MOD * n) % MOD;
    }

}