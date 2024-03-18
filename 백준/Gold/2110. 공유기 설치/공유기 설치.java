import javax.swing.text.html.ListView;
import java.awt.desktop.QuitEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = -1;
        int right = arr[n - 1] + 1;

        while (left < right) {
            int mid = (left + right) / 2;
//            System.out.println("mid = " + mid);
            int sum = 0;
            int i = 0; int j = 1;

            while (true) {
//                System.out.println("I" + i + "j " + j);
                if (arr[j] - arr[i]  >= mid){
                    i = j;
                    j = i + 1;
                    sum++;
                } else {
                    j++;
                }
                if (j == n) break;
            }

//            System.out.println("sum = " + sum);

            if (sum >= m-1) {
                // 이거는 고정을 한데.
                left = mid + 1;
            } else {
                right = mid;
            }

//            System.out.println("left = " + left);
//            System.out.println("right = " + right);
        }

        System.out.println(right-1);
    }
}
