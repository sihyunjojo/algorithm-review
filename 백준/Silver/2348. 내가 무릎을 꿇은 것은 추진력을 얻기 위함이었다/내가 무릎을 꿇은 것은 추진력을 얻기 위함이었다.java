
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static String s;
    static ArrayList<Integer> results = new ArrayList<>();
    static int max_cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        

        max_cnt = Math.min(9, s.length());
        for (int i = 1; i <= max_cnt; i++) {
            func(i,toInt(0, i),i,Integer.MAX_VALUE,1);
        }
        if (results.size() == 0) {
            System.out.println(0);
        } else{
            results.sort(Comparator.naturalOrder());
            System.out.println(results.get(0));
        }
    }
   
    static void func(int cnt, int now_int, int now_point, int dif, int num_num) {
        for (int i = cnt; i <= max_cnt; i++) {
            int next_point = now_point + i;
            if (next_point > s.length()) break;
            if (s.charAt(now_point) == '0') break;
            int next_int = toInt(now_point, next_point);
            int now_dif = next_int - now_int;
            if (next_point == s.length() && num_num > 1) {
                if (next_int % now_int == 0 && next_int != now_int){
                    results.add(next_int / now_int);
                    // System.out.println(next_int/now_int);
                    // System.out.println(now_int);
                    // System.out.println(next_int);
                    // System.out.println();
                }
            }
            if (now_dif <= 0 || (now_dif != dif && num_num != 1)) continue;
            func(cnt, next_int, next_point, now_dif, num_num+1);
            // func(cnt++, next_int, next_point, now_dif, num_num+1);
        }
    }


    static int toInt(int first, int last) {
        return Integer.parseInt(String.valueOf(s.substring(first, last)));
    }
 }