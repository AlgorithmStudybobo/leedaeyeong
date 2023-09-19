import java.util.*;
// PG L4. 1,2,3 떨어뜨리기
/*
 * tc1 -> target[], edges[][] length가 다름, 트리 구현시 인덱스 맞춰야함 
 * 
 * tree 구현 -> 리프 노드를 찾아야되는데 tree 구현이 안되면 찾을수가 없음
 * 
 * sort -> 노드 순서가 정렬이 안되어 있는 경우가 있음
 * 
 * tree 구현으로 부모 인덱스 하위에 자식 배열이 있음, cnt++을 하면서 자식 노드의 length만큼 %연산을 하면 자식을 번갈아가며 순회하는 것처럼 표현 가능
 * 
 * GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG 72점 ㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗ
 */
class Solution {
    static int height;
    
    public int[] solution(int[][] edges, int[] target) {
        height = edges.length + 1;
        List<Integer>[] tree = makeTree(edges);
        // System.out.println(height + " " + setLoop(tree, target));
        int cnt = setLoop(tree, target);

//         for(int i = 0; i < height; i++){
//             System.out.print(i);
//             System.out.println();
//             for(int j : tree[i]){
//                 System.out.print(j);
//             }
//             System.out.println();
//         }
        return drop(cnt, target, tree);
    }

    public int[] drop(int cnt, int[] target, List<Integer>[] tree){
        Queue<Integer> q = new ArrayDeque<>();
        int[] leaf = new int[height];
        int[] cntArr = new int[height];
        int flag = 0;
        
        while(cnt > 0){
            int root = 0;
            while(tree[root].size() > 0){
                root = tree[root].get(cntArr[root]++ % tree[root].size());
            }
            leaf[root]++;
            q.offer(root);
            
            if(leaf[root] > target[root]){
                return new int[] {-1};
            }

            if((flag & (1 << root)) == 0 && target[root] <= leaf[root] * 3) {
                flag |= (1 << root);
                cnt--;
            }
        }
        // System.out.println(q);
        // System.out.println(Arrays.toString(leaf));
        return result(leaf, target, q);
    }
    
    public int[] result(int[] leaf, int[] target, Queue<Integer> q){
        List<Integer> res = new ArrayList<>();
        
        for(int t : q){
            leaf[t]--;
            for(int i = 1; i < 4; i++){
                if(leaf[t] <= target[t] - i && target[t] - i <= leaf[t] * 3){
                    res.add(i);
                    target[t] -= i;
                    break;
                }
            }
            // System.out.println(Arrays.toString(target));
        }
        // System.out.println(res);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int setLoop(List<Integer>[] tree, int[] target){
        int cnt = 0;
        for(int i = 0; i < height; i++){
            if(tree[i].isEmpty() && target[i] > 0){
                cnt++;
            }
        }
        return cnt;
    }
    
    public List<Integer>[] makeTree(int[][] edges){
        List<Integer>[] tree = new ArrayList[height];
        
        for(int i = 0; i < height; i++){
            tree[i] = new ArrayList<>();
        }
        for(int i = 0; i < height-1; i++){
            tree[edges[i][0] - 1].add(edges[i][1] - 1); 
        }
        for(int i = 0; i < height; i++){
            Collections.sort(tree[i]);
        }
        return tree;
    }
}