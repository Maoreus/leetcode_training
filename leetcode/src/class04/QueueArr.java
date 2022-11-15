package class04;

import java.util.LinkedList;
import java.util.Queue;

//用数组实现队列
public class QueueArr {

    
    public  int begin;
    public  int end;
    public  int size = 0;
    public  int len = 10;
    int[] arr = new int[len];



    public <T> T poll() {

       
    }

    public <T> void add( T val) throws Exception {
        if (size == len) {
            throw new Exception("放不下了");
        }
        

    }

    public <T> peek() {

    }

    public void remove() {

    }





    //实现一个双端链表
}
