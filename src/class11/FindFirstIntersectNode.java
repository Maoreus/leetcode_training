package class11;


/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 */
public class FindFirstIntersectNode {

    //找到链表中环的入口
    public static Node cycleEntrance(Node head) {
        ////head.next.next == null的判断必须加上，不然在下面的fast直接就成null了
        if (head == null || head.next == null  || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) { // 这里之前不小心把slow和fast都设置为head，导致没有进入while循环
            //无环节点可以走到头，直接返回null
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //fast回到头结点，再继续走下去相交的地方就是入口
        fast = head;
        while (slow != fast && fast.next != null && slow.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow == fast ? slow : null;
    }

    /**
     * 主函数
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node findFirstIntersectNode(Node head1, Node head2) {
        //第一步先判断两个链表是否有环 head1:1-2-3-4-5-6-4...
        Node entrance1 = cycleEntrance(head1);
        Node entrance2 = cycleEntrance(head2);

        //第一种情况，两条链表都无环
        if (entrance1 == null && entrance2 == null) {
            return noCycle(head1, head2);
        }

        //第二种情况，两个都有环。分三种情况讨论
        if (entrance1 != null && entrance2 != null) {
            return twoCycles(head1, head2, entrance1, entrance2);
        }

        //第三种情况，一个有环一个无环，说明不可能相交
        return null;
    }


    public static Node noCycle(Node head1, Node head2) {
        //统计步长
        int len1 = 0;
        int len2 = 0;
        Node end1 = null;
        Node end2 = null;
        Node cur = head1;
        //这里不能用cur != null做判断，不然出来的时候cur==null，赋值给end1的时候也是null ！！！！！！！！！！！！！
        while (cur.next != null) {
            len1++;
            cur = cur.next;
        }
        end1 = cur;
        cur = head2;
        //这里不能用cur != null做判断，不然出来的时候cur==null，赋值给end1的时候也是null   ！！！！！！！！！！！！！！！
        while (cur.next != null) {
            len2++;
            cur = cur.next;
        }
        end2 = cur;
        //终点都不一样就肯定不相交
        if (end1 != end2) {
            return null;
        }
        //终点一样比较步长
        if (end1 == end2) {
            int delta = Math.abs(len1 - len2);
            if (len1 > len2) {
                cur = head1;
                while (delta != 0) {
                    cur = cur.next;
                    delta--;
                }
                //然后两边开始同时走
                Node start1 = cur;
                Node start2 = head2;
                while (start2 != start1) {
                    start1 = start1.next;
                    start2 = start2.next;
                }
                return start1;
            } else {
                cur = head2;
                while (delta != 0) {
                    cur = cur.next;
                    delta--;
                }
                //这里copy上面把start1写成了head2，搞错了 （X）Node start1 = head2
                Node start1 = head1;
                Node start2 = cur;
                while (start2 != start1) {
                    start1 = start1.next;
                    start2 = start2.next;
                }
                return start1;
            }
        }
        return null;
    }

    public static Node twoCycles(Node head1, Node head2, Node entrance1, Node entrance2) {
        //第一种情况，两个环的入口是同一个，找到先前相交的那个节点
        if (entrance1 == entrance2) {
            int len1 = 0;
            int len2 = 0;
            //统计到环之前的距离
            Node cur = head1;
            while (cur != entrance1) {
                len1++;
                cur = cur.next;
            }
            cur = head2;
            while (cur != entrance2) {
                len2++;
                cur = cur.next;
            }
            int delta = Math.abs(len1 - len2);
            cur = len1 > len2 ? head1 : head2;
            while (delta != 0) {
                cur = cur.next;
                delta--;
            }
            Node start1 = len1 > len2 ? cur : head1;
            Node start2 = len1 > len2 ? head2 : cur;
            while (start2 != start1) {
                start1 = start1.next;
                start2 = start2.next;
            }
            return start1;
        } else {
            //第二种情况，环是同一个，但是入口不等
            // 这里错了
            // cur1如果从head1开始走 ：            （X）Node cur1 = head1;
            // 相当于cur1从链表1的头节点开始走啊！
            // 你写的while是：遇到入环节点就停了，那么cur1必然遇不到entrance2啊
            // cur1从链表1的头节点开始走，刚入环就停了，它哪有机会遇到entrance2？
            //在环内寻找
            Node cur1 = entrance1.next; //如果从head1开始走根本进入不到环内部
            while (cur1 != entrance1) {
                if (cur1 == entrance2) {
                    return cur1;
                }
                cur1 = cur1.next;
            }
            //第三种情况，互相成环且不相交
            return null;
        }

    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(findFirstIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(findFirstIntersectNode(head1, head2).val);
    }


}
