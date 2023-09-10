// PG L3. 아이템 줍기
/*
 * x, y좌표가 반대임 수정하느라 코드가 좀 지저분함
 * 
 * 입력 그대로 사각형을 찍으면 꼭지점 부분 count가 어려워짐 -> 대각선 지점 0이 있을때 cnt 2를 세주려했지만 실패
 * 입력받은 좌표에 *2를 하면 조건을 어렵지 않게 가져갈 수 있음 (단 answer에 /2를 해야함 -> 사각형이 커지므로)
 * 
 * 방법 1. x와 y가 2개다 넘어가면 사각형 안쪽이지만 x, y둘중 하나만 넘어가면 둘레에 위치함
 * 위를 조건으로 주는 것
 * 
 * 방법 2. 2배로 키운 좌표를 채우고 2배 + 1을 한 좌표에 배열을 원상복구하면 둘레만 채워짐
 * 
 * 반복문 더 쓰기 싫어서 1로함
 * 
 * ************** 다른 거 아는사람? **************
 */
import java.util.*;
class Solution {
    static class Point{
        int x1, y1, x2, y2;
        public Point(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    static List<Point> p;
    static Queue<int[]> q = new ArrayDeque<>();
    static int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] rect = new int[102][102];
        p = new ArrayList<>();
        
        for(int[] r : rectangle){
            for(int i = r[1] * 2; i <= r[3] * 2; i++){
                for(int j = r[0] * 2; j <= r[2] * 2; j++){
                    rect[i][j] = -1;
                }
            }
            p.add(new Point(r[0] * 2, r[1] * 2, r[2] * 2, r[3] * 2));
        }   
        
        return move(rect, characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
    }

    private static int move(int[][] rect, int x, int y, int ix, int iy){
        rect[y][x] = 1;
        q.offer(new int[]{y, x, 1});
        
        while(!q.isEmpty()){
            int[] nxt = q.poll();
            
            if(nxt[0] == iy && nxt[1] == ix){
                return nxt[2];
            }
            
            for(int i = 0; i < 4; i++){
                int nx = nxt[0] + dir[i][0];
                int ny = nxt[1] + dir[i][1];
                if(rect[nx][ny] < 0 && check(nx, ny)){
                    q.offer(new int[]{nx, ny, nxt[2] + 1});
                    rect[nx][ny] = nxt[2] + 1;
                }
            }
        }

        return 0;
    }

    private static boolean check(int x, int y){
        for(Point pt : p){
            if(y > pt.x1 && x > pt.y1 && pt.x2 > y && pt.y2 > x){
                return false;
            }
        }
        return true;
    }
}                 