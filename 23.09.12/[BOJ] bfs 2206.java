import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G3.2206 벽 부수고 이동하기
/*
 * 최단거리 == bfs
 * 
 * 벽을 부술수 있다 / 벽을 부수지 않는다 == 2개의 맵에 마킹
 * 벽을 부술수 있다면 빨리 부수고 가는게 최단거리라고 생각했음
 * 즉 부술수 있는 곳이면 바로 부수고 지나감 (boolean이든 정수든 벽을 부순걸 체크해줘서 다시 못 부수고 지나가게 함)
 * 
 * 인덱스 기준 n-1, m-1도착 시 지나온 거리를 반환
 * 벽을 부숴도 갈 수 없으면 while문이 종료되어 -1반환됨
 * 
 */
public class Main {
	static class Move {
		int r, c, crack, dis;
		public Move(int r, int c, int crack, int dis) {
			this.r = r;
			this.c = c;
			this.crack = crack;
			this.dis = dis;
		}
	}

	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, m;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = stoi.apply(st.nextToken());
		m = stoi.apply(st.nextToken());

		String s = "";
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			s = in.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		out.write(crack(new boolean[2][n][m]) + "");
		out.close();
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static int crack(boolean visited[][][]) {
		Queue<Move> q = new ArrayDeque<>();
		q.add(new Move(0, 0, 0, 1));
		while (!q.isEmpty()) {
			Move mv = q.poll();
			if (mv.r == n - 1 && mv.c == m - 1) {
				return mv.dis;
			}
			for (int i = 0; i < 4; i++) {
				int nx = mv.r + dx[i];
				int ny = mv.c + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[mv.crack][nx][ny])
					continue;
				if (map[nx][ny] == 0) {
					visited[mv.crack][nx][ny] = true;
					q.offer(new Move(nx, ny, mv.crack, mv.dis + 1));
				} 
				else if (map[nx][ny] == 1 && mv.crack == 0) {
					visited[mv.crack][nx][ny] = true;
					q.offer(new Move(nx, ny, mv.crack + 1, mv.dis + 1));
				}
			}
		}
		return -1;
	}
}
// 메모리 : 14372KB 실행 시간 : 132ms