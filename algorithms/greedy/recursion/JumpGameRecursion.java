// 05. Jump Game, as plain recursion (the brute force the greedy comes from)
//
// The function answers ONE question:
//   "I stand on index i. Can I reach the last index from here?"
// Try EVERY jump length.
//
// Run: java JumpGameRecursion.java

public class JumpGameRecursion {

    static boolean canReach(int[] nums, int i) {
        return false;
    }

    public static void main(String[] args) {
        check(canReach(new int[]{2,3,1,1,4}, 0), true);
        check(canReach(new int[]{3,2,1,0,4}, 0), false);
        check(canReach(new int[]{0}, 0), true);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
