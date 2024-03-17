import javax.swing.text.html.ListView;
import java.awt.desktop.QuitEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 너무너무너무너무너무 어려움
public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int cnt = 0;
        String[] arr = new String[n+m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        for (int i = n; i < n+m; i++) {
            arr[i] = br.readLine();
        }

        String[] arr_n = Arrays.copyOfRange(arr, 0, n);

        Arrays.sort(arr);

        for (int i = 0; i < m+n-1; i++) {
            if (arr[i].equals(arr[i+1])){
                for (int j = 0; j < n; j++) {
                    if (arr_n[j].equals(arr[i])){
                        cnt++;
                        break;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}