import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int cnt, result, hp;
    static int[] hpArray, happyArray, array;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cnt = Integer.parseInt(br.readLine());
        
        hpArray = new int[cnt];
        happyArray = new int[cnt];

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < cnt; j++) {
            hpArray[j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < cnt; j++) {
            happyArray[j] = Integer.parseInt(st.nextToken());
        }

        // 체력 오름차순 정렬
        array = new int[cnt];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        result = 0; 
        dfs(0, 0, 0);

        System.out.println(result);

    }
    static void dfs(int count, int happy, int hp){
        if (hp >= 100) return;
        if (count == cnt && hp < 100) {
            if (happy > result){
                result = happy;
            }
            return;
        }

        dfs(count + 1, happy + happyArray[array[count]], hp + hpArray[array[count]]);
        dfs(count + 1, happy, hp);
    }
 }