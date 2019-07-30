package com.jarvis.springboot.algorithm.sort;

public class QuickSort implements Sort {

    @Override
    public void sort(int[] array) {
        int left = 0;
        int right = array.length - 1;

        quickSort(array, left, right);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }

        int baseLeft = left;
        int baseRight = right;
        int base = array[left];

        while (right > left) {
            while (right > left && array[right] >= base) {
                right--;
            }

            while (right > left && array[left] <= base) {
                left++;
            }

            if (right > left) {
                // change left and right
                swapTwoNum(array, left, right);
            }
        }

        // base
        array[baseLeft] = array[left];
        array[left] = base;

        // recursive call quickSort
        quickSort(array, baseLeft, left - 1);
        quickSort(array, left + 1, baseRight);
    }
}
