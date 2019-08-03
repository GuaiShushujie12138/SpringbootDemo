package com.jarvis.springboot.algorithm.sort;

public class SelectionSort implements Sort {

    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i; j < array.length; j++) {
                min = array[min] > array[j] ? j : min;
            }

            swapTwoNum(array, min, i);
        }
    }
}
