import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    static boolean primeflag[];
    static ArrayList<Integer> prime;
    static int subtotal[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        prime = new ArrayList<>();
        makePrime(N);

        int start=0, end=0, sum=0, count=0;
        while(true) {
            if(sum >= N ) sum -= prime.get(start++);
            else if(end == prime.size()) break;
            else sum += prime.get(end++);
            if(N==sum) count++;
        }
        System.out.println(count);


    }
    public static void makePrime(int n) {
        primeflag = new boolean[n + 1];
        primeflag[0] = primeflag[1] = true;
        for(int i=2; i*i<=n; i++){
            // prime[i]가 소수라면
            if(!primeflag[i]){
                // prime[j] 소수가 아닌 표시
                for(int j=i*i; j<=n; j+=i) primeflag[j] = true;
            }
        }
        for (int i = 1; i <=n; i++)
            if (!primeflag[i]) prime.add(i);
    }
}
