import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[][] arr;
    static int n, k, underLine;
    static int[][] delta = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[0][i] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        while (checkFinish()) {
            underLine = n;
            time++;
            solve();
        }


        System.out.println(time);
    }


    static void solve() {
        init();
        while (moveUp()) {
        }
        moveFish();
        moveLine();
        moveHarp();
        moveFish();
        moveLine();
    }

    static void init() {
        ArrayDeque<Integer> integers = minFish();
        for (Integer integer : integers) {
            arr[0][integer]++;
        }

        arr[1][0] = arr[0][0];
        for (int i = 0; i < n - 1; i++) {
            arr[0][i] = arr[0][i + 1];
        }
        arr[0][n - 1] = 0;

    }

    static ArrayDeque<Integer> minFish() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (min > arr[0][i]) {
                min = arr[0][i];
                list.clear();
            }
            if (min == arr[0][i]) {
                list.add(i);
            }
        }
        return list;
    }

    static boolean moveUp() {
        int tmp = 0;
        for (int i = 0; i < underLine; i++) {
            if (arr[1][i] == 0) {
                break;
            }
            tmp = i;
        }

        int maxHeight = 0;
        for (int j = 0; j < n; j++) {
            if (arr[j][0] == 0) {
                break;
            }
            if (maxHeight < j) {
                maxHeight = j;
            }
        }

        if (2 * (maxHeight + 1) > underLine) {
            return false;
        }

        int[][] tmp_board = new int[n][n];

        // y
        for (int i = 0; i <= maxHeight; i++) {
            // x
            for (int j = 0; j <= tmp; j++) {
                tmp_board[tmp - j][i] = arr[i][j];
//                arr[maxHeight-j][i] = arr[i][j];
            }
        }


        for (int i = 0; i <= tmp; i++) {
            // x
            for (int j = 0; j <= maxHeight; j++) {
                arr[1 + i][j] = tmp_board[i][j];
//                arr[maxHeight + i][j] = tmp_board[i][j];
            }
        }


        for (int i = tmp + 1; i < underLine; i++) {
            arr[0][i - tmp - 1] = arr[0][i];
        }

        underLine -= tmp + 1;
        for (int i = underLine; i < n; i++) {
            arr[0][i] = 0;
        }
        return true;
    }

    static void moveFish() {
        int[][] tmpBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) continue;
                for (int d = 0; d < 4; d++) {
                    int dy = i + delta[d][0];
                    int dx = j + delta[d][1];
                    if (!checkSize(dy, dx)) continue;
                    if (arr[dy][dx] == 0) continue;
                    if (arr[i][j] > arr[dy][dx]) {
                        int mock = (arr[i][j] - arr[dy][dx]) / 5;
                        if (mock > 0) {
                            tmpBoard[dy][dx] += mock;
                            tmpBoard[i][j] -= mock;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] += tmpBoard[i][j];
            }
        }
    }


    static void moveLine() {
        int[][] tmp = new int[n][n];
        ArrayDeque<Integer> integers = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[j][i] == 0) continue;
                integers.add(Main.arr[j][i]);
            }
        }
        int tmpN = 0;
        while (!integers.isEmpty()) {
            tmp[0][tmpN++] = integers.poll();
        }
        arr = tmp;
    }

    static void moveHarp() {
        int moveLine = n / 2;

        for (int i = 0; i < moveLine; i++) {
            arr[1][moveLine - i - 1] = arr[0][i];
            arr[0][i] = arr[0][i + moveLine];
        }

        for (int i = moveLine; i < n; i++) {
            arr[0][i] = 0;
        }
        moveLine /= 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < moveLine; j++) {
                arr[1 + 2 - i][moveLine - j - 1] = arr[i][j];
                arr[i][j] = arr[i][j + moveLine];
            }
        }

        for (int i = moveLine; i < n; i++) {
            arr[0][i] = 0;
            arr[1][i] = 0;
        }
    }

    static boolean checkFinish() {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (max < arr[0][i]) {
                max = arr[0][i];
            }
            if (min > arr[0][i]) {
                min = arr[0][i];
            }
        }
        return (max - min) > k;
    }

    static boolean checkSize(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

    static void print() {
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
    }
}

