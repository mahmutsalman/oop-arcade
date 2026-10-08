// 03. Maximum Sum Circular Subarray (LeetCode 918, Medium)
//
// The array is a circle: after the last number comes the first one.
// Find the largest sum of a non-empty contiguous part. Each number can be used once.
//
// Run: java MaximumSumCircularSubarray.java

public class MaximumSumCircularSubarray {

    static int maxSubarraySumCircular(int[] nums) {
        // We never wrap. A wrapping part is two pieces (the two ends), awkward to search.
        // What it LEAVES OUT is one normal piece in the middle, and the total is fixed:
        //   [5, -3, 5]   total 7, left out -3 → 7 - (-3) = 10
        // So: no wrap = the largest part, wrap = total - the smallest part. Take the bigger.
        int total = nums[0];
        int runningMax = nums[0];
        int maxSum = nums[0];
        int runningMin = nums[0];
        int minSum = nums[0];
        for(int i=1; i<nums.length;i++){
            total += nums[i];
            runningMax = runningMax<0 ? nums[i] : runningMax+nums[i];
            maxSum = Math.max(maxSum,runningMax);
            runningMin = runningMin>0 ? nums[i] : runningMin+nums[i];
            minSum = Math.min(minSum,runningMin);
        }
        // all negative: the smallest part is the WHOLE array, so "wrap" would keep nothing (0).
        // The part must have at least one number, so answer with the largest single part.
        if(maxSum<0){
            return maxSum;
        }
        return Math.max(maxSum, total-minSum);
    }

    public static void main(String[] args) {
        check(maxSubarraySumCircular(new int[]{1,-2,3,-2}), 3);
        check(maxSubarraySumCircular(new int[]{5,-3,5}), 10);
        check(maxSubarraySumCircular(new int[]{-3,-2,-3}), -2);
        check(maxSubarraySumCircular(new int[]{2,-6,4}), 6);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
