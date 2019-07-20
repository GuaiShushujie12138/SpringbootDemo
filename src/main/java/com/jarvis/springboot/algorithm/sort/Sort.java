package com.jarvis.springboot.algorithm.sort;

public interface Sort {

    void sort(int[] array);

    /**
     * print array
     *
     * @param array
     */
    default void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }

        System.out.println();
    }

    /**
     * swap two num in array
     *
     * @param array
     * @param x
     * @param y
     */
    default void swapTwoNum(int[] array, int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

    /**
     * compare two array
     *
     * @param array1
     * @param array2
     * @return
     */
    default boolean twoArrayEqual(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }

        return true;
    }
}
