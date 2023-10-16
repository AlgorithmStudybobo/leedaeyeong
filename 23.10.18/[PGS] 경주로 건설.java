import java.util.*;

// PGS L1. 바탕화면 정리
/*
 * 문제의 요구사항대로 구현함
 * 1. fees배열을 객체로 변환하여 접근을 용이하게 하고 method를 통해 출차시 요금을 계산해줌
 * 2. 입, 출차 관리 -> 객체와 Map구조를 통해 관리함 차가 들어오면 시간을 저장하고 출차시 시간을 계산함
 * 3. 정산 -> 맵에서 모든 value를 가져와 요금 계산 method를 통해 최종 정산
 * 4. stream을 통해 요구사항에 맞게 출력값으로 바뀜
 */
class Solution {
    public int[] solution(String[] wallpaper) {
        int fr = 52, fc = 52, lr = 0, lc = 0;
        int rLen = wallpaper.length;
        int cLen = wallpaper[0].length();

        for (int i = 0; i < rLen; i++) {
            String s = wallpaper[i];
            for (int j = 0; j < cLen; j++) {
                if (s.charAt(j) == '#') {
                    fr = Math.min(fr, i);
                    fc = Math.min(fc, j);
                    lr = Math.max(lr, i);
                    lc = Math.max(lc, j);
                }
            }
        }

        int[] answer = { fr, fc, lr + 1, lc + 1 };
        return answer;
    }
}