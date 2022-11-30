package class07;

import java.sql.SQLWarning;

/**
 * 实现一个堆
 */
public class Heap {
    public int[] arr;
    //堆的数量
    public int heapSize;
    //限制
    public final int limit = 100000;

 

    /**
     * 加入大根堆
     * @param val
     */
    public static void push(int val) {
        
    }

    /**
     * 弹出最大的数（顶部）
     * heapSize--
     * @return
     */
    public static int pop() {
        
    }

    //新加入的数，放在index位置，一直向上移动
    public static void heapInsert(int[] arr, int index) {
        //[index] vs  parent [(index - 1) /2]
        while (arr[index] > arr[(index - 1) / 2] && index >= 0) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void swap(int[] arr2, int i, int j) {
        int temp = i;
        i = j;
        j = temp;

    }

    /**
     * 下沉，比较左右孩子，
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            //选较大的孩子
        

        }
    }

}
