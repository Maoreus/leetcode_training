package class04;

import java.util.Stack;

/**
 * 如何用栈实现队列？
 * 如何用队列实现栈？
 */
public class Stack2Queue {

    Stack<Integer> pushStack;
    Stack<Integer> popStack;

    // 两个栈实现队列：一个push，一个pop
    // 把数据丢到push栈，再倒数据到pop栈
    // PS: 数据必须一次性倒完，pop栈空了才行

    public Stack2Queue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();

    }

    public void add(int element) {
        pushStack.add(element);
        pushToPop();
    }

    private void pushToPop() {
        //pop栈为空才能往里面扔
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public int poll() throws Exception {
        if (pushStack.isEmpty() && popStack.isEmpty()) {
            throw new Exception("null");
        }
        //检查下数据是不是最新的
        pushToPop();
        return popStack.pop();
    }

}
