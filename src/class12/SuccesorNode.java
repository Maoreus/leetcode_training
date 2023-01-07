package class12;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树结构如下定义：
 * Class Node {
 * 	V value;
 * 	Node left;
 * 	Node right;
 * 	Node parent;
 * }
 * 给你二叉树中的某个节点，返回该节点的后继节点
 * 定义：中序遍历x的的下一个节点
 */
public class SuccesorNode {

    /**
     * 通过重建中序遍历的方式遍历整棵树
     * @return
     */
    public SNode getSuccessorNode1(SNode x) {
        SNode head = null;
        while (x.parent != null) {
            head = x.parent;
        }
        if (head == null) {
            return null;
        }
        //重建整棵树
        List<SNode> list = new ArrayList<>();
        midTraverse(head, list);
        int xIndex = list.indexOf(x);
        int nextIndex = -1;
        if (xIndex != -1) {
            nextIndex = xIndex + 1;
        }
        return nextIndex == list.size() ? null : list.get(nextIndex);
    }
    public void midTraverse(SNode root, List<SNode> list) {
        if (root == null) {
            return;
        }
        midTraverse(root.left, list);
        list.add(root);
        midTraverse(root.right, list);
    }


    /**
     * (1）如果此节点存在右子树，那么后继节点就是其右子树的最左下节点
     *
     * （2）如果此节点不存在右子树，那么后继节点就是，
     * 其第一个作为别的节点的左子树的，祖先节点（所有的祖先节点中，最靠近此节点并且是别的节点的左子树的祖先节点）
     * @return
     */
    public SNode getSuccessorNode2(SNode x) {
        if (x == null) {
            return x;
        }
        SNode nextOne = null;
        //如果存在右子树
        if (x.right != null) {
            nextOne = getLeftMost(x);
        }else {
            SNode parent = x.parent;
            while (x.parent != null && parent.right != x) {
                x = parent; //我来到我父亲的位置
                parent = x.parent; //父节点往上走
            }
            nextOne = parent;
        }
        return nextOne;
    }

    private SNode getLeftMost(SNode x) {
        if (x == null) {
            return x;
        }
        SNode leftMost = null;
        while (x.left != null) {
            leftMost = x.left;
        }
        return leftMost;
    }
}

class SNode {
    int value;
    SNode left;
    SNode right;

    public SNode(int value, SNode left, SNode right, SNode parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    SNode parent;
}
