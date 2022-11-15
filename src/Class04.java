import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Class04 {
    class Node {
        Node next;
        int val;
    }

    class DoubleNode {
        DoubleNode last;
        DoubleNode next;
        int val;
    }


    //反转单链表
    public static Node reversNode(Node head) {
        if (head == null || head. next == null) {
            return head;
        }
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            cur.next = pre;
            pre = head;
            //前进
            cur = head.next;
            head = cur;
        }
        return head;
    }

    //反转双链表
    public static DoubleNode reverseDoubleNode(DoubleNode head) {


    }
    
    //把给定值都删除
    public static Node removeSpecificNode(Node head) {

    }




    //对数器 --- 反转单链表
    public static Node compareTest(Node head) {
        List<Node> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Node cur = head;
        while (cur != null) {
\           queue.add(cur);
            cur = cur.next;
        }
        //重组一条链表
        Node dummy = null;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            dummy.next = curNode;
        }
        return dummy.next;
        
    }


    public static Node generateRandomNodeList(int len, int range) {
        Node dummy = null;
        while (len > 0) {
            dummy.next = new Node()
        }
    }

    public static int randomNumber(int range) {
        return (int) (Math.random()*range + 1) -  (int) (Math.random()*range + 1);
    }

    //测试主方法
    public static void main(String[] args) {
        
    }
}
