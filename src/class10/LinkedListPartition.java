package class10;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 */
public class LinkedListPartition {

    /**
     * 对数器（使用）
     * @param head
     * @param partition
     */
    public static Node listParition1(Node head, int partition) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 0;
        Node cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        Node[] nodes = new Node[len];
        cur = head;
        for (int i = 0; i < len; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });
        cur = nodes[0];
        int index = 1;
        while (cur.next != null) {
            nodes[index].next = nodes[index + 1];
        }
        return nodes[0];
    }


        public static Node listParition2(Node head, int partition) {
        if (head == null || head.next == null) {
            return head;
        }
        Node  cur = head;
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        //cur走到最后一个
        while (cur.next != null) {
            Node temp = cur.next;
            if (cur.val == partition) {
                if (equalHead == null) {
                    //第一次链接上的时候头尾都是当前节点
                    equalHead = cur;
                    equalTail = cur;
                }else {
                    //连接完tail要更新
                    equalTail.next = cur;
                    equalTail = cur;
                }
            } else if (cur.val < partition) {
                if (smallHead == null) {
                    smallHead = cur;
                    smallTail = cur;
                }else {
                    smallTail.next = cur;
                    smallTail = cur;
                }
            }else {
                if (bigHead == null) {
                    bigHead = cur;
                    bigTail = cur;
                }else {
                    bigTail.next = cur;
                    bigTail = cur;
                }
            }
            cur = temp;
        }
        //结束大等小三区划分后开始串联三条头尾
            // 如果有小区
         if (smallTail != null) {
             smallTail.next = equalHead; //空的也没关系
             equalTail = equalTail == null ? smallTail : equalTail;
         }
         //如果有等于区域
            if (equalTail != null) {
                equalTail.next = bigHead;
            }
            return smallHead != null ? smallHead : (equalHead != null ? equalHead : bigHead);
    }



}
