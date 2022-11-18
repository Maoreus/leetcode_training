package class04;

import java.util.Stack;

//实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
//设计的栈类型可以使用现成的栈结构

/**
 * 实现key：准备两个栈，同步压入和弹出。一个叫数据栈，一个叫最小栈
 */
public class SpecialQueue{

    Stack<Integer> dataStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    int curMin = Integer.MAX_VALUE;

    public void push(int val) {
        dataStack.push(val);
        curMin = Math.min(val, curMin);
        minStack.push(curMin);
    }

    public int getMin() {
        return minStack.pop();
    }

    
}
