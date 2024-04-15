import java.io.*;
import java.util.*;

public class Main {
    static int res, n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        final int MAX = 500001;
        n = Integer.parseInt(br.readLine());
        int[] lines = new int[MAX];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());
            map.put(tmp2, tmp1);
            lines[tmp1] = tmp2;
        }

        int[] newLines = new int[MAX];

        int j = 0;
        for (int line : lines) {
            if (line != 0) {
                newLines[j++] = line;
            }
        }

        res = 0;
        // LIS 2번하고 더 작은것 출력
        int[] res1 = LIS(newLines);


        int[] reverseLines = new int[MAX];
        for (int i = MAX - 1, k = 0; i >= 0; i--, k++) {
            reverseLines[i] = newLines[k];
        }
        int[] res2 = LIS(reverseLines);

        int[] lastRes;

        if (res2.length >= res1.length) {
            lastRes = res2;
            sb.append(lastRes.length).append("\n");
            for (int lastRe : lastRes) {
                Integer i = map.get(lastRe);
                reverseLines[i] = 0;
            }
            int[] tmp = new int[n-lastRes.length];
            for (int i = 0, k = 0; i < reverseLines.length; i++) {
                if(reverseLines[i] != 0) tmp[k++] = i;
            }
            Arrays.sort(tmp);
            for (int i : tmp) {
                sb.append(i).append("\n");
            }

        } else {
            lastRes = res1;
            sb.append(n - lastRes.length).append("\n");

            for (int lastRe : lastRes) {
                Integer i = map.get(lastRe);
                lines[i] = 0;
            }
            int[] tmp = new int[n-lastRes.length];
            for (int i = 0, k = 0; i < lines.length; i++) {
                if(lines[i] != 0) tmp[k++] = i;
            }
            Arrays.sort(tmp);
            for (int i : tmp) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);

    }

    static int[] LIS(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[0]);

        int[] dp = new int[arr.length];
        dp[0] = 1;

        int last = 0;
        for (int i = 1; i < n; i++) {
            if (list.get(last) < arr[i]) {
                last++;
                list.add(arr[i]);
                dp[i] = list.size();
            } else {
                int left = 0;
                int right = list.size() - 1;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (arr[i] <= list.get(mid)) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                list.set(right, Math.min(arr[i], list.get(right)));
                dp[i] = right + 1;
            }
        }


        int[] res = new int[list.size()];
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == last + 1) res[last--] = arr[i];
        }

        return res;
    }

}