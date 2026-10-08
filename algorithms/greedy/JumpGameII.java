// 06. Jump Game II (LeetCode 45, Medium)
//
// You stand on index 0. nums[i] is the longest jump you can make from index i.
// The last index can always be reached. Return the fewest jumps to reach it.
//
// Run: java JumpGameII.java

public class JumpGameII {

    static int jump(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        check(jump(new int[]{2,3,1,1,4}), 2);
        check(jump(new int[]{2,3,0,1,4}), 2);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
