// 07. Jump Game VII (LeetCode 1871, Medium)
//
// s is made of '0' and '1'. You stand on index 0, which is '0'.
// From index i you can jump to index j when i + minJump <= j <= i + maxJump and s[j] is '0'.
// Return true if you can reach the last index.
//
// Run: java JumpGameVII.java

public class JumpGameVII {

    static boolean canReach(String s, int minJump, int maxJump) {
        return false;
    }

    public static void main(String[] args) {
        check(canReach("011010", 2, 3), true);
        check(canReach("01101110", 2, 3), false);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
