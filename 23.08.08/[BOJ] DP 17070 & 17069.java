import java.io.*;
// BOJ G5.17070 파이프 옮기기1 & G4.17070 파이프 옮기기2
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int n, r, home[][];
	
	private static long input() throws IOException {
		n = Integer.parseInt(br.readLine());
		home = new int[n][];
		for(int i = 0; i < n; i++)
			home[i] = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt).toArray();
		
		return pipe(new long[3][n][n]);
	}
	
	private static long pipe(long[][][] dp) {
		// pipe 시작 위치 저장
		dp[0][0][1] = 1;

		// pipe의 시작위치 이후부터 탐색 시작
		for(int i = 0; i < n; i++) {
			for(int j = 2; j < n; j++) {

				// 벽을 만나면 건너뜀
				if(home[i][j] == 1) continue;

				// 가로 방향 진행
				dp[0][i][j] = dp[0][i][j-1] + dp[2][i][j-1];
				
				// 0번 행을 건너뛰어 세로와 대각선 연산의 outofbounds 방지
				if(i == 0) continue;
				dp[1][i][j] = dp[1][i-1][j] + dp[2][i-1][j];
				
				// 대각선 이동 조건 - 이전 위치와 현재 위치가 벽이 아닐 것(진행 불가)
				if(home[i-1][j] == 0 && home[i][j-1] == 0 && home[i][j] == 0) {
					dp[2][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];
				}
			}
		}
		return dp[0][n-1][n-1] + dp[1][n-1][n-1] + dp[2][n-1][n-1];
	}
	
	public static void main(String[] args) throws IOException {
		bw.write(input() + "");
		bw.close();
	}
}

// 02:01 ~ 02:50
// 14748 KB 144 ms

// 옮기기1은 반복문을 활용한 입력과 stream 입력이  132ms와 144ms로 큰차이 없음
// 옮기기2는 3 ^ 32 로 dfs 풀이 시 시간초과 dp는 32 ^ 2
// 옮기기2는 32*32 input에서 136ms와 160ms로 유의미한 시간차이 보임
// dp 점화식에서 고려해야될 부분은 현재의 위치보다 이전의 위치가 현재 올 수 있는지 따져봐야 하는거 같다