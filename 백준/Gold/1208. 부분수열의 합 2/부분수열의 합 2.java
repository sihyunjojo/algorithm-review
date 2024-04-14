import java.io.*;
import java.util.*;

// 이진탐색으로도 풀어보기.
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n,s;
    static long res;
    static int[] arr;
    static Map<Integer, Integer> sumMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sumMap = new HashMap<>();

        rightFuc(n/2,0);
//        sumMap.merge(0,-1, Integer::sum);

  //      for (Map.Entry<Integer, Integer> integerIntegerEntry : sumMap.entrySet()) {
    //        System.out.println(integerIntegerEntry);
      //  }
        leftFuc(0,0);

        // 공집함 0 , 0 만났을때 뺴줌.
        if (s == 0) System.out.println(res-1);
        else System.out.println(res);
//        System.out.println(res);
    }
    static void rightFuc(int rightPoint,int sum){
        if (rightPoint == n) {
            sumMap.merge(sum,1, Integer::sum);
            return;
        }
        rightFuc(rightPoint+1,sum);
        rightFuc(rightPoint+1,sum+arr[rightPoint]);
    }

    static void leftFuc(int leftPoint,int sum){
        if (leftPoint == n/2) {
            res += sumMap.getOrDefault(s - sum, 0);
//            System.out.println(sumMap.get(s-sum));
            return;
        }
        leftFuc(leftPoint+1,sum);
        leftFuc(leftPoint+1,sum+arr[leftPoint]);
    }
}