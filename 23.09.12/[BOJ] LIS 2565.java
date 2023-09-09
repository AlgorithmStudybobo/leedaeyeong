import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G5.2565 전깃줄
/*
 * input <= 100 (이분탐색까지 할 필요 없음)
 * 없애야 하는 전깃줄의 최소 == 연결된 전기줄 - 가장 많이 연결될 수 있는 전깃줄
 * 
 * a 전봇대가 정렬된 상태에서 한개의 a전봇대의 위치 기준으로 이전에 연결된 위치에서 뻗어 나간
 * 전깃줄의 위치를 보며 기준 위치보다 작다면 교차하지 않음
 * ex_ a전봇대 3 -> 7 일때 1 -> 3, 2 -> 5 면 교차하지 않는것
 * 
 * lan[i]가 1로 시작하는 이유 == 연결되어 있으니까
 * 
 */
public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = stoi.apply(in.readLine());
		int[][] eletric = new int[n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			eletric[i][0] = stoi.apply(st.nextToken());
			eletric[i][1] = stoi.apply(st.nextToken());
		}
		
		Arrays.sort(eletric, Comparator.comparingInt((o)-> o[0]));
		System.out.println(n - cut(eletric, new int[n]));
	}

	private static int cut(int[][] eletric, int[] lan) {
		for(int i = 0; i < n; i++ ) {
			lan[i] = 1;
			for(int j = 0; j < i; j++) {
				if(eletric[i][1] > eletric[j][1]) {
					lan[i] = Math.max(lan[i], lan[j]+1);
				}
			}
		}
		return Arrays.stream(lan).max().getAsInt();
	}
}