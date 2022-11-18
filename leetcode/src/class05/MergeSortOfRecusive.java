package class05;

import java.util.Arrays;

/**
 * 递归版本实现归并排序
 */
public class MergeSortOfRecusive {


    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) >> 1;
        process(arr, start, mid);
        process(arr, mid+1, end);
        merge(arr, start, mid, end);
     
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] help = new int[end - start + 1];
        int p1= start;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= end) {
            help[index] = arr[p1] > arr[p2] ? arr[p1] : arr[p2];
            index++;
            p1++;
            p2++;
        }
        //剩余的指针继续添加到help后面
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= end) {
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[start + i] = help[i];
        }
    }

    // 申请随机数组
    public static int[] randomArray(int length, int range) {
        // 计算长度
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = randomNum(range);
        }
        return res;

    }

    // [-range, range]
    public static int randomNum(int range) {
        // 课程代码里面的写法看不懂？？？？
        return (int) (Math.random() * range + 1) - (int) (Math.random() * range + 1);

    }




    public static void main(String[] args) {
        int[] arr = randomArray(1000, 100);
        int[] arrCopy = arr.clone();
        Arrays.sort(arrCopy);
        mergeSort(arr);
        for (int i = 0; i < arrCopy.length; i++) {
            if (arr[i] != arrCopy[i]) {
                System.out.println("出错了！");
            }
        }

    }
}