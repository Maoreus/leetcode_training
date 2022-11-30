package class08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 加强堆
 * 带有反向索引表
 */
public class HeapGreater<T> {
    public ArrayList<T> data;
    public HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;

    public HeapGreater(Comparator<? super T> c) {
        data = new ArrayList<T>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comparator = c;
    }


    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public void push(T obj) {
        data.add(obj);
        //索引也要更新
        indexMap.put(obj, heapSize);
        //insert堆
        heapInsert(data, heapSize, comparator);
        heapSize++;
    }

    public T pop() {
        T ans = data.get(0);
        swap(data, 0, --heapSize);
        //索引删去
        indexMap.remove(ans);
        //数据集删去数据
        data.remove(heapSize);
        heapify(data, 0);
        return ans;
    }

    //下沉
    private void heapify(ArrayList<T> data, int index) {
        int left = 2 * index + 1;
        while(left < heapSize) {
            int biggerChild = left + 1 < heapSize && comparator.compare(data.get(left), data.get(left + 1)) < 0 ? left + 1 : left;
            int largest = comparator.compare(data.get(biggerChild), data.get(index)) < 0 ? index : biggerChild;
            if (largest == index) {
                break;
            }
            swap(data, index, largest);
            index = largest;
            left = 2 * index + 1;

        }
    }

    //上浮
    private void heapInsert(ArrayList<T> arr, int index, Comparator<? super T> comparator) {
        while (comparator.compare(arr.get(index), arr.get(index - 1  / 2)) > 0) {
            swap(arr, index, index * 2 + 1);
            index = index - 1 / 2;
        }
    }


    private void swap(ArrayList<T> arr, int i, int j) {
        //index也要同步
        indexMap.put(arr.get(i), j);
        indexMap.put(arr.get(j), i);
        //交换数据集的值
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
        
    }




}
