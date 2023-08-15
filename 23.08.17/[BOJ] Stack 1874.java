import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ S2.1874 스택수열
public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb;
	static int n;

	public static void main(String[] args) throws IOException {
		input();
		bw.close();
	}

	private static void input() throws IOException {
		n = stoi.apply(br.readLine());
		
		int[] chk = new int[n];
		for(int i = 0; i < n; i++) {
			chk[i] = stoi.apply(br.readLine());
		}

		bw.write(number(nums, chk));
	}
	
	private static String number(int[] nums, int[] chk) {
		ArrayDeque<Integer> stack = new ArrayDeque<>(); // 스택 수열
		sb = new StringBuilder();
		
		int idx = 0;
		// 저장한 수열을 순차적으로 확인
		for(int n : chk) {

			// 수열과 현재 값이 맞지 않는다면 수열의 값만큼 스택에 저장
			while(idx < n) {
				stack.offerLast(++idx);
				sb.append("+\n");
			}

			// 수열과 현재 값이 맞으면 pop
			if(stack.peekLast() == n) {
				stack.pollLast();
				sb.append("-\n");
			}
		}

		// 입력된 수열과 같은 형태로 빠져나오면 스택이 비어있고 아니면 스택에 값이 남아있음
		return stack.isEmpty() ? sb.toString() : "NO";
	}
}

// 1시간 쯤..
// 메모리 : 29100KB 실행 시간 : 380ms
// 제출 전 반례 체크하려고 반례게시판 들어갔다가 2 1 1 이라는 반례에 낚임
// 문제 다시 체크하고 그냥 제출함
// 문제 조건을 상기하자....