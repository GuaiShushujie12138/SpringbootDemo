package com.jarvis.springboot.algorithm.leetcode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class Q0004_TwoSortedArrayMedian {

    public static void main(String[] args) {
        int nums1[] = {}, nums2[] = {1};
        System.out.println("中位数:" + new Q0004_TwoSortedArrayMedian().findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int newArrayLength = nums1.length + nums2.length;
        int[] nums = new int[newArrayLength];

        int i = 0, j = 0, k = 0;
        for (; i < nums1.length && j < nums2.length; k++) {
            if (nums1[i] <= nums2[j]) {
                nums[k] = nums1[i];
                i++;
            } else {
                nums[k] = nums2[j];
                j++;
            }
        }

        if (i <= nums1.length - 1) {
            System.arraycopy(nums1, i, nums, k, nums1.length - i);
        }
        if (j <= nums2.length - 1) {
            System.arraycopy(nums2, j, nums, k, nums2.length - j);
        }

        if (newArrayLength == 1) {
            return nums[0];
        }

        if (newArrayLength % 2 == 0) {
            return (nums[newArrayLength / 2] + nums[newArrayLength / 2 - 1]) / 2.0;
        } else {
            return nums[newArrayLength / 2];
        }
    }
}
