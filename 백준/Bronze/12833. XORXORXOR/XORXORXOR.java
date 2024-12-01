import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int a,b,c,result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        c %= 4;
        if (c == 0) c = 4;
        result = b;
        for (int i = 0; i < c - 1; i++) {
            result = result ^ b;
        }
        System.out.println(a ^ result);
    }
 }