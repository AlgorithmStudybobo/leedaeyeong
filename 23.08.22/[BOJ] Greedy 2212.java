import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
// BOJ G5.2212 센서
public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, k;

	public static void main(String[] args) throws IOException {
		input();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = stoi.apply(br.readLine());
		k = stoi.apply(br.readLine());
		
		int[] sensor = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			sensor[i] = stoi.apply(st.nextToken());
		}

		// 오름차 순 정렬(내림차로 하면 인덱스를 편하게 쓸 수 있지만 참조형 타입으로 형 변환 해야됨)
		Arrays.sort(sensor);

		bw.write(install(sensor) + "");
		bw.close();
	}

	private static int install(int[] sensor) {
		// 내림차 순 정렬을 위해 wrapper타입 선언
		Integer[] point = new Integer[n - 1];

		for (int i = n - 2; i >= 0; i--) {
			// 뒤에서 부터 빼주면서 내려옴
			point[i] = sensor[i + 1] - sensor[i];
		}

		// 내림차 순 정렬(거리가 큰 순으로 나열됨)
		Arrays.sort(point, Comparator.reverseOrder());
		
		// 빼주면서 겹친거 제거 후 return
		return IntStream.range(k - 1, n - 1).map(i -> point[i]).sum();
	}
}

// 20 ~ 30분쯤