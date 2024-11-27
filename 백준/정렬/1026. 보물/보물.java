import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> a, b;
    static int s = 0;         
    static int n, score;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        
        n = Integer.parseInt(br.readLine());

        a = new ArrayList<>();
        b = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());    
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());   
            a.add(tmp);
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());   
            b.add(tmp);
        }
        
        a.sort(Comparator.naturalOrder());
        b.sort(Comparator.reverseOrder());

        for (int i = 0; i < n; i++) {
            s += a.get(i) * b.get(i);
        }

        System.out.println(s);
    }
 }