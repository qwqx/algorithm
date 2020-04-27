import java.nio.channels.Pipe;
import java.time.Period;
import java.util.Comparator;

/**
 * 一、应用
 *      1.堆排序 2.优先级队列
 *
 */
public class MaxHeap<Key extends Comparable<Key>> {

    private Key[] pq;

    // exists node
    private int N = 0;

    MaxHeap(int cap) {
        pq = (Key[]) new Comparable[cap + 1];
    }

    public Key Max() {
        return pq[1];
    }

    public void insert(Key e) {
        pq[N + 1] = e;
        N++;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N);
        pq[N] = null;
        N--;
        sink(1);
        return max;
    }

    // 上浮
    private void swim(int k) {
        while(k > 1 && less(parent(k), k) ) {
            exch(parent(k), k);
            k = parent(k);
        }
    }

    // 下沉
    private void sink(int k) {
        while(left(k) <= N) {
            int order = left(k);
            if(right(k) <= N && less(order, right(k))) {
                order = right(k);
            }

            //fixme 此处相等也下沉了，可优化
            if(less(order, k)) {
                break;
            }

            exch(order, k);
            k = order;
        }

    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private int parent(int root) {
        return root/2;
    }

    private int left(int root) {
        return root * 2;
    }

    private int right(int root) {
        return root * 2 + 1;
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(10);
        heap.insert("d");
        heap.insert("a");
        heap.insert("b");
        heap.insert("z");
        heap.insert("v");
        heap.insert("x");

        System.out.println(heap.delMax());
        System.out.println(heap.delMax());
        System.out.println(heap.delMax());
        System.out.println(heap.delMax());
        System.out.println(heap.delMax());
        System.out.println(heap.delMax());

    }

}
