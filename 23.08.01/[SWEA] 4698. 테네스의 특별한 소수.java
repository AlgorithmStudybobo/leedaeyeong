import java.io.*;
import java.util.*;
// 4698. 테네스의 특별한 소수
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
     
    static boolean[] prime = new boolean[1000001];
    public static void che() {
        int chk = (int)Math.sqrt(1000000) + 1;
        prime[1] = true;
         
        for(int i = 2; i <= chk; i++) {
            if(prime[i]) continue;
            for(int j = i+i; j <= 1000000; j+=i) {
                prime[j] = true;
            }
        }
    }
     
    public static void main(String[] args)throws IOException {
        che();
         
        int T = Integer.parseInt(br.readLine());        
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
 
            int cnt = 0;
            for(int i = start; i <= end; i++) {
                if(!prime[i]) {
                    String tmp = String.valueOf(i);
                    for(int j = 0; j < tmp.length();j++) {
                        if (tmp.charAt(j) - '0' == num) {cnt++; break;}
                    }
                }
            }
            bw.write("#" + t + " " + cnt + "\n");
        }
        bw.close();
    }
}