package class06;


/**
 * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.

Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.

 
 */
public class CountRangeNum {

    public static int countRangeNum(int[] arr, int lower, int upper) {
        if (arr == null) {
            return 0;
        }
        int[] preSum = preSumArr(arr);

        return process(preSum, 0, arr.length, lower, upper);
    }

    /**
     * 递归处理左右组
     * @param preSum
     * @param i
     * @param length
     * @param lower
     * @param upper
     * @return
     */
    private static int process(int[] preSum, int left, int right, int lower, int upper) {
        int mid = left + ((right - left) >> 1);
        int leftPart = process(preSum, left, mid, lower, upper);
        int rightPart = process(preSum, mid + 1, right, lower, upper);
        int merge = merge(preSum, left, mid ,right, lower, upper);
        return leftPart + rightPart + merge;
    }


    private static int merge(int[] preSum, int left, int mid, int right, int lower, int upper) {
       if (left ==  right) {
            //此时是前缀和为left的总和，判断是否在区间内
            if (preSum[left] >= lower && preSum[left] <= upper) {
                return 1;
            }else {
                return 0;
            }
        }
        int[] help = new int[right - left + 1];
        //合并数组指针
        int index = right;
        //左右组指针
        int p1 = left;
        int p2 = mid + 1;
        //统计逆序对
        int sum = 0;
        //[sum - upper, sum - lower]开窗计算,从右边看左边，是否在此区间内
        int windowLeft = left;
        int windowRight = left;
        for (int i = mid + 1; i <= right; i++) {
            int max = preSum[i] - lower;
            int min = preSum[i] - upper;
            while (windowLeft <= mid && preSum[windowLeft] >= min) {
                windowLeft++;
            }
            while (windowRight <= mid && windowRight < max) {
                windowRight++;
            }
            sum += windowRight - windowLeft;
        }
        //从右组开始合并
        while (p1 >= left && p2 >= mid + 1) {
            help[index++] = preSum[p1] < preSum[p2] ? preSum[p2++] : preSum[p1++];
        }
        while (p1 >= left) {
            help[index++] = preSum[p1++];
        }
        while (p2 >= mid + 1) {
            help[index++] = preSum[p2++];
        }
        return sum;
    }

    /**
     * 前缀和数组
     * @param arr
     * @return
     */
    private static int[] preSumArr(int[] arr) {
        int[] res = new int[arr.length];
        res[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            res[i] = res[i - 1] + arr[i];
        }
        return res;
    }


    
}
