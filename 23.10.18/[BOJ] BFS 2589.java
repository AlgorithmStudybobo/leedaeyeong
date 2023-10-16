import java.io.*;
import java.util.*;
import java.util.function.Function;

// BOJ G5.2589 보물섬
/*
 * bfs 2번 사용 'L'이면서 방문하지 않은 지점일 때 queue에 넣고
 * 1번째 bfs시작 1번째 bfs에서 해당 지점 좌표를 가지고 2번째 bfs 순회
 * 
 * bfs로 최단 경로를 return 받을 수 있으며 보물 사이 거리는 가장 긴 시간이
 * 걸리는 두 지점이다 즉 bfs로 받은 값중 가장 높은값을 갱신하며 완전 탐색을 하면
 * 정답을 찾을 수 있음
 */
public class Main {

	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, m;
	static int[] point;
	static char[][] map;
	static boolean[][] visited;
	static Queue<int[]> q;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = stoi.apply(st.nextToken());
		m = stoi.apply(st.nextToken());

		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = in.readLine().toCharArray();
		}
		point = new int[3];
		visited = new boolean[n][m];
		int res = 0;
		q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L' && !visited[i][j]) {
					visited[i][j] = true;
					q.offer(new int[] { i, j });
					find();
					visited = new boolean[n][m];
					res = Math.max(res, point[2]);
				}
			}
		}
		out.write(res + "");
		out.close();
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void find() {
		while (!q.isEmpty()) {
			int[] mv = q.poll();
			point = mv;
			search();
			for (int i = 0; i < 4; i++) {
				int nx = mv[0] + dx[i];
				int ny = mv[1] + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny])
					continue;
				if (map[nx][ny] == 'L') {
					visited[nx][ny] = true;
					q.offer(new int[] { nx, ny });
				}
			}
		}
	}

	private static void search() {
		q.offer(new int[] { point[0], point[1], 0 });
		while (!q.isEmpty()) {
			int[] mv = q.poll();
			point = mv;
			visited[point[0]][point[1]] = true;
			for (int i = 0; i < 4; i++) {
				int nx = mv[0] + dx[i];
				int ny = mv[1] + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny])
					continue;
				if (map[nx][ny] == 'L') {
					visited[nx][ny] = true;
					q.offer(new int[] { nx, ny, mv[2] + 1 });
				}
			}
		}
	}

}
// 메모리 : 221080KB 실행 시간 : 476ms