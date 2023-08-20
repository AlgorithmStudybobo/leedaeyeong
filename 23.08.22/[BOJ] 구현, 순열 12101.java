import java.io.*;
import java.util.*;
import java.util.function.Function;
// BOJ G4.17281 ⚾

// 문제 지문부터 쓰레기
// 00시 ~ 05시 밤샘

public class Main {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int n, out, point = 0;
	static int[] player;
	static boolean[] pick;
	static int[][] game;

	public static void main(String[] args) throws IOException {
		input();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = stoi.apply(br.readLine());
		
		game = new int[n][10];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			// 아래 순열 배열에 1 ~ 9 까지 담았으므로 인덱스를 맞추기 위해 1 ~ 9 인덱스에 정보 담음
			for (int j = 1; j < 10; j++) {
				game[i][j] = stoi.apply(st.nextToken());
			}
		}

		// 1번 선수부터 9번 선수까지
		player = new int[10];
		pick = new boolean[10];
		player[4] = 1;
		pick[4] = true;
		
		choose(2);
		bw.write(point+"");
		bw.close();
	}

	private static void choose(int cnt) {
		if (cnt == 10) {
			point = Math.max(point, baseball());
			return;
		}
		
		for (int i = 1; i < 10; i++) {
			// input에서 이미 true 처리 해서 4번 제외 순열 입력받음
			if (pick[i]) continue;
			pick[i] = true;
			// 인덱스를 i로 cnt를 값으로 넘겨야 제대로 순열 뽑음(4번타자는 1번 선수 때문에)
			player[i] = cnt;
			choose(cnt + 1);
			pick[i] = false;
		}
	}

	private static int baseball() {
		// 게임 점수
		int tmp = 0;
		// 첫번째 인덱스의 선수를 뽑아옴
		int hit = 1;

		for (int round = 0; round < n; round++) {
			// 아웃 카운트 초기화
			out = 0;
			boolean[] base = new boolean[4];

			check: while (true) { // <- 이거 좋은듯 파이썬 버릴 이유 하나 찾음 (검색해서 찾음)
				for (int i = hit; i < 10; i++) {
					switch (game[round][player[i]]) {
					// 아웃 카운트
					case 0:
						out++; break;
					// 안타일때 3번 베이스에 있는 사람 보내고 나머지 1루씩 진출
					case 1:
						for (int r = 3; r > 0; r--) {
							if (base[r]) {
								if (r == 3) {
									tmp++;
									base[r] = false;
									continue;
								}
								base[r + 1] = true;
								base[r] = false;
							}
						}
						base[1] = true;
						break;
					// 2번과 3번 베이스는 홈으로 1번과 타자는 3루와 2루로
					case 2:
						for (int r = 3; r > 0; r--) {
							if (base[r]) {
								if (r == 2 || r == 3) {
									tmp++;
									base[r] = false;
									continue;
								}
								base[r + 2] = true;
								base[r] = false;
							}
						}
						base[2] = true;
						break;
					// 타자는 3번 베이스 나머지는 홈으로
					case 3:
						for (int r = 3; r > 0; r--) {
							if (base[r]) {
								tmp++;
								base[r] = false;
							}
						}
						base[3] = true;
						break;
					// 전부 다 홈으로
					case 4:
						for (int r = 1; r < 4; r++) {
							if (base[r]) {
								tmp++;
								base[r] = false;
							}
						}
						tmp++;
						break;
					}
					// 삼진아웃 다음 선수 선발 & 10명을 넘으면 안되므로 1번으로 초기화
					if (out == 3) {
						hit = i + 1;
						if (hit == 10) {
							hit = 1;
						}
						// 외부 while문으로 나가기때문에 hit가 1로 초기화 안됨 -> 이전 번호의 다음 번호
						break check;
					}
				}
				// 9번 선수까지 출전하면 다시 1번 선수부터
				hit = 1;
			}
		}
		return tmp;
	}
}