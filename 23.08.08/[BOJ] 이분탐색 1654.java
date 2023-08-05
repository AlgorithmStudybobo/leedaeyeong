import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
// BOJ S2.1654 랜선 자르기
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static long k, n, lan[];

    private static long cut(long size) {
		return Arrays.stream(lan).map(x -> x/size).sum();
	}
    
    private static long binary(long max) {
		// 범위는 가장 긴 랜선 기준
    	long l = 1, r = max, m = 0, ans = 0;
		while(l <= r) {
			m = (l + r) / 2; // 랜선 자르기
    		if(cut(m) >= n) {ans = m; l = m + 1;} // 길게 자르기
    		else r = m - 1; // 짧게 자르기
    	}
    	return ans;
    }
    
    public static void main(String[] args) throws IOException {
    	st = new StringTokenizer(br.readLine());
    	k = Long.parseLong(st.nextToken());
    	n =  Long.parseLong(st.nextToken());
    	long max = 0;
    	lan = new long[(int) k]; // long 배열 선언시 배열 개수는 명시적 형변환
    	for(int i = 0; i < k; i++) {
    		lan[i] = Long.parseLong(br.readLine());
    		max = lan[i] > max ? lan[i] : max;
    	}
    	bw.write(binary(max) + "");
    	bw.close(); br.close();
    }
}

// 22 : 17 ~ 22: 57
// 메모리 : 17976 kb 시간 : 232ms
// stream 적용하느라 구현에 시간을 좀 더 사용하였다.
// stream 연산으로 시간 손해를 본듯... 이분탐색 연습에 좋은 문제

// 1회 틀렸는데 long 타입 및 ans 리턴 조건을 잘못 생각했다
// if의 l = m + 1 연산을 while조건 이후 그냥 넘겨줘서 풀이 해가 안나옴
// if 조건에서 풀이 해 m을 ans에 대입하여 해결

// 코드 전체를 통일시키기 위해 전부 long타입으로 바꿨지만 굳이 싶긴하다.
// int에서 long은 묵시적 형변환이 되니깐 long타입이 필요없는 cut method는 int타입으로 구현하면
// 좀 더 효율적 일 것 같다.