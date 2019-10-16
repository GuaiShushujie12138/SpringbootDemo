package com.jarvis.springboot.algorithm.sort;

/**
 * 希尔排序/缩小增量排序
 * 改进的插入排序
 */
public class ShellSort implements Sort {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 5, 7, -2, 6};

        Sort sort = new ShellSort();
        sort.sort(array);

        sort.printArray(array);
    }

    @Override
    public void sort(int[] array) {
        int gap = array.length / 2;

        shellSort(array, gap);
    }

    private void shellSort(int[] array, int gap) {
        if (gap <= 0) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            int index = i;
            int value = array[i];

            while (index - gap >= 0 && array[index - gap] > value) {
                array[index] = array[index - gap];
                index -= gap;
            }

            array[index] = value;
        }

        shellSort(array, gap / 2);
    }
}
