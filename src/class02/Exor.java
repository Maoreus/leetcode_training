package class02;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 认识异或运算
 */
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
    public static int[] randomArray(int kTimes, int range, int k, int m) {
        //计算长度
        int len = (kTimes - 1) * m + k;
        int[] res = new int[len];
        //生成随机数，出现k次
        int randomK = randomNum(range);
        //先把k个特别的数给填充进去
        int index = 0;
        for (int i = 0; i < k; i++) {
            res[i] = randomK;
            index++;
        }
        //记录出现过的数
        HashSet<Integer> set = new HashSet<>();
        set.add(randomK);
        kTimes--; //因为已经把特别的那个数给写进res数组中了
        while(kTimes != 0) {
            int random = randomNum(range);
            while (set.contains(random)) {
                random = randomNum(range);
            }
            //出现m次的常规数
            for (int i = 0; i < m; i++) {
                res[index++] = random;
            }
            //已经记录完了一种数
            kTimes--;
        }
        //填好之后打乱顺序
        for (int i = 0; i < res.length; i++) {
            int j = (int)(Math.random() * res.length); // 0~N-1
            int temp = res[i];
            res[i] = res[j];
            res[j] = temp;
        }
        return res;

    }

    //[-range, range]
    public static int randomNum(int range) {
        //课程代码里面的写法看不懂？？？？
        return (int) (Math.random() * range + 1) - (int) (Math.random() * range + 1);
        
    }

    public static void main(String[] args) {
        //有多少种数
        int kTimes = 10;
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