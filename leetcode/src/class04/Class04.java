package class04;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Class04 {
    

    //反转单链表
    public static Node reverseNode(Node head) {
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

    // //反转双链表
    // public static DoubleNode reverseDoubleNode(DoubleNode head) {
    //     if (head == null || head. next == null) {
    //         return head;
    //     }


    // }
    
    // //把给定值都删除
    // public static Node removeSpecificNode(Node head) {

    // }




    //对数器 --- 反转单链表
    public static Node compareTest(Node head) {
        List<Node> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
           stack.push(cur);
            cur = cur.next;
        }
        //重组一条链表
        Node dummy = null;
        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            dummy.next = curNode;
        }
        return dummy.next;
        
    }


    public static Node generateRandomNodeList(int len, int range) {
        Node dummy = new Node(0);
        while (len > 0) {
            dummy.next = new Node(randomNumber(range));
        }
        return dummy.next;
    }

    public static int randomNumber(int range) {
        return (int) (Math.random()*range + 1) -  (int) (Math.random()*range + 1);
    }

    //测试主方法
    public static void main(String[] args) {
        int testTimes = 10000;
        int range = 100;
        for (int i = 0; i < testTimes; i++) {
            
        }
        int maxLen = (int)Math.random()*1000;
        
    }
}
