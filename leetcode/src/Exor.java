import java.util.HashMap;
import java.util.Map;

class Exor {

    //找到数组中出现了奇数次的数a,b
    void printOddTimes2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //eor异或后的结果是a^b
        int eor2 = 0;
        //找到eor最右侧的1
        int rightOne = eor & (-eor);
        for (int i = 0; i < arr.length; i++) {
            //与最右侧与的结果不等于0
            if ((arr[i] & rightOne) != 0) {
                eor2 ^= arr[i];
            }
        }
        System.out.println(eor+eor2);

    }

    //数组中有一个数出现k，其他出现M次。
    static int findKTimesNum(int[] arr, int k, int m) {
        int[] count = new int[32];

        //统计每一位数在二进制位数上出现的次数总和
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < count.length; j++) {
                //第j位的是否是1
                if ( (arr[i] >> j & 1) != 0) {
                    count[j] += 1;
                }
            }
        }

        int res = 0;
        //这个位数上无法整除m的位数就是要找的这个数
        for (int i = 0; i < count.length; i++) {
            if (count[i] % m != 0) {
                //找到了位数补1
                res = res | i;
            }
        }
        return res;


    }

    //对数器测试方法
    public static int findKTimesNumTest(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            }else {
                map.put(key, 1);
            }
        }

        for (int key : map.keySet()) {
            if (map.get(key) == k) {
                return key;
            }
        }
        return -1;
    }

    //申请随机数组
    public static int[] randomArray(int maxLen, int range, int k, int m) {

    }

    //[-range, range]
    public static int randomNum(int range) {
        
    }

    public static void main(String[] args) {
        int len = 100;
        int range = 200;
        int testTimes = 10000;
        
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
        
            int a = (int)(Math.random() * max) + 1; // 1-9
            int b = (int)(Math.random() * max) + 1; //1-9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }


            int[] arr = randomArray(max, range, k, m);
            int ans1 = findKTimesNum(arr, k, m);
            int ans2 = findKTimesNumTest(arr, k, m);
            if (ans1 != ans2) {
                System.out.println("error");
            }
        }
        System.out.println("测试结束");
    }

}