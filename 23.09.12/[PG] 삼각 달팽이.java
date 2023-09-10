// PG L2. 삼각 달팽이
/*
 * 23:30 ~ 23:50
 * 
 * 배열이 채워지는 순서는 아래, 오른쪽, 왼쪽 대각선 순
 * 이 순서를 지키되 배열 범위 밖으로 나가면 안됨
 * 
 * 아래와 오른쪽이동에 배열 범위 밖으로 나가는지 확인해주고
 * 왼쪽 대각선 이동시 요소가 0일때 값을 넣는다
 * 
 * 위 조건으로 인덱스가 0미만으로 내려가는지 확인할 필요가 없어짐
 * => 시작은 (0, 0) 이므로 이미 0이 아닌값으로 바뀜
 */
class Solution {
    static int[][] map;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, -1 } };
    
    public int[] solution(int n) {
        map = new int[n][n];
		int move = 1;
		int cnt = 0;
		int r = 0, c = 0, d = 0;
		
        while (cnt < n) {
            map[r][c] = move++;	
            if (r + dir[d][0] == n || c + dir[d][1] == n || map[r + dir[d][0]][c + dir[d][1]] != 0) {
                d = (d + 1) % 3;
                cnt++;
            }
            r += dir[d][0];
            c += dir[d][1];
        }
		
        int tmp = 0;
        int[] answer = new int[move-1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] != 0){
                    answer[tmp++] = map[i][j];
                }
            }
        }
        return answer;
    }
}