// 01. Lemonade Change, as plain recursion (the brute force the greedy comes from)
//
// The function answers ONE question:
//   "I am at customer i and I hold `fives` fives and `tens` tens. Can I serve everybody from here?"
// Try EVERY way to give the change, do not choose the clever one.
//
// Run: java LemonadeChangeRecursion.java

public class LemonadeChangeRecursion {

    static boolean serve(int[] bills, int i, int fives, int tens) {
        return false;
    }

    public static void main(String[] args) {
        check(serve(new int[]{5,5,5,10,20}, 0, 0, 0), true);
        check(serve(new int[]{5,5,10,10,20}, 0, 0, 0), false);
        check(serve(new int[]{5,5,5,5,10,20,10}, 0, 0, 0), true);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
