import java.io.*;
import java.util.*;
// 4789. 성공적인 공연 기획
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static char ascii = '0';
    
    public static int clap(String s) {
        int cnt = 0, hire = 0;
        for(int i = 0; i < s.length(); i++) {
            int n = s.charAt(i) - ascii;
            if (n == 0) continue;
            if(cnt < i) { hire += i - cnt; cnt = i + n;}
            else  {cnt += n;}
        }
        return hire;
    }
    
    public static void main(String[] args)throws IOException {
        int T = Integer.parseInt(br.readLine());        
        for(int t = 1; t <= T; t++) {
            String s = br.readLine();
            bw.write("#"+ t + " " + clap(s) + '\n');
            bw.flush();
        }
        bw.close();
    }
}