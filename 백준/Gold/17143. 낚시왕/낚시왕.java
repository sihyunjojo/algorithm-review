import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main{
    static class Shark{
        int x,y,dir,speed,size;
        public Shark(int x, int y, int dir, int speed, int size) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.speed = speed;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    ", speed=" + speed +
                    ", size=" + size +
                    '}';
        }
    }
    static int r,c,m,res;
    static StringBuilder sb;
    static Shark[] sharks;
    static int[][] delta = new int[][] {{0,0},{-1,0},{1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sharks= new Shark[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            sharks[i] = new Shark(x-1,y-1,dir,speed,size);
        }

        res = 0;
        sortSharks();
        for (int i = 0; i < c; i++) {
            res += getShark(i);
            moveSharks();
        }

        System.out.println(res);

    }

    private static int getShark(int place) {
        for (int i = 0; i < m; i++) {
            if (sharks[i].size == 0) continue;
            if (sharks[i].x == place){
                int tmp = sharks[i].size;
                sharks[i].size = 0;
                return tmp;
            }
        }
        return 0;
    }

    private static void moveSharks() {

        for (int i = 0; i < m; i++) {
            if (sharks[i].size == 0) continue;
            moveShark(sharks[i]);
        }

        sortSharks();
        sumSharks();
    }

    private static void sortSharks() {
        Arrays.sort(sharks,(s1,s2) -> {
            if (sharkNum(s1) == sharkNum(s2)) {
                return -(s1.size - s2.size);
            }
            return sharkNum(s1) - sharkNum(s2);
        });
    }

    private static void moveShark(Shark shark) {
        for (int i = 0; i < shark.speed; i++) {
            switch (shark.dir) {
                case 1:
                    shark.y--;
                    if (shark.y < 0){
                        shark.dir = 2;
                        shark.y = 1;
                        if (r == 0){
                            shark.y = 0;
                        }
                    }
                    if (shark.y == 0){
                        shark.dir = 2;
                    }
                    break;
                case 2:
                    shark.y++;
                    if (shark.y > r-1){
                        shark.dir = 1;
                        shark.y = r-2;
                        if (r == 0){
                            shark.y = 0;
                        }
                    }
                    if (shark.y == r-1){
                        shark.dir = 1;
                    }
                    break;
                case 3:
                    shark.x++;
                    if (shark.x > c-1){
                        shark.dir = 4;
                        shark.x = c-2;
                        if (c == 0){
                            shark.x = 0;
                        }
                    }
                    if (shark.x == c-1){
                        shark.dir = 4;
                    }
                    break;
                case 4:
                    shark.x--;
                    if (shark.x < 0){
                        shark.dir = 3;
                        shark.x = 1;
                        if (c == 0){
                            shark.x = 0;
                        }
                    }
                    if (shark.x == 0){
                        shark.dir = 3;
                    }
                    break;
            }
        }
    }

    private static void sumSharks() {
        for (int i = 0; i < m; i++) {
              if (sharks[i].size == 0) continue;
            for (int j = i+1; j < m; j++) {
                Shark tmp = sharks[j];
                if (sharkNum(sharks[i]) == sharkNum(tmp)){
                    tmp.size = 0;
                    i++;
                } else {
                    break;
                }
            }
        }
    }

    private static int sharkNum(Shark shark){
        return (shark.x * r + shark.y % r);
    }
    static boolean check_size_col(int y){
        return 0 >= y || y >= r-1;
    }
    static boolean check_size_row(int x){
        return 0 >= x || x >= c-1;
    }
}