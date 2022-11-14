import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int[] dp;

    public static void main(String[] args) {
        int[] coins = new int[3];
        coins[0] =1;
        coins[1] =2;
        coins[2] =5;

        int res = coinChange(coins, 11);
    }


    public static int coinChange(int[] coins, int amount) {
        //变量：余额，硬币数量(无限)
        //变量：余额，硬币数量(无限)
        dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        return process(coins, amount);
    }


    static int process(int[] coins, int amount) {
        //1,2,5
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        //遍历过了

        if (dp[amount] != Integer.MAX_VALUE - 1) {
            return dp[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int cal = process(coins,amount - coins[i]);
            if (cal == -1) {
                continue;
            }

            res = Math.min(res, cal + 1);
        }
        dp[amount] = (res == Integer.MAX_VALUE - 1 || res == -1000) ? -1 : res;
        return dp[amount];
    }



    public boolean areAlmostEqual(String s1, String s2) {
        //长度都不等，不需要进一步比较
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        //用一个数组记录不相等的位置
        int len = c1.length;
        int[] record = new int[len];
        Arrays.fill(record, 0);
        for (int i = 0; i < len; i++) {
            if (c1[i] != c2[i]) {
                record[i] = 1;
            }
        }
        return compare(c1, c2, record);

    }
    boolean compare(char[] c1, char[] c2, int[] record) {
        //记录不相等的字符
        ArrayList<Character> notEqualChar1 = new ArrayList<>();
        ArrayList<Character> notEqualChar2 = new ArrayList<>();

        for (int i = 0; i < record.length; i++) {
            if (record[i] != 0) {
                notEqualChar1.add(c1[i]);
                notEqualChar2.add(c2[i]);
            }
        }
        //没有两个不等的字符说明无法交换
        if (notEqualChar1.size() != 2) {
            return false;
        }
        boolean flag = false;
        String temp11 = String.valueOf(notEqualChar1.get(0)) + String.valueOf(notEqualChar1.get(1));
        String temp12 = String.valueOf(notEqualChar1.get(1)) + String.valueOf(notEqualChar1.get(2));
        String temp2 = String.valueOf(notEqualChar2.get(0)) + String.valueOf(notEqualChar2.get(1));
        if (temp2.equals(temp11) || temp2.equals(temp12)) {
            flag = true;
        }
        return flag;

    }
}