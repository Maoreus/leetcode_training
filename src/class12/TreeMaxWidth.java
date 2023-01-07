package class12;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreeMaxWidth {

    /**
     * 利用哈希表记录
     * @param root
     * @return
     */
    public static int maxWidth1(Tree root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        int curLevel = 1;
        int curLevelNodes = 0;
        //保存当前节点在第几层
        Map<Tree, Integer> levelMap = new HashMap<>();
        levelMap.put(root, 1);
        Queue<Tree> queue = new LinkedList<>();
        queue.add(root);
        Tree cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            int curLevelValue = levelMap.get(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                levelMap.put(cur.left, curLevelValue + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                levelMap.put(cur.right, curLevelValue + 1);
            }
            //还在当前层，统计节点数++
            if (curLevelValue == curLevel) {
                curLevelNodes++;
            }else {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                //下一层
                curLevel++;
            }
        }
        return max;
    }
}
