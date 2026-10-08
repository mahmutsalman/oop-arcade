// 02. Maximum Subarray (LeetCode 53, Medium)
//
// Find the contiguous part of the array (at least one number) with the largest sum.
// Return that sum.
//
// Run: java MaximumSubarray.java

public class MaximumSubarray {

    static int maxSubArray(int[] nums) {
        // runningSum = the best sum of a part that ENDS at the current number.
        // A negative running sum never helps the next number, so drop it and start again:
        //   -10  5 | 100   carry -5 → 95, worse than 100 alone → drop
        //    15 -10 | 100  carry  5 → 105, better than 100 alone → keep
        int runningSum = nums[0];
        int maxSum = nums[0];
        for(int i=1; i<nums.length;i++){
            if(runningSum<0){
                runningSum = nums[i];
            }
            else{
                runningSum+=nums[i];
            }
            maxSum = Math.max(maxSum,runningSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        check(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
        check(maxSubArray(new int[]{1}), 1);
        check(maxSubArray(new int[]{5,4,-1,7,8}), 23);
        check(maxSubArray(new int[]{5,-9}), 5);   // the best part is the first number alone
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
