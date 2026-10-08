// 06. Jump Game II, as plain recursion (the brute force the greedy comes from)
//
// The function answers ONE question:
//   "I stand on index i. What is the fewest number of jumps to reach the last index from here?"
// Try EVERY jump length.
//
// Run: java JumpGameIIRecursion.java

public class JumpGameIIRecursion {

    static int fewestJumps(int[] nums, int i) {
        return 0;
    }

    public static void main(String[] args) {
        check(fewestJumps(new int[]{2,3,1,1,4}, 0), 2);
        check(fewestJumps(new int[]{2,3,0,1,4}, 0), 2);
        check(fewestJumps(new int[]{7}, 0), 0);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
