import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(n/2+1);

            Queue<Integer> min_heap = new PriorityQueue<>(Comparator.naturalOrder());
            Queue<Integer> max_heap = new PriorityQueue<>(Comparator.reverseOrder());

            for (int i = 0; i < n; i++) {
                if(i % 20 == 0){
                    sb.append("\n");
                }
                if (i % 10 == 0){
                    st = new StringTokenizer(br.readLine());
                }
                int num = Integer.parseInt(st.nextToken());
                if (i == 0) max_heap.offer(num);
                else {
                    if (max_heap.peek() >= num) {
                        max_heap.offer(num);
                    } else {
                        min_heap.offer(num);
                    }
                }

                if (i % 2 == 0) {
                    while (!(max_heap.size() == min_heap.size() + 1)) {
                        if (max_heap.size() > min_heap.size() +1){
                            min_heap.offer(max_heap.poll());
                        }

                        else if (max_heap.size() < max_heap.size() +1){
                            max_heap.offer(min_heap.poll());
                        }

//                        System.out.println(max_heap);
//                        System.out.println(min_heap);
                    }
                    sb.append(max_heap.peek()).append(" ");

                }

            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}