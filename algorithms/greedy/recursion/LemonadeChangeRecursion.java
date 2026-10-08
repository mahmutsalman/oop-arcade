// 01. Lemonade Change, as plain recursion (the brute force the greedy comes from)
//
// The function answers ONE question:
//   "I am at customer i and I hold `fives` fives and `tens` tens. Can I serve everybody from here?"
// Try EVERY way to give the change, do not choose the clever one.
//
// Run: java LemonadeChangeRecursion.java

public class LemonadeChangeRecursion {

    static boolean serve(int[] bills, int i, int fives, int tens) {
        if(i==bills.length){
            return true;
        }
        boolean a = false;
        boolean b = false;
        //if statement
        if(bills[i]==5){
            fives++;
            a = serve(bills,i+1,fives,tens);
        }
        if(bills[i]==10 ){
            if(fives>0){
                 fives--;
                tens++;
                b = serve(bills,i+1,fives,tens);
            }
            else{
                return false;
            }
           
        }
        
        boolean x = false;
        boolean y = false;
        if(bills[i]==20){
            if(fives>=3){
                x = serve(bills,i+1,fives-3,tens); // 5 5 5
            }
            if(fives>=1 && tens>=1){
                y = serve(bills,i+1,fives-1,tens-1);
            }
             
        }
        
        return a || b || x || y;
    }

    public static void main(String[] args) {
        check(serve(new int[]{5,5,5,10,20}, 0, 0, 0), true);
        check(serve(new int[]{5,5,10,10,20}, 0, 0, 0), false);
        check(serve(new int[]{5,5,5,5,10,20,10}, 0, 0, 0), true);
        check(serve(new int[]{10}, 0, 0, 0), false);          // a 10 and no five to give back
        check(serve(new int[]{5,10,10}, 0, 0, 0), false);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
