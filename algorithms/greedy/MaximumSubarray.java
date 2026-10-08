// 02. Maximum Subarray (LeetCode 53, Medium)
//
// Find the contiguous part of the array (at least one number) with the largest sum.
// Return that sum.
//
// Run: java MaximumSubarray.java

public class MaximumSubarray {

    static int maxSubArray(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        check(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
        check(maxSubArray(new int[]{1}), 1);
        check(maxSubArray(new int[]{5,4,-1,7,8}), 23);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
