package com.jarvis.springboot.algorithm.sort;

public class MergeSort implements Sort {

    public static void main(String[] args) {
        int array[] = {1, 3, 5, 2, 4, 6};
        Sort sort = new MergeSort();
        sort.sort(array);
        sort.printArray(array);
    }

    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int start, int end) {
        if (array.length <= 1 || end - start <= 0) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);

        int[] arr = new int[end - start + 1];
        int l = start;
        int m = mid + 1;
        int i = 0;
        while (l <= mid && m <= end) {
            arr[i++] = array[l] < array[m] ? array[l++] : array[m++];
        }
        if (l <= mid) {
            System.arraycopy(array, l, arr, i, arr.length - i);
        }
        if (m <= end) {
            System.arraycopy(array, m, arr, i, arr.length - i);
        }

        // 将 arr 拷贝到 array
        System.arraycopy(arr, 0, array, start, arr.length);
    }
}
