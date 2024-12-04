import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        loop:
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.'){
                if (tmp % 2 == 0) {
                    for (int j = 0; j < tmp/4; j++) {
                        sb.append("AAAA");
                    }
                    if (tmp % 4 == 2) {
                        sb.append("BB");
                    }
                }
                else{
                    sb.append(-1);
                    break loop;
                }
                sb.append('.');
                tmp = 0;
            }
            else{
                tmp++;
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length()-1) == -1) {
            System.out.println(-1);
        }
        if (sb.length() == 0 && tmp == 0){
            System.out.println(-1);
        }
        else if (tmp % 2 == 0) {
            for (int j = 0; j < tmp/4; j++) {
                sb.append("AAAA");
            }
            if (tmp % 4 == 2) {
                sb.append("BB");
            }
            System.out.println(sb);
        }
        else {
            System.out.println(-1);
        }
        
    }
 }