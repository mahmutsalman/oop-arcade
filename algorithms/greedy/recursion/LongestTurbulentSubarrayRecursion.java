// 04. Longest Turbulent Subarray, as plain recursion (the brute force the greedy comes from)
//
// The function answers ONE question:
//   "How many numbers are in the turbulent part that ENDS at index i?"
// maxTurbulenceSize asks it for every i and keeps the biggest answer.
//
// Run: java LongestTurbulentSubarrayRecursion.java

public class LongestTurbulentSubarrayRecursion {

    static int endsAt(int[] arr, int i) {
        return 0;
    }

    static int maxTurbulenceSize(int[] arr) {
        int best = 1;
        for(int i=0;i<arr.length;i++){
            best = Math.max(best, endsAt(arr, i));
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}), 5);
        check(maxTurbulenceSize(new int[]{4,8,12,16}), 2);
        check(maxTurbulenceSize(new int[]{100}), 1);
        check(maxTurbulenceSize(new int[]{9,9,9}), 1);
        check(maxTurbulenceSize(new int[]{1,3,2,4,5,3}), 4);
        check(maxTurbulenceSize(new int[]{5,5,1}), 2);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
