// 01. Lemonade Change (LeetCode 860, Easy)
//
// A lemonade costs 5. Customers come one by one and pay with a 5, a 10 or a 20.
// You start with no money. Give each customer the correct change.
// Return true if you can serve every customer.
//
// Run: java LemonadeChange.java

public class LemonadeChange {

    static boolean lemonadeChange(int[] bills) {
        return false;
    }

    public static void main(String[] args) {
        check(lemonadeChange(new int[]{5,5,5,10,20}), true);
        check(lemonadeChange(new int[]{5,5,10,10,20}), false);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
