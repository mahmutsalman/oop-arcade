// 08. Gas Station (LeetCode 134, Medium)
//
// Gas stations stand on a circle. Station i gives gas[i]; driving from i to i + 1 costs cost[i].
// You start with an empty tank at a station you choose.
// Return the start index that lets you drive the full circle once, or -1 if none does.
//
// Run: java GasStation.java

public class GasStation {

    static int canCompleteCircuit(int[] gas, int[] cost) {
        return 0;
    }

    public static void main(String[] args) {
        check(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}), 3);
        check(canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}), -1);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
