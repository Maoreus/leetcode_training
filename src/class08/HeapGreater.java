package class08;

import java.util.*;

/**
 * 加强堆
 */
public class HeapGreater<T> {

    private HashMap<T, Integer> indexMap;
    public ArrayList<T> data;
    private Comparator<? super T> comparator;
    private int heapSize;
    private int limit;

    public HeapGreater(Comparator<? super T> c) {
        comparator = c;
        heapSize = 0;
    }

    public void push(T value) throws Exception {
        if (heapSize == limit) {
            throw new Exception("full");
        }
        //list中加入这个数
        data.add(value);
        heapInsert(data, heapSize++);
        //反向索引表同步更新
        indexMap.put(value, heapSize);
    }


    /**
     *  新加入的节点放在最后的位置，不停与父节点比较
     * @param data
     * @param index
     */
    private void heapInsert(ArrayList<T> data, int index) {
        while (comparator.compare(data.get(index), data.get((index - 1) / 2)) < 0) {
            swap(data, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(ArrayList<T> data, int i, int j) {
        //更新数据集
        T t1 = data.get(i);
        T t2 = data.get(j);
        data.set(i, t2);
        data.set(j, t1);
        //更新索引
        indexMap.put(t1, j);
        indexMap.put(t2, i);
    }

    /**
     * 弹出堆顶元素，交换堆顶和最后一个元素，下沉
     * @return
     */
    public T pop(ArrayList<T> data) throws Exception {
        if (heapSize == 0) {
            throw new Exception("empty");
        }
        //数据集删除这个元素
        T ans = data.get(0);
        swap(data, 0, --heapSize);
        heapify(data, 0, heapSize);
        //索引中移除这个
        indexMap.remove(ans);
//        data.remove(0); 最后一个元素相当于废弃，后面会被set进来。逻辑删除
        return ans;
    }

    public T pop() throws Exception {
        if (heapSize == 0) {
            throw new Exception("empty");
        }
        //数据集删除这个元素
        T ans = data.get(0);
        swap(data, 0, --heapSize);
        heapify(data, 0, heapSize);
        //索引中移除这个
        indexMap.remove(ans);
//        data.remove(0); 最后一个元素相当于废弃，后面会被set进来。逻辑删除
        return ans;
    }

    /**
     * 下沉，把交换的头结点不断向下（直到没有子节点比他大）
     * @param data
     * @param index
     * @param heapSize
     */
    private void heapify(ArrayList<T> data, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int biggerChild = left + 1 < heapSize && comparator.compare(data.get(left), data.get(left + 1)) < 0 ? left + 1 : left;
            int largest = comparator.compare(data.get(index), data.get(biggerChild)) < 0 ? biggerChild : index;
            if (largest == index) {
                break;
            }
            //把当前位置跟较大的孩子互换位置
            swap(data, index, largest);
            index = largest;
            //更新索引
            left = 2 * index + 1;

        }
    }

    public T peek() {
        return data.get(0);
    }

    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T t :  data) {
            ans.add(t);
        }
        return ans;
    }

    public void remove(T obj) {
        T replace = data.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        data.remove(--heapSize);
        //如果要删除的是最后一个数，就不需要继续了
        if (replace != obj) {
            //把这个换成新的
            data.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }

    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public int size() {
        return indexMap.size();
    }

    public void resign(T replace) {
        heapInsert(data, indexMap.get(replace));
        heapify(data, indexMap.get(replace), heapSize);
    }


    public boolean isEmpty() {
        return heapSize == 0;
    }
}
