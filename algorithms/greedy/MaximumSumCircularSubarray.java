// 03. Maximum Sum Circular Subarray (LeetCode 918, Medium)
//
// The array is a circle: after the last number comes the first one.
// Find the largest sum of a non-empty contiguous part. Each number can be used once.
//
// Run: java MaximumSumCircularSubarray.java

public class MaximumSumCircularSubarray {

    static int maxSubarraySumCircular(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        check(maxSubarraySumCircular(new int[]{1,-2,3,-2}), 3);
        check(maxSubarraySumCircular(new int[]{5,-3,5}), 10);
        check(maxSubarraySumCircular(new int[]{-3,-2,-3}), -2);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
