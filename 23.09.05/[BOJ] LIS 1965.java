import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ S2.1965 상자넣기
public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n;
	static int[] nums, target;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = stoi.apply(in.readLine());

		nums = new int[n];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = stoi.apply(st.nextToken());
		}
        
		target = new int[n];
		out.write(box(0, 0)+"");
		out.close();
	}

	private static int box(int idx, int cnt) {
		// 상자를 담을 초기 값 셋팅
		target[cnt] = nums[idx];
		while (++idx < n) {
			// 상자의 크기를 담은 배열을 순회하면서 상자를 담을 배열보다 크기가 크면 담는다
			if (nums[idx] > target[cnt]) {
				target[++cnt] = nums[idx];
			}
			// 크기가 작다면 몇번째 위치에 담을 수 있는지 확인
            else {
				target[lower(0, cnt, nums[idx])] = nums[idx];
			}
		}
		return cnt + 1;
	}

	
	private static int lower(int s, int e, int v) {
		int m = 0;
		// 중간값은 상자가 담길 위치가 아닌 찾아야되는 상자보다 작은 상자의 위치를 가르킨다
        while (s < e) {
			m = (s + e) / 2;
			if (target[m] >= v) {
				e = m;
			} 
			else {
				s = m + 1;
			}
		}
        // s, e 둘중 아무거나 return해도 상관없음
		return e;
	}
}
// 메모리 : 14448KB 실행 시간 : 136ms