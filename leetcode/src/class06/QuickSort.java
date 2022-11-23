package class06;



/**
 * 快排的优化几个版本
 */
public class QuickSort {
    

    /**
     * 快排1.0版本
     * @param arr
     */
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //选择最后一个数作为起切点
        process(arr, 0 ,arr.length - 1);
    }
    //递归处理左右
    private static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int equal = partition1(arr, left, right);
        //中间位置的数不用动
        process(arr, left, equal - 1);
        process(arr, equal + 1, right);
    }

    private static int partition1(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        if (left < right) {
            return - 1;
        }
        int target = arr[right];
        //<=区域
        int lessEqual = left - 1;
        //数组中指针
        int index = left;
        while(index <= right) {
            //如果当前数大于等于目标数
            if (arr[index] == target) {
                swap(arr, lessEqual + 1, index);
                index++;
                lessEqual++;
            }else {
                index++;
            }
        }
        //因为划分值在R位置，最后要入<=区域。
        swap(arr, right, index);
        return ++lessEqual;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 快排2.0
     * @param arr
     */
    public static void NetherlanfFlag(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //选择最后一个数作为起切点
        int[] area = process2(arr, 0 ,arr.length - 1);
        process2(arr, 0, area[0] - 1);
        process2(arr, area[1] + 1, arr.length - 1);

    }

    /**
     * 返回左右边界
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int[] process2(int[] arr, int left, int right) {
        //越界情况
        if (left > right) {
            return new int[] {-1, -1};
        }
        if (left == right) {
            return new int[] {left, left};
        }
        int less = left - 1;
        int index = left;
        int more = right; // 边界，right先剔除出来
        int target = arr[right];
        //左边界逼近右边界
        while (index < more) {
            //相等直接前进
            if (arr[index] == target) {
                index++;
            }else if (arr[index] < target) {
                swap(arr, index, less + 1);
                index++;
                less++;
            }else {
                swap(arr, index, more - 1);
                //index++;  这里的index不能继续++，因为此时index位置的值是刚换过来的
                more--;
            }
        }
        swap(arr, index, right);
        return new int[] {less + 1, more - 1};
    } 


    /**
     * 快排3.0
     * @param arr
     */
    public static void quickSort3(int[] arr) {

    }
}
