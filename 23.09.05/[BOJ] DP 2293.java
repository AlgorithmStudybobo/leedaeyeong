import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G5.2293 동전 1
/*
 *
 * input ------
 * 3 10
 * 5
 * 1
 * 2
 * 
 * arr ------
 * [1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1] -- 5
 * [1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3] -- 1
 * [1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 10] -- 2
 * 
 * 순서는 중요하지 않음 초기 시작값만 set하고 시작
 * 
 * 가치 5 동전은 5와 10이 될수 있고
 * 가치 1, 5 동전을 사용할 때  5원이 되는 경우는 5원 동전 하나, 1원 동전 5개로 2가지 경우
 * 10원 동전은 5 2개/ 5원 1개, 1원 5개 /1원 10개 로 3가지 경우를 가짐
 * 
 * 각 배열의 요소는 동전의 가치로 될 수 있는 경우를 가르키며
 * 코인의 가치가 바뀔때(반복문 순회) 이전에 동전으로 만들 수 있었던 경우의 수와 더해짐(될 수 있다면)
 * 
 * ans ------
 * 10
 */
public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, m;
	static int[] coin;
	
	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = stoi.apply(st.nextToken());
		m = stoi.apply(st.nextToken());

		coin = new int[n];
		for (int i = 0; i < n; i++) {
			coin[i] = stoi.apply(in.readLine());
		}

		out.write(digit(new int[m+1])+"");
		out.close();
	}

	private static int digit(int[] nums) {
		nums[0] = 1;
		for (int i = 0; i < coin.length; i++) {
			for (int j = coin[i]; j <= m; j++) {
				nums[j] += nums[j - coin[i]];
			}
		}
		return nums[m];
	}
}
// 메모리 : 14144KB 실행 시간 : 132ms