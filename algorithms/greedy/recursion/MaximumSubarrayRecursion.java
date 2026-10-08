// 02. Maximum Subarray, as plain recursion (the brute force the greedy comes from)
//
// The function answers ONE question:
//   "What is the largest sum of a part that ENDS at index i?"
// maxSubArray then asks it for every i and keeps the biggest answer.
//
// Run: java MaximumSubarrayRecursion.java

public class MaximumSubarrayRecursion {

    static int bestEndingAt(int[] nums, int i) {
        return 0;
    }

    static int maxSubArray(int[] nums) {
        int best = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            best = Math.max(best, bestEndingAt(nums, i));
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
        check(maxSubArray(new int[]{1}), 1);
        check(maxSubArray(new int[]{5,4,-1,7,8}), 23);
        check(maxSubArray(new int[]{5,-9}), 5);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
