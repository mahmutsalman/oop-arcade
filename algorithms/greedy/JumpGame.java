// 05. Jump Game (LeetCode 55, Medium)
//
// You stand on index 0. nums[i] is the longest jump you can make from index i.
// Return true if you can reach the last index.
//
// Run: java JumpGame.java

public class JumpGame {

    static boolean canJump(int[] nums) {
        return false;
    }

    public static void main(String[] args) {
        check(canJump(new int[]{2,3,1,1,4}), true);
        check(canJump(new int[]{3,2,1,0,4}), false);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
