import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.in;
import static java.util.Comparator.naturalOrder;

public class Main {
    static int res;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split("");

        int[] table = new int[n];

        int j = 0;
        for (int i = 1; i < n; i++) {
            if (0 < j && !arr[i].equals(arr[j])){
                j = table[j-1];
            }
            if (arr[i].equals(arr[j])){
                j++;
                table[i] = j;
            }
        }

        System.out.println(n-table[n-1]);
    }
}