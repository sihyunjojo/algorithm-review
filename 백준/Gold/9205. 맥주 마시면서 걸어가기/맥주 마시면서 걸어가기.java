import java.util.*;
import java.io.*;

import static java.lang.System.in;

public class Main {
    static class Place {
        int x, y, index;
        ArrayDeque<Integer> inbound = new ArrayDeque<>();

        public Place(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Place{" +
                    "x=" + x +
                    ", y=" + y +
                    ", index=" + index +
                    ", inbound=" + inbound +
                    '}';
        }
    }

    static int res;
    static StringBuilder sb;
    static Place[] markets;
    static boolean[] isvisited;
    static int cnt_markets;
    static Place home;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            Place fst;
            cnt_markets = Integer.parseInt(br.readLine());
            markets = new Place[cnt_markets + 2];
            isvisited = new boolean[cnt_markets + 2];

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            home = new Place(x, y, 0);
            markets[0] = home;

            for (int i = 1; i < cnt_markets + 1; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());

                Place market = new Place(x, y, i);

                for (int j = 0; j < i; j++) {
                    Place place = markets[j];
                    if (check_in_bound(market,place)){
                        market.inbound.add(place.index);
                        place.inbound.add(market.index);
                    }
                }

                markets[i] = market;
            }


            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            fst = new Place(x, y, cnt_markets + 1);

            for (int j = 0; j < cnt_markets+1; j++) {
                Place place = markets[j];
                if (check_in_bound(fst, place)){
                    fst.inbound.add(place.index);
                    place.inbound.add(fst.index);
                }
            }
            markets[cnt_markets + 1] = fst;


           /* if (dfs(home)) System.out.println("happy");
            else System.out.println("sad");*/

            System.out.println(bfs());
        }
    }
    static boolean p(){
        // 출발지
        for (int i = 0; i < cnt_markets + 1; i++) {
            // 경유지
            for (int j = 0; j < cnt_markets + 1; j++) {
                if (i == j) continue;
                // 도착지
                for (int k = 0; k < cnt_markets + 1; k++) {
                    if (j == k) continue;

                }
            }
        }
        return false;
    }
    static boolean dfs(Place p) {
        if (p.index == cnt_markets + 1) {
            return true;
        }
        for (Integer i : p.inbound) {
            if (isvisited[i]) continue;
            isvisited[i] = true;
            if (dfs(markets[i])) return true;
            isvisited[i] = false;
        }
        return false;
    }

    static String bfs(){
        ArrayDeque<Place> q = new ArrayDeque<>();
        q.add(home);

        while (!q.isEmpty()){
            Place now = q.poll();
            if (now.index == cnt_markets + 1) {
                return "happy";
            }

            for (Integer i : now.inbound) {
                if(isvisited[i]) continue;
                isvisited[i] = true;
                q.add(markets[i]);
            }
        }

        return "sad";
    }

    static int cal_dis(Place p1, Place p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static boolean check_in_bound(Place p1, Place p2) {
        int dis = cal_dis(p1, p2);
        return dis <= 1000;
    }
}