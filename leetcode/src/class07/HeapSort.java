package class07;

import java.util.PriorityQueue;

//实现一个
public class HeapSort{


    public int[] sortedArrayDistanceLessK(int[] arr, int k) {
        if (arr == null || arr.length == 1) {
            return arr;
        }
        int[] res = new int[arr.length];
        //小根堆
        PriorityQueue heap = new PriorityQueue<>(k);
        for (int i = 0; i < Math.min(arr.length, k); i++) {
            heap.add(arr[i]);
        }
        //把小根堆维持在k个数的规模，不断弹出头部
        int index = 0;
        while (index < arr.length) {
            res[index] = heap.poll();
        }
        return res;
    }


    
}

