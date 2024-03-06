import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int[] arr;
    static int n,m,res;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] arr = new ArrayList[n+1];
        int[] arr_depth = new int[n+1];

        for (int i = 0; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from].add(to);
            arr_depth[to]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] isvisited = new boolean[n+1];
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 1; i < n+1; i++){
            if (arr_depth[i] == 0){
                q.add(i);
                isvisited[i] = true;
                res.add(i);
            }
        }

        while (!q.isEmpty()){
            Integer poll = q.poll();
            for (int i = 0; i < arr[poll].size(); i++) {
                Integer now = arr[poll].get(i);
                arr_depth[now] -= 1;
                if (arr_depth[now] == 0){
                    q.add(now);
                    isvisited[now] = true;
                    res.add(now);
                }
            }
        }

        if (n == res.size()) {
            for (Integer re : res) {
                System.out.printf(re + " ");
            }
        }else System.out.println(0);
    }
}

