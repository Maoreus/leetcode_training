package class07;

public class Heap {
    int[] arr;
    public int heapSize;
    int limit;
    public Heap(int limit) {
        arr = new int[limit];
        this.limit = limit;
        heapSize = 0;
    }

    public void push(int value) throws Exception {
        if (heapSize == limit) {
            throw new Exception("达到上限了");
        }
        //将value加入数组
        arr[heapSize] = value;
        //调整堆
        heapInsert(arr, heapSize);
        heapSize++;
    }

    /**
     * 返回最大值，并且在大根堆中，把最大值删掉
     * @return
     * @throws Exception
     */
    public int pop() throws Exception {
        if (heapSize == 0) {
            throw new Exception("空了");
        }
        int res = arr[0];
        swap(arr, 0 , --heapSize);
        //从最后一个位置开始，已经把头部跟尾部交换过了
        heapify(arr, 0);
        return res;
    }
    /**
     * 上升，直到不比父节点大
     * @param arr
     * @param index
     */
    public void heapInsert(int[] arr, int index) {
        while (arr[index] < arr[(index - 1) / 2] && index >= 0) {
            //比父节点大要交换
            swap(arr, index, (index - 1) / 2);
            //数组中小的下标在前面，即向上
            index = (index - 1) / 2;
        }

    }
    public  void heapify(int[] arr, int index) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            //左右孩子中选大的那个index
            int biggerIndex = left + 1 <= heapSize && arr[left] < arr[left + 1] ? left + 1 : left ;
            //左右孩子跟父节点比较
            int largest = arr[biggerIndex] > arr[index] ? biggerIndex : index;
            if (largest ==  index) {
                break;
            }
            //互换index和较大的孩子
             swap(arr, index, largest);
            left = 2 * largest + 1;
        }


    }

    private void swap(int[] arr, int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }
}

