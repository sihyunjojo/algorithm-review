import javax.swing.text.html.ListView;
import java.awt.desktop.QuitEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 너무너무너무너무너무 어려움
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n,m,l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+2];
        arr[0] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[n+1] = l;

        Arrays.sort(arr);

        int left = 1;
        int right = l-1;

        while (left < right){
            int mid = (left + right) / 2;
            int sum = 0;

            for (int i = 0; i < n+1; i++){
                // mid와 같다면 지을 수 없으니 mid에 +1을 해주자
                // 위의 생각은 아주 위험한 생각. 그거보단 딱 맞아 떨어지면 안되니
                // 두 거리 사이에서 1을 뺴줘야지가 맞다 왜냐? 아래를 참고해바

                // 이거 2개의 차이가 엉청 크다. 아예 값이 다르게 나옴.
                // mid에 연산을 하면 아래 left right를 계산해 줄때, 귀찮아지고 복잡해진다.
                // 그리고 오버플로가 날 수도 있다.
                // 이진 탐색시 그냥 절대로 mid인 기준 값을 건드리지마!!!!! 제발
                // 그럼 처음과 마지막에서 무한 반복 걸림. left right 더 건드리므로
                // 쉽게 가
//                sum += (arr[i+1] - arr[i]) / (mid + 1);

                // 몇개가 가능한지 판단
                // 설치해야할 M개의 휴개소
                sum += (arr[i+1] - arr[i] -1) / mid;
            }
            // 몇개가 우리가 설치하려는 개수와 판단
            // 너무 많은 휴개소를 설치 할 수 있으니.
            // 더 적게 설치하세요.

            if (sum > m){
                // 이러면 마지막에는 넘어오면서 끝나고
                // 가장 최적의 값이 나왔다??
                // 결정문제 인데??

                // mid는 더 크므로 불가능하니까 +1
                left = mid +1;
            }
            // 휴개소가 적으면
            // 더 많이 설치하라고 (더 뺵뺵하게 설치 가능)범위를 줄여줌.
            // 만약 같다면 (최대의 최솟값을 줄여주기 위해서 가장 큰 휴개소의 길이 범위를 줄여줌)
            else {
                // 이러면 Left와 right가 같아지면서 끝난다.
                // 움직일 mid가 없다.

                // mid는 가능은 하니까 right를 mid로 해준다.

                // 그런데 다른 코드들은 mid -1을 해주던데 그 이유가 뭐야???
                right = mid;
            }

//            System.out.println("left = "+left);
//            System.out.println("right = "+right);
//            System.out.println("mid = "+mid);
        }

        // right가 아니고 left가 답인 이유?
        System.out.println(left);
    }
}

