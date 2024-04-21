import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());  //테스트 케이스 개수
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] scores = new int[n];
            double sum = 0;
            for (int j = 0; j < n; j++) {
                scores[j] = Integer.parseInt(st.nextToken());
                sum += scores[j];
            }
            sum /= (double)n; //평균 구하기
            double count = 0;  //평균 이상이 몇 명인지 세기
            for (int j = 0; j < n; j++) {
                if (scores[j] > sum) {
                    count++;
                }
            }
            count /= (double)n;
            count *= 100;  //평균 이상인 확률 구하기
            String s = String.format("%.3f", count);  //세자리수로 반올림 하기
            sb.append(s + "%\n");
        }
        System.out.print(sb);
    }
}