package class05;


/**
 * 逆序对
 */
public class ReversePair {
    

    public static int reversePair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }



    /**
     * 递归处理过程
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int process(int[] arr, int left, int right) {
        //base case
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int leftPart = process(arr, left, mid);
        int rightPart = process(arr, mid + 1, right);
        int merge = merge(arr, left, mid, right);
        return leftPart + rightPart + merge;
    }


    /**
     * 左右组归并过程
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private static int merge(int[] arr, int left, int mid, int right) {
        if (left ==  right) {
            return 0;
        }
        int[] help = new int[right - left + 1];
        //合并数组指针
        int index = right;
        //左右组指针
        int p1 = mid;
        int p2 = right;
        //统计逆序对
        int sum = 0;
        //从右组开始合并
        while (p1 >= left && p2 >= mid + 1) {
            sum += arr[p1] > arr[p2] ? (p2- mid) : 0;
            help[index--] = arr[p1] < arr[p2] ? arr[p2--] : arr[p1--];
        }
        while (p1 >= left) {
            help[index--] = arr[p1--];
        }
        while (p2 >= mid + 1) {
            help[index--] = arr[p2--];
        }
        return sum;
    }



    /**
     * 暴力比较
     * @param arr
     * @return
     */
    public static int comparator(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    sum++;
                }
            }
        }
        return sum;
    }


    // for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (reversePair(arr1) != comparator(arr2)) {
				System.out.println("Oops!");
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println("测试结束");
	}
}
