package class13;

import scala.Int;

import java.util.ArrayList;

//返回这颗二叉树中最大的二叉搜索子树的大小。
public class MaxSubBST {
    public static int maxSubBST2(Tree head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSubBST;
    }

    private static Info process(Tree x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.val;
        int min = x.val;
        int size = 1;

        int p1 = 0;
        if (leftInfo != null) {
            p1 = leftInfo.maxSubBST;
            max = Math.max(leftInfo.max, max); //注意这里是和max比较，不是固定写的x.val
            min = Math.min(leftInfo.min, min);
            size += leftInfo.size;
        }
        int p2 = 0;
        if (rightInfo != null) {
            p2 = rightInfo.maxSubBST;
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
             size += rightInfo.size;
        }

        //第二种情况：最大搜索子树包含x
        int p3 = -1;
        boolean leftBST = leftInfo == null ? true : leftInfo.maxSubBST == leftInfo.size;
        boolean rightBST = rightInfo == null ? true : rightInfo.maxSubBST == rightInfo.size;
        if (leftBST && rightBST) {
            boolean leftLessThanX = leftInfo == null ? true : (leftInfo.max < x.val);
            boolean rightMoreThanX = rightInfo == null ? true : (rightInfo.min > x.val);
            if (leftLessThanX && rightMoreThanX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.size;
                int rightSize = rightInfo == null ? 0 : rightInfo.size;
                p3 = leftSize + rightSize + 1;
            }
        }
        int maxSubBST = Math.max(p1, Math.max(p2, p3));
        return new Info(maxSubBST, max, min, maxSubBST == size, size);
    }


    static class Info {
        public int maxSubBST;
        public int max;
        public int min;

        public Info(int maxSubBST, int max, int min, boolean isBST, int size) {
            this.maxSubBST = maxSubBST;
            this.max = max;
            this.min = min;
            this.size = size;
        }

        public int size;
    }
    
    
    
    //为了验证
    // 对数器方法
    public static int right(Tree head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
    public static int getBSTSize(Tree head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Tree> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    // 为了验证
    // 对数器方法
    public static void in(Tree head, ArrayList<Tree> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证
    // 对数器方法
    public static Tree generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 为了验证
    // 对数器方法
    public static Tree generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Tree head = new Tree((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // 为了验证
    // 对数器方法
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Tree head = generateRandomBST(maxLevel, maxValue);
            int a = right(head);
            int b = maxSubBST2(head);
            if (a != b) {
                int c = maxSubBST2(head);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

}
