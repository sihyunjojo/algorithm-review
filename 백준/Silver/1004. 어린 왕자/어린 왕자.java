import java.io.*;
import java.util.Arrays;

public class Main {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Planet {
        int radius;
        Point point;

        public Planet(int x, int y, int radius) {
            this.radius = radius;
            this.point = new Point(x, y);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < tc; i++) {
            int[] arr = getIntArrFromStr(br.readLine());
            Point startPoint = new Point(arr[0], arr[1]);
            Point endPoint = new Point(arr[2], arr[3]);
            int num = Integer.parseInt(br.readLine());
            int res = 0;
                
            for (int j = 0; j < num; j++) {
                int[] planetArr = getIntArrFromStr(br.readLine());
                Planet planet = new Planet(planetArr[0], planetArr[1], planetArr[2]);
                if (isPointInPlanet(startPoint, planet) ^ isPointInPlanet(endPoint, planet)) {
                    res++;
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
    

    static int[] getIntArrFromStr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    static boolean isPointInPlanet(Point point, Planet planet) {
        Point planetPoint = planet.point;
        double distance = Math.sqrt(Math.pow(planetPoint.x - point.x, 2) + Math.pow(planetPoint.y - point.y, 2));
        return distance <= planet.radius;
    }
}