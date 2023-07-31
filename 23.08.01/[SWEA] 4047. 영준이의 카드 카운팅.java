import java.io.*;
import java.util.*;
// D3 4047. 영준이의 카드 카운팅
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int n;
	static char ascii = '0';
	
	public static String pick(char[] card, int[][] num) {
		StringBuilder sb = new StringBuilder();
		if(card.length > 156) return "ERROR";
		
		for(int i = 0; i < card.length-2; i+=3) {
			int trans = ((card[i+1]-ascii) * 10) + (card[i+2] - ascii);
			if(card[i] == 'S') { num[0][trans]++; }
			else if(card[i] == 'D') { num[1][trans]++; }
			else if(card[i] == 'H') { num[2][trans]++; }
			else { num[3][trans]++; }
		}
		
		for(int i = 0; i < 4; i++) {
			int cnt = 13;
			for(int j = 1; j <= 13; j++) {
				if(num[i][j] > 1) return "ERROR";
				else cnt -= num[i][j];
			}
			sb.append(cnt+" ");
		}
		
		return String.valueOf(sb);
	}
	
	
	public static void main(String[] args)throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			char[] card = br.readLine().toCharArray();
			int[][] num = new int[4][14];
		
			bw.write("#" + t + " " + pick(card, num) + "\n");
		}
        bw.close();
		}
}