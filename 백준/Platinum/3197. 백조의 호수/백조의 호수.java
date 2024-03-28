
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int r, c;
	static String[][] map;
	static boolean[][] visited, v;
	static ArrayList<int[]> person = new ArrayList<>();
    static Queue<int[]> q = new ArrayDeque<>();
    static Queue<int[]> q2 = new ArrayDeque<>();
	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };

	static boolean isPossibe(int x, int y) {
		if (x < 0 || x >= r)
			return false;
		if (y < 0 || y >= c)
			return false;

		return true;
	}

	static boolean meet() {

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nextr = cur[0] + dr[d];
				int nextc = cur[1] + dc[d];

				if (!isPossibe(nextr, nextc) || v[nextr][nextc])
					continue;

                v[nextr][nextc] = true;
                if(map[nextr][nextc].equals("X")){
                   
                    q2.add(new int[] { nextr, nextc });
                    continue;
                }
                    
                
                
				if (nextr == person.get(1)[0] && nextc == person.get(1)[1])
					return true;

				
				q.add(new int[] { nextr, nextc });
			}
		}
        q = q2;
        q2 = new ArrayDeque<>();
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		visited = new boolean[r][c];
		v = new boolean[r][c];
		Queue<int[]> land = new ArrayDeque<>();
		map = new String[r][c];
		for (int i = 0; i < r; i++) {
			String[] s = br.readLine().split("");
			for (int j = 0; j < c; j++) {
				map[i][j] = s[j];

				if ("L".equals(map[i][j])) {
					land.add(new int[] { i, j });
					person.add(new int[] { i, j });
				}

				if (".".equals(map[i][j])) {
					land.add(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}

        q.add(person.get(0));
		v[person.get(0)[0]][person.get(0)[1]] = true;
        
		int day = 0;
		while (true) {
			day++;
			int size = land.size();
			for (int i = 0; i < size; i++) {// 땅 주위 빙하 녹이기
				int[] cur = land.poll();
				for (int d = 0; d < 4; d++) {
					int nextr = cur[0] + dr[d];
					int nextc = cur[1] + dc[d];

					if (!isPossibe(nextr, nextc) || visited[nextr][nextc] || !map[nextr][nextc].equals("X"))
						continue;

					visited[nextr][nextc] = true;
					map[nextr][nextc] = ".";
					land.add(new int[] { nextr, nextc });
				}
			}

			if (meet()) { // 두사람 만나는지 확인
				break;
			}
		}

		System.out.println(day);
	}
}

