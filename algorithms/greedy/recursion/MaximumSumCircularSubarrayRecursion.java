// 03. Maximum Sum Circular Subarray, as plain recursion (the brute force the greedy comes from)
//
// Here the brute force really WRAPS, with the modulo: the number at circular position p is nums[p % nums.length].
// The function answers ONE question:
//   "A part begins at index start and has already taken k numbers. I must take the next one.
//    What is the largest sum I can still collect?"  (a part can never have more than nums.length numbers)
// maxSubarraySumCircular asks it for every start, with k = 0.
//
// Run: java MaximumSumCircularSubarrayRecursion.java

public class MaximumSumCircularSubarrayRecursion {

    static int bestFrom(int[] nums, int start, int k) {
        return 0;
    }

    static int maxSubarraySumCircular(int[] nums) {
        int best = Integer.MIN_VALUE;
        for(int start=0;start<nums.length;start++){
            best = Math.max(best, bestFrom(nums, start, 0));
        }
        return best;
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
