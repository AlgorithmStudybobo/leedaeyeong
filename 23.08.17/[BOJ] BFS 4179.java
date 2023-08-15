import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G4.4179 불!
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Function<String, Integer> stoi = Integer::parseInt;
	static StringTokenizer st;
	static int n, m, fire[][], move[][];
	static char map[][];
	static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static Queue<int[]> q = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		input();
		bw.close();
	}

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = stoi.apply(st.nextToken());
		m = stoi.apply(st.nextToken());

		// 배열 입력
		map = new char[n][m];
		for (int i = 0; i < n; i++)
			map[i] = br.readLine().toCharArray();

		// 불이 퍼지는 시간을 우선 계산
		fire = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (map[i][j] == 'F') {
					fire[i][j] = 1; // 불 위치 마킹
					q.add(new int[] { i, j });
				}
		
		fire();

		// 지훈이의 이동시간 계산
		move = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (map[i][j] == 'J') {
					move[i][j] = 1; // 지훈이 위치 마킹
					q.add(new int[] { i, j });
					
					bw.write(success());
					return;
				}
	}

	private static void fire() {
		while (!q.isEmpty()) {
			int[] f = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = f[0] + dir[i][0];
				int ny = f[1] + dir[i][1];

				// 불이 배열 범위로 나가는 것을 체크
				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				
				// 이미 불이 붙은 곳, 벽 체크
				if (fire[nx][ny] > 0 || map[nx][ny] == '#')
					continue;

				// 불이 확산하면서 퍼짐
				fire[nx][ny] = fire[f[0]][f[1]] + 1;
				q.add(new int[] { nx, ny });
			}
		}
	}

	private static String success() {
		while (!q.isEmpty()) {
			int[] f = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = f[0] + dir[i][0];
				int ny = f[1] + dir[i][1];

				// 지훈이 탈출
				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					return move[f[0]][f[1]] + "";
				
				// 지훈이 이동 시간이 이미 마킹되있는지 & 벽 체크
				if (move[nx][ny] > 0 || map[nx][ny] == '#')
					continue;
				
				// 불이 없는곳과 이미 불이 확산된 곳은 갈 수 없음
				if (fire[nx][ny] != 0 && move[f[0]][f[1]] + 1 >= fire[nx][ny])
					continue; //
				
				// 지훈이 이동
				move[nx][ny] = move[f[0]][f[1]] + 1;
				q.add(new int[] { nx, ny });

			}
		}

		return "IMPOSSIBLE";
	}
}

// 실 구현만 따지면 3시간쯤 걸린거 같다
// 메모리 : 56380 KB 시간 : 552ms
// 고려해야될 조건이 좀 많아서 어려움 여러 조건을 미리 잘 정리 했으면 수월했을듯 싶다