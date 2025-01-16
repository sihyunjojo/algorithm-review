
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int cnt, s,t,result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        cnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            solve();
        }      
        System.out.println(sb);
    }
    static void solve(){
        // now_score, target_score, time
        Deque<int[]> q = new ArrayDeque<>();
        q.addFirst(new int[] {s,t,0});

        while (!q.isEmpty()) {
            int[] now = q.pollLast();
            int now_score = now[0];
            int target_score = now[1];
            int time = now[2];

            if (now_score == target_score) {
                sb.append(time).append("\n");
                break;
            }

            if (now_score <= target_score + 1){
                q.addFirst(new int[] {now_score +1, target_score , time+1});
                q.addFirst(new int[] {now_score *2, target_score +3 , time+1});
            }
        }

    }
 }