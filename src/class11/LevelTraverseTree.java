package class11;

import java.util.LinkedList;
import java.util.Queue;

//层次遍历
public class LevelTraverseTree {


    public static void levelTraverse(Tree root) {
        if (root == null) {
            return;
        }
        Queue<Tree> queue = new LinkedList<>();
        queue.add(root);
        Tree cur = root;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.println(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }
}
