// 04. Longest Turbulent Subarray (LeetCode 978, Medium)
//
// A part of the array is turbulent when the comparison sign flips between every
// neighbouring pair: up, down, up, down... (or down, up, down, up...).
// Return the length of the longest turbulent part.
//
// Run: java LongestTurbulentSubarray.java

public class LongestTurbulentSubarray {

    static int maxTurbulenceSize(int[] arr) {
        // length = how many NUMBERS are in the turbulent part that ends here
        int prev = 0;
        int length = 1;      // the first number alone
        int maxLength = 1;
        for(int i=1;i<arr.length;i++){
            int curr = Integer.signum(arr[i]-arr[i-1]);
            if(curr==0){              // rule 1: no difference → reset
                length=1;
            }
            else if(prev!=curr){      // rule 2: it alternates → one more
                length = length+1;
            }
            else{
                length=2;
            }
            maxLength = Math.max(maxLength,length);
            prev = curr;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        check(maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}), 5);
        check(maxTurbulenceSize(new int[]{4,8,12,16}), 2);
        check(maxTurbulenceSize(new int[]{100}), 1);
        check(maxTurbulenceSize(new int[]{8,8}), 1);       // equal neighbours: only one number counts
        check(maxTurbulenceSize(new int[]{9,9,9}), 1);
        check(maxTurbulenceSize(new int[]{1,3,2,4,5,3}), 4);   // 1,3,2,4 then 5 goes up again
        check(maxTurbulenceSize(new int[]{5,5,1}), 2);     // the part is 5,1
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
