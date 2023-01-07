package class13;

import java.util.LinkedList;
import java.util.Queue;

//判断是否是完全二叉树：每一层都满，除了最后一层
public class IsCBT {

    /**
     * 按层遍历
     * 1）如果y有右孩子没有左孩子，肯定不是
     * 2）当第一次遇到左右孩子不双全的时候，剩下遍历的节点必须是叶子节点
     * @return
     */
    public static boolean isCompleteTree1(Tree head) {
        if (head == null) {
            return true;
        }
        Queue<Tree> queue = new LinkedList<>();
        //是否左右不双全
        boolean notFull = false;
        Tree left = null;
        Tree right = null;
        queue.add(head);

        while (!queue.isEmpty()) {
            Tree cur = queue.poll();
            left = cur.left;
            right = cur.right;
            //如果有右边没有左边
            if (right != null && left == null) {
                return false;
            }
            //不全而且不是叶子节点
            if (notFull && (left != null || right != null)) {
                return false;
            }
            //按层遍历
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            //节点全不全 -- 有一次就都要判断了
            if (left == null || right == null) {
                notFull = true;
            }
        }
        return true;
    }


    /**
     * 判断二叉树是否是完全二叉树 -- 递归实现
     * @param head
     * @return
     */
    public static boolean isCompleteTree2(Tree head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    private static Info process(Tree head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        boolean isCBT = false;
        boolean isFull = false;
        int height = 1;
        //如果是满二叉树，那么也是完全二叉树
       if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
           isCBT = true;
       }
       //左满，右满，左高=右高+1
       else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == (rightInfo.height + 1)) {
            isCBT = true;
        }
        //左完，右满，左高=右高+1
       else if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == (rightInfo.height + 1)) {
            isCBT = true;
        }
        //左满右完，左边高=右边高
       else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height ) {
            isCBT = true;
        }
        isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(isCBT, isFull, height);
    }

   public static class Info{
        public boolean isCBT;
        public boolean isFull;
        public int height;

       public Info(boolean isCBT, boolean isFull, int height) {
           this.isCBT = isCBT;
           this.isFull = isFull;
           this.height = height;
       }
   }

    public static Tree generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Tree generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Tree head = new Tree((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Tree head = generateRandomBST(maxLevel, maxValue);
            if (isCompleteTree1(head) != isCompleteTree2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
