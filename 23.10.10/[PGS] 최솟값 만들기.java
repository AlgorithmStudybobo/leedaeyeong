import java.util.*;

// PGS L2. 최솟값 만들기
/* 
 * 배열의 합이 큰 쪽에서 원소를 하나 빼서 작은쪽에 넣으면
 * 가장 적은 횟수로 같은 값이 될 수 있을 것이라 생각함
 * 
 * -1이 나오는 경우 : 2개의 배열의 길이의 합만큼 순회해도 같은 값을 찾지 못하면
 * 같은 값이 될 수 없는 입력이 들어왔을 것이라 생각 -> *2만큼 돌아도 최악의 경우 120만번 연산이며
 * 반례를 찾진 못했지만 같은값을 찾으면 연산횟수가 바로 return되면서 종료되기 때문에 상관없다고 생각함
 */
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int len = A.length;
        for (int i = 0; i < len; i++) {
            answer += A[i] * B[len - 1 - i];
        }

        return answer;
    }
}
