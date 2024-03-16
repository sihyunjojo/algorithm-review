import javax.swing.text.html.ListView;
import java.awt.desktop.QuitEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 너무너무너무너무너무 어려움
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n,m,l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        int last = n-1;
        int first = 0;
        int second = 1;

        for (int i = n; i >= 1; i--) {
            int num = Integer.parseInt(st.nextToken());
            switch (num){
                case 1:
                    arr[first] = i;
                    second++;
                    first = second-1;
                    break;
                case 2:
                    arr[second] = i;
                    second++;
                    break;
                default:
                    arr[last] = i;
                    last--;
                    break;
            }
//            System.out.println(list);
        }

        StringBuilder sb = new StringBuilder();

        for (int i= 0; i < n; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);

    }
}

