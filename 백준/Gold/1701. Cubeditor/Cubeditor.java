import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int n = str.length();
		max = 0;
		int pi[] = new int[5001];
		String pattern = "";
		for(int i=0; i<n; i++) {
			pattern = str.substring(i,n);
			getPi(pattern,pi);
		}
		System.out.println(max);
	}

	private static void getPi(String pattern, int[] pi) {
        pi = new int[5001]; 
		int size = pattern.length();
		int j=0;
		for(int i=1; i<size; i++) {
			while(j>0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			if(pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
			if(max < pi[i]) {
				max = pi[i];
			}
		}
	}
}