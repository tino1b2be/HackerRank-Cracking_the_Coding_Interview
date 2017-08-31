import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class Heap {

        int[] data = new int[10];
        int size = 0;

        void checkSize() {
            if (size >= data.length)
                data = Arrays.copyOf(data, data.length * 2);
        }

        int getParentIndex(int i) {
            return (i - 1) / 2;
        }

        boolean hasParent(int index) { return getParentIndex(index) >= 0;};

        boolean hasLeftChild(int i) {
            return getLeftChildIndex(i) < size;
        }

        boolean hasRightChild(int i) {
            return getRightChildIndex(i) < size;
        }

        int getLeftChildIndex(int i) {
            return 2 * i + 1;
        }

        int getRightChildIndex(int i) {
            return 2 * i + 2;
        }

        void swapNodes(int one, int two) {
            int temp = data[one];
            data[one] = data[two];
            data[two] = temp;
        }

        int peek() {
            return data[0];
        }

    }

    static class MinHeap extends Heap {

        public void push(int n) {
            ++size;
            checkSize();
            data[size - 1] = n;
            heapifyUp();
        }

        private void heapifyUp() {
            int index = size - 1;
            while (hasParent(index) && data[index] < data[getParentIndex(index)]) {
                swapNodes(index, getParentIndex(index));
                index = getParentIndex(index);
            }
        }


        public int pop() {
            int v = data[0];
            data[0] = data[size - 1];
            data[size - 1] = -1;
            --size;
            heapifyDown();
            return v;
        }

        private void heapifyDown() {
            int index = 0;
            while (hasLeftChild(index)) {
                if (data[index] > data[getLeftChildIndex(index)]) {
                    if (hasRightChild(index) && data[getLeftChildIndex(index)] > data[getRightChildIndex(index)]) {
                        swapNodes(index, getRightChildIndex(index));
                        index = getRightChildIndex(index);
                    } else {
                        swapNodes(index, getLeftChildIndex(index));
                        index = getLeftChildIndex(index);
                    }
                } else if (hasRightChild(index) && data[getLeftChildIndex(index)] > data[getRightChildIndex(index)]) {
                    swapNodes(index, getRightChildIndex(index));
                    index = getRightChildIndex(index);
                } else {
                    break;
                }
            }
        }
    }

    static class MaxHeap extends Heap {

        public void push(int n) {
            ++size;
            checkSize();
            data[size - 1] = n;
            heapifyUp();
        }

        private void heapifyUp() {
            int index = size - 1;
            while (hasParent(index) && data[index] > data[getParentIndex(index)]) {
                swapNodes(index, getParentIndex(index));
                index = getParentIndex(index);
            }
        }

        public int pop() {
            int v = data[0];
            heapifyDown();
            return v;
        }

        private void heapifyDown() {

            data[0] = data[size - 1];
            data[size - 1] = -1;
            --size;
            int index = 0;
            while (hasLeftChild(index)) {
                if (data[index] < data[getLeftChildIndex(index)]) {
                    if (hasRightChild(index) && data[getLeftChildIndex(index)] < data[getRightChildIndex(index)]) {
                        swapNodes(index, getRightChildIndex(index));
                        index = getRightChildIndex(index);
                    } else {
                        swapNodes(index, getLeftChildIndex(index));
                        index = getLeftChildIndex(index);
                    }
                } else if (hasRightChild(index) && data[getLeftChildIndex(index)] < data[getRightChildIndex(index)]) {
                    swapNodes(index, getRightChildIndex(index));
                    index = getRightChildIndex(index);
                } else {
                    break;
                }
            }
        }
    }

    static class Median {
        // keep two heaps
        // min heap is min of the top half
        // max heap is max of bottom half
        private MinHeap min = new MinHeap();
        private MaxHeap max = new MaxHeap();
        private int size = 0;
        private double median = 0;

        public void add(int n) {
            ++size;
            if (max.size > 0 && n <= max.peek())
                max.push(n);
            else
                min.push(n);

            while (max.size > min.size) {
                min.push(max.pop());
            }

            while (min.size - max.size > 1) {
                max.push(min.pop());
            }
        }

        public double getMedian() {
            if (min.size != max.size)
                // if odd, pop from mean
                return (double) min.peek();
            else
                // if even, get average of the two centre values.
                return (double) (min.peek() + max.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        Median m = new Median();
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
            m.add(a[a_i]);
            System.out.printf("%.1f\n", m.getMedian());
        }
    }
}
