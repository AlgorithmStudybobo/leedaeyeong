import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G3.1005 ACM Craft
/*
 * 최단거리 == bfs
 * 
 * 벽을 부술수 있다 / 벽을 부수지 않는다 == 2개의 맵에 마킹
 * 벽을 부술수 있다면 빨리 부수고 가는게 최단거리라고 생각했음
 * 즉 부술수 있는 곳이면 바로 부수고 지나감 (boolean이든 정수든 벽을 부순걸 체크해줘서 다시 못 부수고 지나가게 함)
 * 
 * 인덱스 기준 n-1, m-1도착 시 지나온 거리를 반환
 * 벽을 부숴도 갈 수 없으면 while문이 종료되어 -1반환됨
 * 
 */
public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, k;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
        int t = stoi.apply(in.readLine());
		for(int tc = 0; tc < t; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
            n = stoi.apply(st.nextToken());
			k = stoi.apply(st.nextToken());
			
			
            int[] buildTime = new int[n+1];
            st = new StringTokenizer(in.readLine());
			for(int i = 1; i <= n; i++) {
				buildTime[i] = stoi.apply(st.nextToken());
			}
			
			List<Integer>[] order = new ArrayList[n+1];
			for(int i = 1; i <=n; i++) {
				order[i] = new ArrayList<>();
			}
			
			int[] sequence = new int[n+1];
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(in.readLine());
				int first = stoi.apply(st.nextToken());
				int second = stoi.apply(st.nextToken());
				order[first].add(second);
				sequence[second]++;
			}
			
			int target = stoi.apply(in.readLine());
			out.write(topology(target, buildTime, sequence, order) + "\n");
			out.flush();
		}
	}

	private static int topology(int target, int[] buildTime, int[] sequence, List<Integer>[] order) {
		Queue<Integer> q = new ArrayDeque<>();
		int[] time = new int[n+1];
		
		for(int i = 1; i <= n; i++) {
			if(sequence[i] == 0) {
				time[i] = buildTime[i];
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == target) {
				return time[cur];
			}
			for(int num : order[cur]) {
				time[num] = Math.max(buildTime[num] + time[cur], time[num]);
				if(--sequence[num] == 0) {
					q.offer(num);
				}
			}
		}
		return time[n];
	}
}
// 메모리 : 250400KB 실행 시간 : 1004ms