import java.util.*;
// PGS L3. 두 큐 합 같게 만들기
/* 
 * 배열의 합이 큰 쪽에서 원소를 하나 빼서 작은쪽에 넣으면
 * 가장 적은 횟수로 같은 값이 될 수 있을 것이라 생각함
 * 
 * -1이 나오는 경우 : 2개의 배열의 길이의 합만큼 순회해도 같은 값을 찾지 못하면
 * 같은 값이 될 수 없는 입력이 들어왔을 것이라 생각 -> *2만큼 돌아도 최악의 경우 120만번 연산이며
 * 반례를 찾진 못했지만 같은값을 찾으면 연산횟수가 바로 return되면서 종료되기 때문에 상관없다고 생각함
 */
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> one = new LinkedList<>();
        Queue<Integer> two = new LinkedList<>();

        for (int i = 0; i < queue1.length; i++) {
            one.add(queue1[i]);
            two.add(queue2[i]);
        }

        int answer = 0;
        long first = Arrays.stream(queue1).sum();
        long second = Arrays.stream(queue2).sum();

        int cnt = (queue1.length + queue2.length) * 3;
        while (cnt-- > 0) {
            if (first == second) {
                return answer;
            }

            if (first < second) {
                int tmp = two.poll();
                one.add(tmp);
                first += tmp;
                second -= tmp;
            } else {
                int tmp = one.poll();
                two.add(tmp);
                first -= tmp;
                second += tmp;
            }
            answer++;
        }
        return -1;
    }
}