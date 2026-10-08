// 01. Lemonade Change (LeetCode 860, Easy)
//
// A lemonade costs 5. Customers come one by one and pay with a 5, a 10 or a 20.
// You start with no money. Give each customer the correct change.
// Return true if you can serve every customer.
//
// Run: java LemonadeChange.java

public class LemonadeChange {

    static boolean lemonadeChange(int[] bills) {
        int fives = 0;
        int tens = 0;
        //for 20 bill we need to give 15, we can do this either 5+5+5 or 5+10
        for(int bill : bills){
            if(bill==5){
                fives++;
                continue;
            }
            if(bill==10 && fives>0){
                fives--;
                tens++;
                continue;
            }
            if(bill==20){
                
                //give 5 + 10
                if(fives>0 && tens>0){
                    fives--;
                    tens--;
                    continue;
                }
                //give 5+5+5
                if(fives>=3){
                    fives-=3;
                    continue;
                }
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        check(lemonadeChange(new int[]{5,5,5,10,20}), true);
        check(lemonadeChange(new int[]{5,5,10,10,20}), false);
        // the order of the two ifs for a 20 decides this one: 5 + 10 first keeps a five for the last 10
        check(lemonadeChange(new int[]{5,5,5,5,10,20,10}), true);
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
