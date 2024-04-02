import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main {
    static int res;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int page = Integer.parseInt(br.readLine());
        int[] ans = new int[10];
        int point = 1;
        int start = 1;
        while (start <= page) {
            // 끝자리 9로 만들기
            // -를 통해서 9로 만든다 -> 999
            // 125 이면 125 에서 119로 만드는 과정
            // 2이면 0으로하니까 밑에 브레이크.

            // 11 ~ 9
            // -> 11 10개 10 10개
            while (page % 10 != 9 && start <= page) {
                cal(page, ans, point);
                page--;
            }

            // 이때 처음 까지 가능하면
            if (page < start) {
                break;
            }

            // 끝자리 0으로 만들기
            // start 증가시키며
            // 1 ~ 9 까지 하기
            // start = 10

            // 1 ~ 9까지 10개씩 증가.
            while (start % 10 != 0 && start <= page) {
                cal(start, ans, point);
                start++;
            }
            // start 는 그럼 1으로 가는데? 그럼 똑같겠네!!
            start /= 10;
            // 10 나눠주고
            page /= 10;

            // 119 이면 11 개씩 넣어줌.
            // 119 ~ 100까지 11개씩
            // 11 - 1 + 1
            //
            // 11 -> 9 -> 9 - 1 + 1

            // 119까지 1의 자리수 추가 + 125 ~ 119까지 추가

            // 9 - 10 + 1
            for (int i = 0; i < 10; i++) {
                ans[i] += (page - start + 1) * point;
            }

            // point는 10 = 이제 10의 자리수니 10개씩 추가
            //
            point *= 10;
        }
        for (int i = 0; i < 10; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }

    // 일의 자리수에 point만큼 추가해주고
    // 또 x/10해서 그 다음 값 point 만큼 추가해주고.
    public static void cal(int x, int[] ans, int point) {
        while (x > 0) {
            ans[x % 10] += point;
            x /= 10;
        }
    }
}

