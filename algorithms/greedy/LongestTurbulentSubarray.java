// 04. Longest Turbulent Subarray (LeetCode 978, Medium)
//
// A part of the array is turbulent when the comparison sign flips between every
// neighbouring pair: up, down, up, down... (or down, up, down, up...).
// Return the length of the longest turbulent part.
//
// Run: java LongestTurbulentSubarray.java

public class LongestTurbulentSubarray {

    static int maxTurbulenceSize(int[] arr) {
        if(arr.length==1){
            return 1;
        }
        // Work on the STEPS, not the numbers: signum(this - previous) = -1 down, 0 equal, 1 up.
        // length = how many NUMBERS are in the turbulent part that ends here.
        //   equal step (8,8)            → 1   only the number itself
        //   same direction (4,8,12)     → 2   the last two numbers are still a valid part
        //   different direction         → +1
        int prev = Integer.signum(arr[1]-arr[0]);
        int length = prev==0 ? 1 : 2;
        int maxLength = length;
        for(int i=2;i<arr.length;i++){
            int curr = Integer.signum(arr[i]-arr[i-1]);
            if(curr==0){
                length=1;
            }
            else if(prev!=curr){
                length+=1;
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
        check(maxTurbulenceSize(new int[]{5,5,1}), 2);     // the part is 5,1
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
