package class10;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 复制随机链表
 *  测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyRandList {

    /**
     * 用容器copy
     * @param head
     * @return
     */
    public static Node copyRandomList1(Node head) {
        Node cur = head;
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        while (cur.next !=null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        //copy rand节点
        cur = head;
        while (cur.next != null) {
            Node newNode = map.get(cur);
            newNode.next = map.get(cur.next); // 设置
            newNode.rand = map.get(cur.rand);
        }
        //头节点是新链表的第一个
        return map.get(head);
    }

    /**
     * 用有限几个变量实现
     * @param head
     * @return
     */
    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        //三个while实现，第一步插入节点1-1'-2-2'
        Node cur = head;
        while (cur.next != null) {
            Node oldNext = cur.next;
            Node insert = new Node(cur.val);
            cur.next = insert;
            insert.next = oldNext;
            cur = cur.next;
        }
        //第二步设置rand
        cur = head;
        while (cur.next != null) {
            Node insert = cur.next;
            insert.rand = cur.rand.next;
            Node oldNext = cur.next.next;
            cur = oldNext;
        }
        //分离新老节点
        cur = head;
        Node res = head.next;
        while (cur.next != null) {
            Node oldNext = cur.next.next;
            Node newNext = cur.next;
            cur.next = oldNext;
            newNext.next = oldNext.next;
            cur = oldNext;
        }
        return res;
    }

}
