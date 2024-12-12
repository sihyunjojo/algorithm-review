
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int a,b,c;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        System.out.println(function());

    }
    static String function(){
        double tmp1 = Math.pow(b, 2) - (4 * a * c);
        // System.out.println(Math.pow(b,2));
        // System.out.println(4 * a * c);
        // System.out.println("tmp1 = " + tmp1);
        double tmp2 = Math.sqrt(tmp1);
        // System.out.println("tmp2 = " + tmp2);
        if (Double.isNaN(tmp2) || !isInt(tmp2)) return "둘다틀렸근";

        double n = (-b + tmp2) / (2 * a);
        // System.out.println(n);
        if (!isInt(n)) return "둘다틀렸근";
        double m = (-b - tmp2) / (2 * a);
        // System.out.println(m);
        if (!isInt(m)) return "둘다틀렸근";
        if (n == m) return "둘다틀렸근";
        if (isPowTow(n) && isPowTow(m) && n != m){
            return "이수근";
        }

        return "정수근";

    }
    static boolean isInt(double d){
        double tmp = d % 1;
        // System.out.println(tmp);
        return tmp == 0;
    }
    
    static boolean isPowTow(double d){
        while (d % 2 == 0) {
            d /= 2;
            if (d == 1) return true;
            if (-2 < d && d < 2) return false;
        }
        return false;
    }
 }