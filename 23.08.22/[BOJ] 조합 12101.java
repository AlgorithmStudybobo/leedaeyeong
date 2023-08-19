import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ S1.12101 1, 2, 3 더하기2

// 메모리 16416KB 실행 시간 : 180ms
// 주석 달게 없음

public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, m, cnt;
	static String s = "";

	public static void main(String[] args) throws IOException {
		input();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = stoi.apply(st.nextToken());
		m = stoi.apply(st.nextToken());
		
		cnt = 0;
		cal(0, s);

		System.out.println(s.length() == 0 ? -1 : String.join("+", s.split("")));
	}

	private static void cal(int num, String chk) {
		if (num > n) return;
		
		if (num == n) {
			if (++cnt == m) { s = chk; }
			return;
		}
		
		for (int i = 1; i <= 3; i++) {
			cal(num + i, chk + i);
		}
	}
}