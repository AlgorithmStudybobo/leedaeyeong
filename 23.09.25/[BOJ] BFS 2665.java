import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G4.2665 미로만들기
/*
 * 최초 접근 : 2206 문제처럼 3차원 배열을 선언해 이동하면서 벽을 만나면 다음 z축으로 이동하여
 * 문제의 요구사항에 만족 시 z축 값을 return하였음
 * -> 모든 방을 바꾸면서 지나가기 때문에 z축의 마지막 인덱스가 반환됨, z축 이동이후 해당 지점에서 완탐을 돌리는 방법을
 * 생각해 보았지만 시도 X 
 * 
 * 이동의 우선순위를 부여 -> 벽일때 부술수 있는데 벽이 아닌 경우를 먼저 처리 해줌 
 * Deque를 이용해 벽이 아닌경우 먼저 이동하기 위해 앞에 삽입하고 벽인경우 제일 뒤에 삽입하여
 * 벽이 아닌경우를 먼저 처리함
 * 
 */
public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, k;
	static final char ASCII = '0';

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = stoi.apply(in.readLine());
		int[][] room = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = in.readLine();
			for (int j = 0; j < n; j++) {
				room[i][j] = s.charAt(j) - ASCII;
			}
		}
		out.write(crack(room) + "");
		out.close();
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static int crack(int[][] room) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {0, 0, 0});
		room[0][0] = -1;
		
		while(!q.isEmpty()) {
			int[] nxt = q.poll();	
			if(nxt[0] == n - 1 && nxt[1] == n-1) {
				return nxt[2];
			}

			for(int d = 0; d < 4; d++) {
				int nx = nxt[0] + dx[d];
				int ny = nxt[1] + dy[d];
				if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				if(room[nx][ny] == 1) {
					q.offerFirst(new int[] {nx, ny, nxt[2]});
				}
				else if(room[nx][ny] == 0) {
					q.offer(new int[] {nx, ny, nxt[2] + 1});
				}
				room[nx][ny] = -1;
			}
		}
		return 0;
	}
}
// 메모리 : 14396KB 실행 시간 : 132ms