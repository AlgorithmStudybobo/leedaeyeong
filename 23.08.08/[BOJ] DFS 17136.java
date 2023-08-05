import java.io.*;
import java.util.*;
import java.util.Arrays;
// BOJ G2.17136 색종이 붙이기
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int ans = Integer.MAX_VALUE;
	static int paper[] = {5, 5, 5, 5, 5},  squere[][] = new int[10][];
	
	private static int input() throws IOException {
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			squere[i] = Arrays.stream(br.readLine().split(" "))
							  .mapToInt(Integer::parseInt).toArray();
			
			cnt += Arrays.stream(squere[i]).filter(x -> x == 1).count();
		}

		// 배열에 1만 있으면 4개만 필요
		if (cnt == 100) return 4;

		// 배열에 1이 없으면 0개 필요
		if (cnt == 0) return 0;
		
		// 탐색
		find(0, 0);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}
	
	private static void find(int x, int y) {
		// 기저 조건
		// 유도 조건에서 종이를 붙여보고 탐색을 마치는 지점에서 작은 값을 저장함
		// x가 9이고 y가 9를 넘어가는 지점이 탐색 종료 지점
		if (x >= 9 && y > 9) { ans = find(); return; }
		
		// 다음 지점탐색
		if (y > 9) { find(x + 1, 0); return; }
		
        if (squere[x][y] == 1) {
			// 5,4,3,2,1 순으로 종이가 붙음
			for (int i = 5; i >= 1 ; i--) {

				// 종이가 0개보다 많으면 붙일 수 있음
				if (paper[i-1] > 0) {
					paper[i-1]--;

					// 종이를 붙일 공간이 있는지 확인
					if (check(x, y, i)) {

						// 종이 붙이기(방문 배열을 선언하지 않고 -1을 곱해 방문 처리를 대신함)
						visited(x, y, i);

						// 다음 종이를 붙이러 감
						find(x, y + 1);

						// 붙였던 종이를 때서 다른 종이를 붙일 수 있게 함
						visited(x, y, i);
						}

					// 종이 회수
					paper[i-1]++;
				}
			}
		}
		// 종이 붙일 공간이 아니면 다음 지점 탐색
		else find(x, y + 1);
	}
	
	// 몇장의 종이가 필요한지 반환
	private static int find() {
		return Math.min(ans, Arrays.stream(paper).reduce(25, (x, y) ->  x - y));
	}

	// 종이를 붙일 수 있는지 확인 method
	private static boolean check(int x, int y, int n) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (x + i >= 10 || y + j >= 10 || squere[x+i][y+j] != 1) return false;
		return true;
	}
	
	// 종이를 붙이는 method
	private static void visited(int x, int y, int n) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				squere[x+i][y+j] *= -1;
	}
	
	public static void main(String[] args) throws IOException {
		bw.write(input() + "");
		bw.close();
	}
}

// 4시간쯤
// 26516 KB 376 ms
// 어려운 문제였다 고려해야될 조건때문에 디버깅에 시간이 많이 소요됐다.
// stream으로 인해 시간이 70 ~ 100 정도 더 소요되는거 같다.