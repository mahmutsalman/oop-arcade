// 06. Jump Game II (LeetCode 45, Medium)
//
// You stand on index 0. nums[i] is the longest jump you can make from index i.
// The last index can always be reached. Return the fewest jumps to reach it.
//
// Run: java JumpGameII.java

public class JumpGameII {

    // Greedy = walking the BFS levels without a queue.
    //   jumps = the level I am in   end = the last index of this level   far = the last index of the next level
    static int jump(int[] nums) {
        int jumps = 0;
        int end = 0;
        int far = 0;
        for(int i=0;i<nums.length-1;i++){
            far = Math.max(far, i+nums[i]);
            if(i==end){          // the level is finished: one more jump, the next level starts
                jumps++;
                end = far;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        check(jump(new int[]{2,3,1,1,4}), 2);
        check(jump(new int[]{2,3,0,1,4}), 2);
        check(jump(new int[]{7}), 0);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
