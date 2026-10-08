// 05. Jump Game (LeetCode 55, Medium)
//
// You stand on index 0. nums[i] is the longest jump you can make from index i.
// Return true if you can reach the last index.
//
// Run: java JumpGame.java

public class JumpGame {

    static boolean canJump(int[] nums) {
        // far = the farthest index I can reach so far (the one thing I carry).
        //   i <  far  and  i == far : I can stand here → the same case
        //   i >  far                : I can never stand here → false
        // Two jobs at every index: 1) am I still in a valid state?  2) can I push far further?
        int far = 0;
        for(int i=0;i<nums.length;i++){
            if(i>far){
                return false;
            }
            far = Math.max(far, i+nums[i]);
        }
        return true;
    }

    // The same question as plain recursion (the brute force the greedy comes from):
    // "from index i, try every jump length; can any of them reach the end?"
    static boolean canJumpRecursive(int[] nums, int i) {
        if(i>=nums.length-1){
            return true;
        }
        for(int step=1; step<=nums[i]; step++){
            if(canJumpRecursive(nums, i+step)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        check(canJump(new int[]{2,3,1,1,4}), true);
        check(canJump(new int[]{3,2,1,0,4}), false);
        check(canJump(new int[]{0}), true);        // already on the last index
        check(canJumpRecursive(new int[]{2,3,1,1,4}, 0), true);
        check(canJumpRecursive(new int[]{3,2,1,0,4}, 0), false);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
