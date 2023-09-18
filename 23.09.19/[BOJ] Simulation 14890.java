import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G3.14890 경사로
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
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, l;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = stoi.apply(st.nextToken());
		l = stoi.apply(st.nextToken());

		int[][] map = new int[n][n];
		int[][] hMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = stoi.apply(st.nextToken());
				hMap[j][i] = map[i][j];
			}
		}

		out.write(String.valueOf(roadCount(map, 0) + roadCount(hMap, 0)));
		out.close();
	}

	private static int roadCount(int[][] map, int res) {
		for (int i = 0; i < n; i++) {
			boolean flag = true;
			int road = 1;

			for (int j = 0; j < n - 1; j++) {
				if (Math.abs(map[i][j] - map[i][j + 1]) > 1) {
					flag = false;
					break;
				}
				
				if (map[i][j] == map[i][j + 1]) {
					road++;
				}
				else if (map[i][j] + 1 == map[i][j + 1]) {
					if (road >= l) {
						road = 1;
					}
					else {
						flag = false;
						break;
					}
				}
				else if (map[i][j] == map[i][j + 1] + 1) {
					if (isIn(map, i, j)) {
						j += l - 1;
						road = 0;
					}
					else {
						flag = false;
						break;
					}
				}
			}
			
			if (flag) res++;
		}
		return res;
	}
	
	private static boolean isIn(int[][] map, int i, int j) {
		for (int k = j + 1; k < j + 1 + l; k++) {
			if (k >= n || map[i][k] != map[i][j + 1]) {
				return false;
			}
		}
		return true;
	}
}
// 메모리 : 14920KB 실행 시간 : 160ms