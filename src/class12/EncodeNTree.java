package class12;

import java.util.ArrayList;
import java.util.List;

/**
 * n叉树和二叉树互换
 */
public class EncodeNTree {

    /**
     * 全部挂在左树右边界上
     * @return
     */
    public static Tree encodeNTree(NTree nhead) {
        if (nhead == null) {
            return null;
        }
        //根节点是一样的
        Tree root = new Tree(nhead.val);
        root.left = encode(nhead.children);
        return root;
    }

    private static Tree encode(List<NTree>  children) {
        Tree head = null;
        Tree cur = null;
        for (NTree child : children) {
            Tree curNode = new Tree(child.val);
            if (head == null) {
                head = curNode;
            }else {
                cur.right = curNode;
            }
            cur = curNode;
            cur.left = encode(child.children); //递归遍历好扔回来左子树
        }
        return head;
    }

    public static NTree decode(Tree head) {
        if (head == null) {
            return null;
        }
        NTree root = new NTree(head.val);
        root.children = de(head.left); //左树的右边界
        return root;
    }

    private static List<NTree> de(Tree head) {
        NTree cur = null;
        List<NTree> nTrees = new ArrayList<>();
        while (cur != null) {
            cur = new NTree(head.val, de(head.left));
            nTrees.add(cur);
            head = head.right; //我的兄弟都在我的右侧
        }
        return nTrees;
    }
}
