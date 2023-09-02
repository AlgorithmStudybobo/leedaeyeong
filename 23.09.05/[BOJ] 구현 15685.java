import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G4.15685 드래곤 커브
public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, r, c;
	static boolean[][] map;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static List<Integer> curve = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 100 * 100 맵
		map = new boolean[101][101];
		n = stoi.apply(in.readLine());
		// 커브 갯수 == max(20)
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			c = stoi.apply(st.nextToken());
			r = stoi.apply(st.nextToken());
			int d = stoi.apply(st.nextToken());
			int g = stoi.apply(st.nextToken());
			
			// 초기 맵 셋팅 = 세대는 무조건 0세대 이상이므로 0세대를 만들고 시작
			map[r][c] = true;
			r += dx[d];
			c += dy[d];
			map[r][c] = true;
			
			// 다음 드래곤 커브를 위해 초기화
			curve.clear();
			/*  다음 세대는 이전 세대에서 역순으로 1이 추가되어 붙음(시계방향으로 90도가 끝점에 붙는다)
			 *  0 -> 0
			 *  1 -> 0 1
			 *  2 -> 0 1 2 1
			 *  3 -> 0 1 2 1 2 3 2 1
			 *  4 -> 0 1 2 1 2 3 2 1 2 3 0 3 2 3 2 1
			 */
			curve.add((d + 1) % 4);
			move(g);
		}
		
		out.write(total() + "");
		out.close();
	}

	private static void move(int g) {
		// 세대 수 만큼 반복(시작은 1세대 부터) 
		for (int i = 0; i < g; i++) {
			// 역순으로 탐색하며 맵에 마킹
			for (int j = curve.size() - 1; j >= 0; j--) {
				r += dx[curve.get(j)];
				c += dy[curve.get(j)];
				map[r][c] = true;
				curve.add((curve.get(j) + 1) % 4);
			}
		}

	}

	private static int total() {
		// 사각형 판별은 자기 자신과 우, 하, 우하 가 true이면 사각형 1개 추가
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
// 메모리 : 14344KB 실행 시간 : 136ms