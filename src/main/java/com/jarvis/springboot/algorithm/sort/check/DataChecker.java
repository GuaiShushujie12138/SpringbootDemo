package com.jarvis.springboot.algorithm.sort.check;

import com.jarvis.springboot.algorithm.sort.CountSort;
import com.jarvis.springboot.algorithm.sort.Sort;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class DataChecker {

    public static void main(String[] args) {
        int[] array = initArray();
        int[] array2 = copyArray(array);

        Sort sort = new CountSort();

        log.info("排序前:");
        sort.printArray(array);
        sort.printArray(array2);

        log.info("排序前两数组是否相同:" + sort.twoArrayEqual(array, array2));

        sort.sort(array);
        Arrays.sort(array2);

        log.info("排序后:");
        sort.printArray(array);
        sort.printArray(array2);

        log.info("排序后两数组是否相同:" + sort.twoArrayEqual(array, array2));
    }

    private static int[] copyArray(int[] array) {
        int[] arrayCopy = new int[array.length];

        System.arraycopy(array, 0, arrayCopy, 0, array.length);

        return arrayCopy;
    }

    private static int[] initArray() {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }

        return array;
    }
}
