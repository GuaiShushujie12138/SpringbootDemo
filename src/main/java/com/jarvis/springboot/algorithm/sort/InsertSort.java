package com.jarvis.springboot.algorithm.sort;

public class InsertSort implements Sort {

    public static void main(String[] args) {
        int[] array = {1, -3, 2, 0, 8, 10, 13, 7, 6};
        Sort sort = new InsertSort();
        sort.sort(array);
        sort.printArray(array);
    }

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            int value = array[i];

            while (j > 0 && array[j - 1] > value) {
                array[j] = array[j - 1];
                j--;
            }

            array[j] = value;
        }
    }
}
