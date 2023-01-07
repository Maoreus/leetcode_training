package class13;

//给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离
public class MaxDistance {



    /**
     * 左子树的最大距离、右子树的最大距离、左子树的高度+右子树的高度+1
     * @return
     */
    public int maxDistance2(Tree head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxDistance;
    }

    private Info process(Tree head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int max = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(max, height);
    }

    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }
}
