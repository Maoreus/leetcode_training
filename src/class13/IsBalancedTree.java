package class13;

//给定一棵二叉树的头节点head，返回这颗二叉树是不是平衡二叉树：左右树高度差不超过1
public class IsBalancedTree {

    /**
     * 1) x的左子树是平衡的
     * 2)右子树是平衡的
     * 3)左右高度差<2
     * @param head
     * @return
     */
    public boolean isBalancedTree(Tree head) {
        if (head == null) {
            return true;
        }
        return process(head).isBalanced;
    }

    private Info process(Tree head) {
        if (head == null) {
            return new Info(true, 0);
        }
        boolean isBalanced = true;
        int height = 0;
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        if (!leftInfo.isBalanced || !rightInfo.isBalanced) {
            isBalanced = false;
        }
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        height = Math.max(leftInfo.height, rightInfo.height);
        return new Info(isBalanced, height);
    }

    class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

}
