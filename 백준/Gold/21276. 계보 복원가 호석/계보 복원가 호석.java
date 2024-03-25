import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.naturalOrder;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n  = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String>[] relation = new ArrayList[n];
        ArrayList<String> tmp = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tmp.add(st.nextToken());
        }
        tmp.sort(naturalOrder());

        for (int i = 0; i < n; i++) {
            String name = tmp.get(i);
            relation[i] = new ArrayList<>();
            relation[i].add(name);
            map.put(name, i);
        }


        int m = Integer.parseInt(br.readLine());

        int[] dis = new int[n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            String p1 = st.nextToken();
            String p2 = st.nextToken();
            relation[map.get(p2)].add(p1);
            dis[map.get(p1)]++;
        }

        boolean[] isvisited = new boolean[n];
        int root_cnt = 0;


        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (dis[i] == 0) {
                root_cnt++;
                q.add(i);
            }
        }

        sb.append(root_cnt).append("\n");

        for (Integer i : q) {
            sb.append(relation[i].get(0)).append(" ");
        }
        sb.append("\n");

        ArrayList<Integer>[] result = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            result[i] = new ArrayList<>();
            result[i].add(i+10000);
        }



        while (!q.isEmpty()){
            Integer now = q.poll();

            for (String next : relation[now]) {
                Integer nextNum = map.get(next);
                if (!now.equals(nextNum)){
                    result[now].add(nextNum);
                }
                if(--dis[nextNum] == 0) {
                    q.add(nextNum);
                }
            }
        }



        Arrays.sort(result,Comparator.comparingInt(o->o.size()));
        ArrayList<Integer> tmptmp = new ArrayList<>();

        for (ArrayList<Integer> integers : result) {
            loop:
            for (int i = 0; i < integers.size(); i++) {
                for (int j = 0; j < tmptmp.size(); j++) {
                    if (integers.get(i).equals(tmptmp.get(j))){
                        integers.remove(i);
                        i--;
                        continue loop;
                    }
                }
                tmptmp.add(integers.get(i));
            }
        };


        Arrays.sort(result,Comparator.comparingInt(o->o.get(0)));
//        for (int i = 0; i < result.length; i++) {
//            sb.append(relation[result[i].get(0)-10000]).append(" ");
//            sb.append(result[i].size()-1).append(" ");
//            result[i].remove(0);
//
//            for (int j = 0; j < ; j++) {
//
//            }
//        }

        for (ArrayList<Integer> integers : result) {
            sb.append(relation[integers.get(0)-10000].get(0)).append(" ");
            sb.append(integers.size()-1).append(" ");
            integers.remove(0);
            integers.sort(Comparator.naturalOrder());
            for (Integer integer : integers) {
                sb.append(relation[integer].get(0)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
