import java.util.*;

// PGS L3. 연속 펄스 부분 수열의 합
/*
 * dp - 20 ~ 30분
 * 
 * 2개 배열을 사용
 * 첫번째 배열에 1, -1, 1... 순으로 곱한 값 저장
 * 두번째 배열에 -1, 1, -1... 순으로 곲한 값 저장
 * 
 * 바텀 업 방식으로 들어올 수 있는 2차원 배열에 최대값을 갱신하고
 * 정답을 출력할 변수에 max값을 저장한다
 * 
 * 이때 반복문이 1부터 시작하여 n - 1에서 종료되므로 배열의 길이가 1일때 값을 저장할 수 없음
 * (case 2) -> 입력 배열의 length가 1일때 전처리 배열에서 최대값을 return해줌
 * 
 */
class Solution {
    public long solution(int[] sequence) {

        int len = sequence.length;

        long[][] memo = new long[2][len];
        long[] one = cal(sequence, len, 1);
        long[] mOne = cal(sequence, len, -1);

        if (len == 1) {
            return Math.max(one[0], mOne[0]);
        }

        memo[0][0] = one[0];
        memo[1][0] = mOne[0];

        long answer = Long.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            memo[0][i] = Math.max(memo[0][i - 1] + one[i], one[i]);
            memo[1][i] = Math.max(memo[1][i - 1] + mOne[i], mOne[i]);
            answer = Math.max(answer, Math.max(memo[0][i], memo[1][i]));
        }
        return answer;
    }

    private long[] cal(int[] sequece, int len, int num) {
        long[] tmp = new long[len];
        for (int i = 0; i < len; i++) {
            tmp[i] = sequece[i] * num;
            num *= -1;
        }
        return tmp;
    }
}