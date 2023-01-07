package class09;

/**
 * 前缀树实现(Node 数组的形式)
 */
public class Node {
        int pass;
        int end;
        Node[] nexts;

    public Node() {
        pass = 0;
        end = 0;
        nexts = new Node[26];
    }



}
