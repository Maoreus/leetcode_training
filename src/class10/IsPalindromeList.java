package class10;

import java.util.Stack;

/**
 * 回文链表判断
 */
public class IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    /**
     * 借助栈来判断
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeList1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> help = new Stack<>();
        Node cur = head;
        while (cur.next != null) {
            help.push(head);
            cur = cur.next;
        }
        cur = head;
        while (!help.isEmpty()) {
            if (help.pop().value != cur.value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 把右边链表反转跟左边链表做比较
     * @param head
     * @return
     */
    public static boolean isPalindromeList3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        //1-2-3
        Node fast = head; //0-1
        Node slow = head;//1-3
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //slow为中点
        Node n1 = slow; //slow=2
        Node n2 = n1.next;//右边的起始点, n2=3
        n1.next = null;//设为空，不然会死循环
        Node n3 = null;
        //反转右边链表
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1; // 右边指向中点
            n1 = n2; //n1（中点）前进
            n2 = n3; // n2前进
        }
        boolean res = false;
        //比较左半边和右半边
        Node curLeft = head;
        Node curRight = n1;
        //走到中点停止了
        while (curLeft != null && curRight != null) {
            if (curLeft.value != curRight.value) {
                return res;
            }
            curLeft = curLeft.next;
            curRight = curRight.next;
        }
        res = true;
        //恢复右半边链表,起始点从右边第一个节点开始
        n2 = n1.next;
        n1.next = null; //尾巴节点设为null，不然会死循环
        n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        return res;
    }



    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
/*
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
*/

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

//        head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(1);
//        printLinkedList(head);
//        System.out.print(isPalindromeList1(head) + " | ");
//        System.out.println(isPalindromeList3(head) + " | ");
//        printLinkedList(head);
//        System.out.println("=========================");
//
//        head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(3);
//        head.next.next.next = new Node(1);
//        printLinkedList(head);
//        System.out.print(isPalindromeList1(head) + " | ");
//        System.out.println(isPalindromeList3(head) + " | ");
//        printLinkedList(head);
//        System.out.println("=========================");

//        head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(2);
//        head.next.next.next = new Node(1);
//        printLinkedList(head);
//        System.out.print(isPalindromeList1(head) + " | ");
//        System.out.println(isPalindromeList3(head) + " | ");
//        printLinkedList(head);
//        System.out.println("=========================");
//
//        head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(3);
//        head.next.next.next = new Node(2);
//        head.next.next.next.next = new Node(1);
//        printLinkedList(head);
//        System.out.print(isPalindromeList1(head) + " | ");
//        System.out.println(isPalindromeList3(head) + " | ");
//        printLinkedList(head);
//        System.out.println("=========================");

    }
}
