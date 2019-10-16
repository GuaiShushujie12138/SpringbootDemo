package com.jarvis.springboot.algorithm.sort;

/**
 * 堆排序, 可使用最大堆和最小堆
 * 最大堆 : 父节点比左右子节点都大
 * 最小堆 : 父节点比左右子节点都小
 */
public class HeapSort implements Sort {

    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            maxHeap(array, array.length - i);

            // 每次构造完最大堆, 交换最后节点和根节点的数
            swapTwoNum(array, 0, array.length - 1 - i);
        }
    }

    /**
     * 最大堆
     * 从数组最后一个数字开始构建
     *
     * @param array
     * @param length
     */
    private void maxHeap(int[] array, int length) {
        for (int i = length - 1; i >= 0; i--) {
            heap(array, i, length);
        }
    }

    /**
     * 在 currentIndex 处构造最大堆, 完成一次堆构建
     *
     * @param array
     * @param currentIndex
     * @param length
     */
    private void heap(int[] array, int currentIndex, int length) {
        // 完全二叉树, 左子节点 = current * 2 + 1, 右子节点 = current * 2 + 2
        int leftChild = currentIndex * 2 + 1;
        int rightChild = currentIndex * 2 + 2;

        int maxIndex = currentIndex;

        if (leftChild < length && array[leftChild] > array[maxIndex]) {
            maxIndex = leftChild;
        }

        if (rightChild < length && array[rightChild] > array[maxIndex]) {
            maxIndex = rightChild;
        }

        if (maxIndex != currentIndex) {
            // 交换根节点和子节点
            swapTwoNum(array, currentIndex, maxIndex);

            // 子节点继续往下进行构造堆
            heap(array, maxIndex, length);
        }
    }
}
