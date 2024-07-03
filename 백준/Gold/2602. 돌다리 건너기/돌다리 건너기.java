import java.io.*;

// 돌다리건너기
// 2차원 dp로 하다가 3차원 dp로 변경
// 2차원 dp시 조건이 복잡한거 같으면 3차원 dp로 조져보자
public class Main {
    static String arr;
    static String[] bridge;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = "0" + br.readLine() + "1";
        bridge = new String[2];
//        bridge[0] = br.readLine();
//        bridge[1] = br.readLine();
        // 다리 위치 맞추기
        bridge[0] = "0" + br.readLine() + "1";
        bridge[1] = "0" + br.readLine() + "1";


        // [위아래][현재 단어의 위치][ 이전 단어가 어느위치에 있는지 따라]
        dp = new int[2][arr.length()][bridge[0].length()];
        // for문 돌려야하나? ㄴㄴ
//        for (int i = 0; i < bridge[0].length(); i++) {
            dp[0][0][0] = 1;
            dp[1][0][0] = 1;
//        }

        for (int i = 1; i < arr.length(); i++) {
            logic(0, i);
            logic(1, i);
        }

        System.out.println(dp[0][arr.length()-1][bridge[0].length()-1]+dp[1][arr.length()-1][bridge[0].length()-1]);
    }

    private static void logic(int updown, int row){
        // 현재 글자
        char nowBrChar = arr.charAt(row);
        char pastArrChar = arr.charAt(row-1);
        // 모든 다리의 글자 중에서 지금 글자와 같은 글자에 고름.
        for (int i = 1; i < bridge[0].length(); i++) {
            if (bridge[updown].charAt(i) == nowBrChar){
                // 찾은 글자의 이전 두루마기 글자와 비교후 덧셈
                for (int j = 0; j < i; j++) {
                    if(bridge[(updown+1)%2].charAt(j) == pastArrChar) {
                        // why? row-1
                        // 현재 두루마기 숫자의 바로 이전 숫자를 순서대로 가야하니까!
                        // why? j
                        // 이전 돌다리면 다 상관 없으니까
                        dp[updown][row][i] += dp[(updown + 1) % 2][row-1][j];
                    }
                }
            }
        }
    }
}