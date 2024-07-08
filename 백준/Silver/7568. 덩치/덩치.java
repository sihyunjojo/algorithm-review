import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = i;
        }

        Arrays.sort(arr,(o1, o2) -> {
            if (o1[0] == o2[0]){
                return -(o1[1] - o2[1]);
            }
            return -(o1[0] - o2[0]);
        });

        arr[0][3] = 1;

        for (int i = 1; i < n; i++) {
            int cnt = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j][0] > arr[i][0] && arr[j][1] > arr[i][1]) cnt++;
            }
            arr[i][3] = cnt;
        }

        Arrays.sort(arr,Comparator.comparingInt(o->o[2]));

        for (int[] o : arr) {
            sb.append(o[3]).append(" ");
        }

        System.out.println(sb);
    }
}