package class05;

/**
 * 非递归实现归并排序
 */
public class MergeSortNoRecusive {
    

    public void mergeSort(int[] arr) {
        //最右的边界
        int len = arr.length - 1;
        //步长
        int step = 1;
        //左边界
        int left = 0;

        while (left < len) {
            int mid = left + step - 1;
            //左组的右边也不能越界
            if (mid > len) {
                break;
            }
            //右组的最右边也不能越界
            int right = Math.min(mid + step, len);
            //left...mid...right
            merge(arr, left, mid, right);
            //更新左边界
            left = mid + 1;
            //防止溢出
            if (step > len / 2) {
                break;
            }

            //更新步长
            step = step << 1;
        }
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
}
