import java.io.*;
import java.nio.file.Watchable;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        // 같은 양의 두 용액을 혼합
        long[] res = new long[2];


        int left = 0;
        int right = n - 1;

        long min = Math.abs(arr[left] + arr[right]);
        res[0] = arr[left];
        res[1] = arr[right];

        while (left < right) {
            long water = arr[left] + arr[right];
            if (Math.abs(water) < min) {
                min = Math.abs(water);
                res[0] = arr[left];
                res[1] = arr[right];
            }
            if (water < 0){
                left++;
            } else {
                right--;
            }
        }

        System.out.println(res[0] + " " + res[1]);
    }
}