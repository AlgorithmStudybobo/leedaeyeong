import java.util.*;

// PGS L2. 귤 고르기
/*
 * 21:40 ~ 22:17
 * 
 * LRU 적용 문제
 * 
 * 배열 안에 같은 값이 없으면 cache miss, 있으면 cache hit
 * 
 * String 배열의 경우 선언 후 초기 값은 null이므로 문제의
 * input에 해당하지 않는 값으로 초기화 
 * 
 * 문제에서 대, 소문자 구분이 없다고 하였으므로 대문자나 소문자로
 * 치환하여 같은 값인지 체크
 * 
 * match의 값이 변한다면 cache hit이므로 1부터 match까지 밀어주고
 * 변하지 않으면 cache miss이므로 1부터 배열의 끝까지 밀어준다
 * 
 * idx = 0은 가장 최근 들어온 페이지를 의미한다. -> 배열의 마지막 요소가 가장 먼저 교체될 페이지
 * 
 * 배열로 solve시 cacheSize가 0이 들어오면 인덱스 에러가 발생 -> 0이면 모든 요소에 cache miss가 생김: return 배열 길이 * 5
 */
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Arrays.sort(tangerine);

        int len = tangerine.length;
        int max = tangerine[len - 1];

        int[] size = new int[max + 1];
        for (int box : tangerine) {
            size[box]++;
        }

        Arrays.sort(size);
        for (int i = max; i >= 0; i--) {
            if (k <= 0) {
                return max - i;
            }
            k -= size[i];
        }
        return answer;
    }
}