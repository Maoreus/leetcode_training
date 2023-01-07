package class11;

import java.util.Stack;

/**
 * 非递归方式实现二叉树的先序、中序、后序遍历
 */
public class BinaryTreeNoRecusive {

    public static void preOrderCompare(Tree root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrderCompare(root.left);
        preOrderCompare(root.right);
    }

    public static void inOrderCompare(Tree root) {
        if (root == null) {
            return;
        }
        preOrderCompare(root.left);
        System.out.println(root.val);
        preOrderCompare(root.right);
    }

    public static void posOrderCompare(Tree root) {
        if (root == null) {
            return;
        }
        preOrderCompare(root.left);
        preOrderCompare(root.right);
        System.out.println(root.val);
    }


    public static void pre(Tree root) {
        System.out.println("非递归实现先序遍历结果：");
        Tree cur = root;
        if (root == null) {
            return;
        }
        Stack<Tree> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.println(cur.val);
            if (cur.right != null) {
                stack.push(cur.left);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

    }

    public static void in(Tree root) {
        System.out.println("非递归实现中序遍历结果：");
        if (root == null) {
            return;
        }
        Tree cur = root;
        Stack<Tree> stack = new Stack<>();
        stack.push(cur);
        while (!stack.isEmpty() && cur != null) {
            //先把左边全部压进去
            if (cur.left != null) {
                stack.push(cur.left);
                cur = cur.left;
            } else {
                //弹出节点打印
                cur = stack.pop();
                System.out.println(cur.val);
                cur = cur.right;
            }
        }

    }

    public static void pos(Tree root) {
        System.out.println("非递归实现后序遍历结果：");
        if (root == null) {
            return;
        }
        //准备两个栈，用头右左的逆序换成后序的结果
        Stack<Tree> stack1 = new Stack<>();
        Stack<Tree> stack2 = new Stack<>();
        Tree cur = root;
        stack1.push(cur);
        while (!stack1.isEmpty()) {
            cur = stack1.pop();// 1 - 2 - 3 ,压入1
            stack2.push(cur);
            if (cur.left != null) {
                stack1.push(cur.left); // 压入2
            }
            if (cur.right != null) {
                stack1.push(cur.right);//压入3
            }
        }
        while (!stack2.isEmpty()) {//弹出顺序：2-3-1
            System.out.println(stack2.pop().val);
        }
    }
}