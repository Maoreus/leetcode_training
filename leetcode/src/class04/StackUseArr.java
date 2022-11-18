package class04;

import java.util.Stack;

/**
 * 用数组实现栈
 */
public class StackUseArr {
    
    int index = -1;
    int len = 100;

    int[] arr = new int[len];

    public void add(int val) {

        Stack<Integer> s = new Stack<>();
    

    }

    public int pop() throws Exception {
        if (index == 0) {
            throw new Exception("没东西弹了");
        }
        return arr[index--];

    }

    public void push(int val) throws Exception {
        if (index == len - 1) {
            throw new Exception("满了");
        }
        index++;
        arr[index] = val;
    }

    public int peek() throws Exception {
        if (index < 0) {
            throw new Exception("栈是空的");
        }
        return arr[index];
    }

}
