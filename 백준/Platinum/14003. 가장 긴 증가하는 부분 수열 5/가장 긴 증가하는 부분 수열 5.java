import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        final int MIN = -1000000000;
        int n = Integer.parseInt(br.readLine());


        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - MIN;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[0]);

        int last = 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] > list.get(last)) {
                last++;
                list.add(arr[i]);
                dp[i] = list.size();
            } else {
                int left = 0;
                int right = last;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (list.get(mid) < arr[i]){
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
//                dp[i] = Math.max(right,1);
                dp[i] = right+1;
                list.set(right, Math.min(list.get(right), arr[i]));
            }
        }
        System.out.println(list.size());

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = n-1; i >= 0; i--) {
            if(last+1 == dp[i]) {
                q.addFirst(arr[i] + MIN);
                last--;
            }
        }
        for (Integer i : q) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}

//6
//1 3 5 7 2 3
//
//정답
//---------
//4
//1 3 5 7

//6
//8 10 9 9 10 8
//(정답: 3/8 9 10)

//7
//2 100 200 3 4 5 6

//9
//3 1 2 4 7 5 6 8 10