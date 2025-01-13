
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int t;
    static final int MAX = 1000000000;
    static ArrayList<Integer> dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        make_dp();
        // System.out.println(dp);

        for (int i = 0; i < t; i++) {
            int num = Integer.parseInt(br.readLine());
            for (int j = 0; j < dp.size(); j++) {
                if (num < dp.get(j)) {
                    sb.append(j).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb);
    }

    static void make_dp(){
        dp = new ArrayList<>();
        dp.add(1);
        dp.add(2);
        int tmp = 0;
        while (dp.get(tmp+1) <= MAX) {
            dp.add(dp.get(tmp)+dp.get(tmp+1)+1);
            tmp++;
        }
        dp.add(dp.get(tmp)+dp.get(tmp+1)+1);
    }
 }