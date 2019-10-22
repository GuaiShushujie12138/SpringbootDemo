package com.jarvis.springboot.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class Q0015_ThreeNumSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        log.info("所有和为 0 的三元组:" + new Q0015_ThreeNumSum().threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums.length < 3) {
            return result;
        }

        // 从小到大排序
        Arrays.sort(nums);

        /**
         * 排序之后遍历整个数组, 当前 index 的下一个下标作为左边的指针, 数组最后一个元素下标作为右边的指针
         * 将这三个数组值相加, 如果小于 0 那么左边的指针往右 +1, 如果等于 0 那么将该三个数值记录下来, 如果大于 0 那么右指针往左 -1
         * 如果当前值大于 0 那么直接跳过
         * 如果当前值和上一个值相等, 那么也直接跳过(要求是不重复的三元组)
         */
        for (int i = 0; i < nums.length - 2; i++) {
            int curr = i;
            int leftPoint = curr + 1;
            int rightPoint = nums.length - 1;

            if (nums[curr] > 0 || (curr > 0 && nums[curr - 1] == nums[curr])) {
                continue;
            }

            while (leftPoint < rightPoint) {

                int sum = nums[curr] + nums[leftPoint] + nums[rightPoint];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[curr], nums[leftPoint], nums[rightPoint]));

                    // 去重
                    while (leftPoint < rightPoint && nums[leftPoint] == nums[leftPoint + 1]) leftPoint++;
                    while (leftPoint < rightPoint && nums[rightPoint] == nums[rightPoint - 1]) rightPoint--;

                    leftPoint++;
                    rightPoint--;
                } else if (sum > 0) {
                    rightPoint--;
                } else {
                    leftPoint++;
                }
            }
        }

        return result;
    }
}
