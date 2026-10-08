// 04. Longest Turbulent Subarray (LeetCode 978, Medium)
//
// A part of the array is turbulent when the comparison sign flips between every
// neighbouring pair: up, down, up, down... (or down, up, down, up...).
// Return the length of the longest turbulent part.
//
// Run: java LongestTurbulentSubarray.java

public class LongestTurbulentSubarray {

    static int maxTurbulenceSize(int[] arr) {
        return 0;
    }

    public static void main(String[] args) {
        check(maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}), 5);
        check(maxTurbulenceSize(new int[]{4,8,12,16}), 2);
        check(maxTurbulenceSize(new int[]{100}), 1);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
