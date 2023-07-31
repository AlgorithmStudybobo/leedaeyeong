import java.io.*;
import java.util.*;
// 5356. 의석이의 세로로 말해요
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static String strAry(char[][] sAry) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				if (sAry[j][i] != '\u0000') sb.append(sAry[j][i]);
			}
		}
		return String.valueOf(sb);
	}
	
	public static void main(String[] args)throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			char[][] sAry = new char[15][15];
			for(int i = 0; i < 5; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j = 0; j < tmp.length; j++) {
					sAry[i][j] = tmp[j];
				}
			}
			bw.write("#" + t + " " + strAry(sAry) + "\n");
		}
        bw.close();
        br.close();
		}
}