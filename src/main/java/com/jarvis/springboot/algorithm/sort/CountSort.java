package com.jarvis.springboot.algorithm.sort;

/**
 * 计数排序
 * 思想: 记录每个数字出现的次数, 不使用比较
 * 适用于待排序数组中数字范围较小, 数组较大的场景
 * 实际场景 : 1. 给定一个高考分数, 算出第几名; 2. 对公司人员进行年龄排序
 */
public class CountSort implements Sort {

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }

        Sort sort = new CountSort();

        System.out.println("排序前:");
        sort.printArray(array);

        sort.sort(array);

        System.out.println("排序后:");
        sort.printArray(array);
    }

    @Override
    public void sort(int[] array) {
        // count 数组代表每个数字出现的次数
        int[] count = new int[101];
        for (int i : array) {
            count[i] += 1;
        }

        // arr 数组代表的是正确排序后的该数字的最后下标值
        int[] arr = new int[101];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                arr[i] = count[i];
            } else {
                arr[i] = arr[i - 1] + count[i];
            }
        }

        int[] newArr = new int[array.length];

        for (int i = array.length - 1; i >= 0; i--) {
            int value = arr[array[i]];
            newArr[value - 1] = array[i];
            arr[array[i]] -= 1;
        }

        System.arraycopy(newArr, 0, array, 0, array.length);
    }
}
