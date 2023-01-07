package class04;


//用数组实现队列
public class QueueArr {

    
    public  int begin;
    public  int end;
    //用size把begin和end解耦，不关心是否到了边界，边界由size控制
    public  int size = 0;
    public  int len = 10;
    int[] arr = new int[len];



    public int poll() throws Exception {
        if (size == 0) {
            throw new Exception("队列为空");
        }
        int res = arr[begin];
        begin = nextIndex(begin);
        size--;
        return res;
       
    }

    private int nextIndex(int cur) {
        //没到最后的位置
        if (cur < len - 1) {
            cur++;
        }
        return 0;
    }

    public void add(int val) throws Exception {
        if (size == len) {
            throw new Exception("放不下了");
        }
        arr[end] = val;
        end = nextIndex(end);
        size++;
    }





    //实现一个双端链表
}
