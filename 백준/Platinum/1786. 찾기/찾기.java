import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String t = br.readLine();
        String p = br.readLine();
        int cnt = 0;


        int[] table = new int[p.length()];

        // 패턴에서 접두사와 접미사의 길이가 같은 걸찾음.
        int j = 0;
        for (int i = 1; i < p.length(); i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = table[j - 1]; // 이전 테이블 값 가져오면서 그
            }
            if (p.charAt(i) == p.charAt(j)) {
                j += 1; // j는 1을 증가해서 다음 반복문에서 비교할 변수 준비 (+1 과 마지막을 비교해야하니)
                table[i] = j; // j는 위에서 1을 증가시켜줘야지 인덱스 + 1이므로 개수가 들어간다.
            }
        }

//        System.out.println(Arrays.toString(table));

        ArrayList<Integer> list = new ArrayList<>();

        j = 0;
        for (int i = 0; i < t.length(); i++) {
            while (j > 0 && t.charAt(i) != p.charAt(j)){
                j = table[j-1];
            }
            if (t.charAt(i) == p.charAt(j)){
                if (j == p.length()-1){
                    sb.append(i-j+1).append(" ");
                    cnt++;
//                    System.out.printf("%d ",i-j+1);
                    j = table[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(sb);
    }
}