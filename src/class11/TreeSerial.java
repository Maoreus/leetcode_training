package class11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//1）先序方式序列化和反序列化  2）按层方式序列化和反序列化
public class TreeSerial {

    /**
     * 先序序列化
     * @param root
     * @return
     */
    public static List<String> preSerial(Tree root) {
        List<String> res = new ArrayList<>();
        preS(root, res);
        return res;
    }

    public static void preS(Tree root, List<String> res) {
        if (root == null) {
            res.add(generateSerial(root));
            return;
        }
        preS(root.left, res);
        preS(root.right, res);
    }

    public static String generateSerial(Tree node) {
        if (node == null) {
            return "#";
        }
        return String.valueOf(node.val);
    }

    /**
     * 先序重建二叉树
     * @param list
     * @return
     */
    public static Tree buildPreSerial(List<String> list) {
        if (list.size() == 1 && list.get(0).equals("#")) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        for (String t : list) {
            queue.add(t);
        }
        //先重建根节点
        return buildPreProcess(queue);
    }

    public static  Tree buildPreProcess(Queue<String> queue) {
        String str = queue.poll();
        if (str.equals("#")) {
            return null;
        }
        Tree root = buildPreS(str);
        root.left = buildPreProcess(queue);
        root.right = buildPreProcess(queue);
        return root;
    }
    public static Tree buildPreS(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new Tree(Integer.valueOf(val));
    }

    public static Queue<String> posSerial(Tree root) {
        Queue<String> ans = new LinkedList<>();
        poss(root, ans);
        return ans;
    }

    private static void poss(Tree root, Queue<String> ans) {
        if (root == null) {
            ans.add(null);
        } else {
            poss(root.left, ans);
            poss(root.right, ans);
            ans.add(String.valueOf(root.val));
        }
    }

    /**
     * 按层序列化
     * @return
     */
    public static Queue<String> levelSerial(Tree root) {
        Queue<String> res = new LinkedList<>();
        if (root == null) {
            res.add(null);
        }else {
            res.add(String.valueOf(root));
            Queue<Tree> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Tree cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                res.add(generateSerial(cur.left));
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                res.add(generateSerial(cur.right));
            }
        }
        return res;
    }

    public static Tree buildByLevel(Queue<String> list) {
        if (list.size() == 0 || list == null) {
            return null;
        }
        String rootStr = list.poll();
        Tree root = buildByQueueStr(rootStr);
        Queue<Tree> queue = new LinkedList();
        queue.add(root);

        Tree cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cur.left = buildByQueueStr(list.poll());
            cur.right = buildByQueueStr(list.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return root;
    }

    public static Tree buildByQueueStr(String s) {
        if (s == null) {
            return null;
        }
        return new Tree(Integer.valueOf(s));
    }
}
