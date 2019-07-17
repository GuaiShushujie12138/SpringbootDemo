package com.jarvis.springboot.algorithm.leetcode;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 */
public class Q0001_TwoSum {

    public static void main(String[] args) {
        int nums[] = {1, 3, 5, 0, -2, 4, 6, 3};
        System.out.println("result:" + Arrays.toString(new Q0001_TwoSum().twoSum(nums, 6)));
        System.out.println("result:" + Arrays.toString(new Q0001_TwoSum().twoSumByOneHash(nums, 6)));
        System.out.println("result:" + Arrays.toString(new Q0001_TwoSum().twoSumByDoubleHash(nums, 6)));
    }

    /**
     * 时间复杂度 : O(n^2)
     * 空间复杂度 : O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

    /**
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumByDoubleHash(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> arrayMap = Maps.newHashMap();

        for (int i = 0; i < nums.length; i++) {
            arrayMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (arrayMap.containsKey(target - nums[i]) && i != arrayMap.get(target - nums[i])) {
                result[0] = i;
                result[1] = arrayMap.get(target - nums[i]);
                break;
            }
        }

        return result;
    }

    /**
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumByOneHash(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> arrayMap = Maps.newHashMap();

        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (arrayMap.containsKey(value)) {
                result[0] = arrayMap.get(target - nums[i]);
                result[1] = i;
                break;
            }

            arrayMap.put(nums[i], i);
        }

        return result;
    }
}
