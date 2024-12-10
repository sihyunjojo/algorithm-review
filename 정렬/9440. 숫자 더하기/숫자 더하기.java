
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        ArrayList<Integer> list = new ArrayList<>();

        while (!s.equals("0")) {
            st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            ArrayList<Integer> arr = new ArrayList<>();
            int zero_cnt = 0;

            for (int i = 0; i < n; i++) {
                Integer atom = Integer.parseInt(st.nextToken());
                if (atom == 0){
                    zero_cnt++;
                } else {
                    arr.add(atom);
                }
            }

            arr.sort(null);

            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            
            s1.append(arr.get(0));
            s2.append(arr.get(1));

            for (int i = 1; i <= zero_cnt; i++) {
                if (i % 2 == 1) s1.append(0);
                else if (i % 2 == 0) s2.append(0);
            }
            for (int i = 2; i < n - zero_cnt; i++) {
                if (i % 2 == 0) {
                    if (zero_cnt % 2 == 1) s2.append(arr.get(i));
                    else s1.append(arr.get(i));
                } else if (i % 2 == 1) {
                    if (zero_cnt % 2 == 1) s1.append(arr.get(i));
                    else s2.append(arr.get(i));
                }
            }
            list.add(Integer.parseInt(s1.toString()) + Integer.parseInt(s2.toString()));
            s = br.readLine();            
        }
        
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (list.size() != i-1){
                System.out.println();
            }
            
        }
        
    }
 }