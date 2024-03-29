import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static StringBuilder sb;
    static int k, res;
    static int[] tmp;
    static List<Integer> alpas;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new String[n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            int len = s.length();
            arr[i] = s.substring(4, len - 4);
        }

        res = 0;
        // antic
        if (k < 5) {
            System.out.println(0);
        } else {
            k -= 5;
            alpas = count_alpa(arr);
            int len = alpas.size();
            if (k >= len){
                System.out.println(arr.length);
            }
            else {
                tmp = new int[k + 5];
                tmp[k + 4] = 0;
                tmp[k + 3] = 2;
                tmp[k + 2] = 8;
                tmp[k + 1] = 13;
                tmp[k] = 19;
                dfs(0, 0);
                System.out.println(res);
            }
        }

    }


    static void dfs(int count, int start) {
        if (count == k) {
            int cnt = count_read_String();
            if (cnt > res) res = cnt;
            return;
        }
        for (int i = start; i < alpas.size(); i++) {
            tmp[count] = alpas.get(i);
            dfs(count+1,i+1);
        }
    }

    static int count_read_String(){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int flag = 0;
            int len = arr[i].length();
            // 문자열에서 하나씩 꺼내서
            for (int j = 0; j < len; j++) {
                // tmp와 비교
                for (int value : tmp) {
                    if (arr[i].charAt(j)-'a' == value) {
                        flag++;
                        break;
                    }
                }
                // 일치하지 않는게 중간에 나옴
                if (flag != j+1){
                    break;
                }
            }
            if (flag == len){
                count++;
            }
        }
        return count;
    }


    // antic
    // 0 2 8 13 19
    static List<Integer> count_alpa(String[] s) {
        HashSet<Integer> tmp = new HashSet<>();
        for (int i = 0; i < s.length; i++) {
            int len = arr[i].length();
            for (int j = 0; j < len; j++) {
                tmp.add(arr[i].charAt(j) - 'a');
            }
        }
        tmp.remove(0);
        tmp.remove(2);
        tmp.remove(8);
        tmp.remove(13);
        tmp.remove(19);
        return new ArrayList<>(tmp);
    }
}