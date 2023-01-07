package class08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 给出每个时间段是哪k个用户得奖
 */
public class WhoWinPrize {
    private HashMap<Integer, Customer> customers;
    //候选区-大根堆，买的多买得早的在前面
    private HeapGreater<Customer> candidateHeap;
    //获奖区-小根堆，买的少的买的早的在前面，方便滚蛋
    private HeapGreater<Customer> winnerHeap;
    //前几名获奖
    private int k;

    public WhoWinPrize( int k) {
        customers = new HashMap<Integer, Customer>();
        candidateHeap = new HeapGreater<>(new CandidateComparator());
        winnerHeap = new HeapGreater<>(new DaddyComparator());
        this.k = k;
    }

    public static class CandidateComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
        }

    }

    public static class DaddyComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }

    }

    public List<List<Integer>> topK(int[] arr, boolean[] op, int k) throws Exception {
        List<List<Integer>> ans = new ArrayList<>();
        WhoWinPrize whoWinPrize = new WhoWinPrize(k);
        for (int i = 0; i < arr.length; i++) {
            whoWinPrize.operate(i, arr[i], op[i]);
            ans.add(whoWinPrize.getWinners());
        }
        return ans;

    }

    private List<Integer> getWinners() {
        List<Integer> ans = new ArrayList<>();
        List<Customer> allElements = winnerHeap.getAllElements();
        for (Customer c : allElements) {
            ans.add(c.id);
        }
        return ans;
    }

    private void operate(int time, int customerId, boolean buyOrRefund) throws Exception {
        //之前没买现在还退货的，滚蛋
        if (!buyOrRefund && !customers.containsKey(customerId)) {
            return;
        }
        //第一次进入队列的, init
        if (!customers.containsKey(customerId)) {
            customers.put(customerId, new Customer(customerId, 0, 0));
        }
        //看购买数量
        Customer curCustomer = customers.get(customerId);
        if (buyOrRefund) {
            curCustomer.buy++;
        }else {
            curCustomer.buy--;
        }
        //buy ==0 要移除
        if (curCustomer.buy == 0) {
            customers.remove(customerId);
        }
        //buy ！= 0并且得奖区和候选区都没有他，分情况讨论
        if (!candidateHeap.contains(curCustomer) && !winnerHeap.contains(curCustomer)) {
            //如果奖池剩余位置充足，直接加入
            if (winnerHeap.size() < k) {
                curCustomer.enterTime = time;
                winnerHeap.push(curCustomer);
            }else {
                //得奖区满了，加入候选区
                candidateHeap.push(curCustomer);
            }
        }else if (candidateHeap.contains(curCustomer)) {
            //此时候选区包含用户
            if (curCustomer.buy == 0) {
                candidateHeap.remove(curCustomer);
            }else {
                candidateHeap.resign(curCustomer);
            }
        }else {
            if (curCustomer.buy == 0) {
                winnerHeap.remove(curCustomer);
            }else {
                winnerHeap.resign(curCustomer);
            }
        }
        //得奖区
        winnerMove(time);
    }

    private void winnerMove(int time) throws Exception {
        if (candidateHeap.isEmpty()) {
            return;
        }
        if (winnerHeap.size() < k) {
            //候选区第一名加入
            Customer p = candidateHeap.pop();
            p.enterTime = time;
            winnerHeap.push(p);
        } else {
            if (candidateHeap.peek().buy > winnerHeap.peek().buy) {
                Customer oldDaddy = winnerHeap.pop();
                Customer newDaddy = candidateHeap.pop();
                oldDaddy.enterTime = time;
                newDaddy.enterTime = time;
                winnerHeap.push(newDaddy);
                candidateHeap.push(oldDaddy);
            }
        }
    }


    // 干完所有的事，模拟，不优化
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> cands = new ArrayList<>();
        ArrayList<Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(daddy));
                continue;
            }
            // 没有发生：用户购买数为0并且又退货了
            // 用户之前购买数是0，此时买货事件
            // 用户之前购买数>0， 此时买货
            // 用户之前购买数>0, 此时退货
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            // 买、卖
            Customer c = map.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) {
                map.remove(id);
            }
            // c
            // 下面做
            if (!cands.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    c.enterTime = i;
                    daddy.add(c);
                } else {
                    c.enterTime = i;
                    cands.add(c);
                }
            }
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            cands.sort(new CandidateComparator());
            daddy.sort(new DaddyComparator());
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    public static void move(ArrayList<Customer> cands, ArrayList<Customer> daddy, int k, int time) {
        if (cands.isEmpty()) {
            return;
        }
        // 候选区不为空
        if (daddy.size() < k) {
            Customer c = cands.get(0);
            c.enterTime = time;
            daddy.add(c);
            cands.remove(0);
        } else { // 等奖区满了，候选区有东西
            if (cands.get(0).buy > daddy.get(0).buy) {
                Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                Customer newDaddy = cands.get(0);
                cands.remove(0);
                newDaddy.enterTime = time;
                oldDaddy.enterTime = time;
                daddy.add(newDaddy);
                cands.add(oldDaddy);
            }
        }
    }

    public static void cleanZeroBuy(ArrayList<Customer> arr) {
        List<Customer> noZero = new ArrayList<Customer>();
        for (Customer c : arr) {
            if (c.buy != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Customer c : noZero) {
            arr.add(c);
        }
    }
    public static List<Integer> getCurAns(ArrayList<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : daddy) {
            ans.add(c.id);
        }
        return ans;
    }

        public static class Customer {
            public int id;
            public int buy;
            public int enterTime;

            public Customer(int customerId, int buyNum, int time) {
                id = customerId;
                buy = buyNum;
                enterTime = 0;
            }
        }
    }