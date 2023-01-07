package class09;

//基数排序实现
public class RadixSort {

    public void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0 , arr.length - 1, getMaxDigit(arr));
    }

    private void process(int[] arr, int left, int right, int maxDigit) {
        final int radix = 10;

        //有几位就进出几次bucket
        for (int d = 0; d < maxDigit; d++) {
            int[] count = new int[radix];
            //当前位数展开n个桶，用count[i] 表示当前digit的情况下，位数=i的数有几个
            for (int i = 0; i < radix; i++) {
                int bit = getDigit(arr[i], d);
                count[bit]++;
            }
            int[] count2 = new int[count.length];
            count2[0] = count[0];
            //求以i结尾的位数前缀和，count[i]表示<=i的数一共有几个
            for (int j = 1; j < count.length; j++) {
               count2[j] = count2[j - 1] + count[j];
            }
            //help数组记录顺序
            int[] help = new int[right - left + 1];
            for (int i = right; i >=left ; i--) {
                int bit = getDigit(arr[i], d);
                help[count2[bit] - 1] = arr[i];
                count2[bit]--;
            }
            for (int i = 0, j = left; i <= right ; i++, j++) {
                arr[i] = help[j];
            }
        }

    }

    /**
     * 获取当前数组的最大位数
     * @param arr
     * @return
     */
    private int getMaxDigit(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int digit = 0;
        while (max != 0) {
            digit++;
            max /= 10;
        }
        return digit;
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    public static void main(String[] args) {
        System.out.println(getDigit(99, 2));
    }
}
