// 09. Hand of Straights (LeetCode 846, Medium)
//
// Split all the cards into groups. Every group has groupSize cards with consecutive values.
// Return true if that is possible.
//
// Run: java HandOfStraights.java

public class HandOfStraights {

    static boolean isNStraightHand(int[] hand, int groupSize) {
        return false;
    }

    public static void main(String[] args) {
        check(isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3), true);
        check(isNStraightHand(new int[]{1,2,3,4,5}, 4), false);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
