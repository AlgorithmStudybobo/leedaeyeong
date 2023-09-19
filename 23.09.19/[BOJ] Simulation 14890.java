import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G3.14890 경사로
/*
 * 현재위치와 앞의 요소를 비교하며 반복문 순회
 * 
 * 현재위치 == 현재위치 + 1 인경우 지나온 길을 확인하기위해 cnt++
 * 
 * 현재위치 < 현재위치 + 1 인경우 cnt값이 경사로를 설치할 수 있는 길이인 l보다 크다면
 * cnt를 1로 초기화(경사로의 최소 길이는 1이므로)
 * 
 * 현재위치 > 현재위치 + 1 인경우 현재위치 + 1의 요소부터 경사로 설치 길이만큼 순회하며
 * 설치가 가능한 경우 설치하고 cnt를 0으로 초기화(경사로 길이 - 1 만큼 현재 위치를 조정 하기 때문)
 * 
 * 경사로 설치가 불가능한 경우 내부 반복문을 빠져나온다.
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