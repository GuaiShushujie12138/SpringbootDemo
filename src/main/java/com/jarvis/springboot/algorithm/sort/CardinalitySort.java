package com.jarvis.springboot.algorithm.sort;

/**
 * 基数排序
 * 算法思想: 先根据个位数进行排序, 之后依次对十位、百位排序, 找出最大的数计算总共需要排序的趟数
 */
public class CardinalitySort implements Sort {

    public static void main(String[] args) {
        int[] array = {3, 23, 112, 1110, 290};

        new CardinalitySort().sort(array);
    }

    @Override
    public void sort(int[] array) {
        int maxNum = findMax(array);

        for (int i = 1; maxNum / i > 0; i *= 10) {
            int[][] buckets = new int[array.length][10];

            for (int j = 0; j < array.length; j++) {
                int num = (array[j] / i) % 10;
                buckets[j][num] = array[j];
            }

            // 将 buckets 中的值再塞回 array, 一列一列的回收
            int k = 0;
            for (int j = 0; j < 10; j++) {
                for (int l = 0; l < array.length; l++) {
                    if (buckets[l][j] != 0) {
                        array[k++] = buckets[l][j];
                    }
                }
            }
        }
    }

    private int findMax(int[] array) {
        int max = array[0];

        for (int i : array) {
            max = Math.max(max, i);
        }

        return max;
    }
}
