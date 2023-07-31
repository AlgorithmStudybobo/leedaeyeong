import java.io.*;
import java.util.*;
// D3 3499. 퍼펙트 셔플
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int n;
	static String[] card;

	public static String suffle() {
		StringBuilder sb = new StringBuilder();
		// Even case
		if(n % 2 ==0) { 
			int i = 0, cnt = 0;
			int even = n / 2;
			while(even - 1 >= cnt) {
				sb.append(card[i + cnt] + " ");
				i += even;
				if(i > n-1) {i %= n; cnt++;}
			}
		}
		// Odd case
		else {
			int i = 0, cnt = 0;
			int odd = n / 2 + 1;
			while(odd > cnt) {
				sb.append(card[i] + " ");
				i += odd;
				if(i > n-1) {i %= n; cnt++;}
			}
		}
		
		return String.valueOf(sb);
	}
	
	public static void main(String[] args)throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			card = new String[n];
			for(int i = 0; i < n; i++) {
				card[i] = st.nextToken();
			}
			bw.write("#" + t + " " + suffle() + "\n");
		}
        bw.close();
	}
}