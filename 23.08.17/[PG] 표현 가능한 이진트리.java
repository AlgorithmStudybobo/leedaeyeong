import java.io.*;
import java.util.*;
// PG L3. 표현 가능한 이진트리

// 15일 22 : 20 ~ 16일 02 : 17

// 아래 5점 코드가지고 씨름하다가 잘못된 부분을 도저히 못찾겠어서
// 해설 코드 봤는데 차이가 트리 배열을 method 구분 했는지 안했는지 밖에 없음
// 뭐가 문제인지 몰라서 한참 돌리다가 tree 재선언 안한거 발견해서 수정함

//--------------------------- 정답 ---------------------------
class Solution {
    static int tree, chk;
    static boolean[] binTree;
    
	public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < answer.length; i++){
            chk = 1;
			tree = 0;
            binTree = binary(numbers[i]);
            search(0, tree-1, false);
            answer[i] = chk;
        }
        return answer;
    }
    
    public static void search(int s, int e, boolean flag) {
		int m = (s + e) / 2;
		if(flag && binTree[m]) { // 루트노드 찾는데 루트노드가 0인데 자식노드가 1이면 포화 이진트리가 아님
            chk = 0;
            return;
        }
        if(s != e) {
			search(s, m-1, !binTree[m]);
			search(m+1, e, !binTree[m]);
		}
	}

	private static boolean[] binary(long num) {
		int tmp = 1;
		String b = Long.toBinaryString(num);
		while(b.length() > tree) {
			tree = (int) Math.pow(2, tmp++) - 1; 
		}
		boolean[] bin = new boolean[tree];
		int point = tree - b.length(); // 포화 이진트리 시작지점 설정(앞에는 0으로 채움)
        for(int i = 0; i < b.length(); i++) {
            bin[point++] = b.charAt(i) == '1';
        }
		return bin; 
	}
}


// ------------------------------5점--------------------------
class Solution {
    static int tree, chk;
    static boolean[] binTree;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < answer.length; i++){
            chk = 1;
            binTree = binary(numbers[i]);
            search(0, tree-1, false);
            answer[i] = chk;
        }
        return answer;
    }

    private static void search(int s, int e, boolean flag) {
		int m = (s + e) / 2;
		if(flag && binTree[m]) { // return 0 안됨 (재귀라 뭍힘)
            chk = 0;
            return;
        }
        if(s != e) { // > 안됨(오른쪽 탐색시 s가 커짐)
			search(s, m-1, !binTree[m]);
			search(m+1, e, !binTree[m]);
		}
	}
    
    private static boolean[] binary(long num) { // 여기 
		int tmp = 1;
		String b = Long.toBinaryString(num);
		while(b.length() > tree) {
			tree = (int) Math.pow(2, tmp++) - 1; 
		}
		boolean[] bin = new boolean[tree];
		int point = tree - b.length();
        for(int i = 0; i < b.length(); i++) {
            bin[point++] = b.charAt(i) == '1';
        }
		return bin;
	}
}

