import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 항상 정해져있는 만큼의 조합을 할때에는
// for문을 그냥 사용하면 된다.
// n이 바뀌는게 아니면 재귀를 쓸 필요가 없다.
public class Main {
    static int min, max;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<int[]> tmp_list;
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        int n = calHolsu(s);
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        dfs(s, n);
        System.out.println(min + " " + max);
    }

    static void dfs(String s, int res) {
        if (s.length() == 2) {
            while (s.length() == 2) {
                s = isLenTwo(s);
                int num = calHolsu(s);
                res += num;
            }
        }
        if (s.length() == 1) {
            if (max < res) max = res;
            if (min > res) min = res;
            return;
        }
        int len = s.length();
        for (int i = 0; i <= len - 3; ++i) {
            for (int j = i + 1; j <= len - 2; ++j) {
                String s1 = s.substring(0, i + 1);
                String s2 = s.substring(i + 1, j + 1);
                String s3 = s.substring(j + 1, len);

                int sum = Integer.parseInt(s1) + Integer.parseInt(s2) + Integer.parseInt(s3);
                dfs(String.valueOf(sum), res + calHolsu(String.valueOf(sum)));
            }
        }
    }

    static String isLenTwo(String s) {
        int first = Integer.parseInt(String.valueOf(s.charAt(0)));
        int second = Integer.parseInt(String.valueOf(s.charAt(1)));
        return String.valueOf(first + second);
    }

    static int calHolsu(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((Integer.parseInt(String.valueOf(s.charAt(i))) % 2) == 1) {
                cnt++;
            }
        }
        return cnt;
    }


}