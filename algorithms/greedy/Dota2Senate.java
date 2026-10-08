// 10. Dota2 Senate (LeetCode 649, Medium)
//
// Senators of two parties, R (Radiant) and D (Dire), act in order, round after round.
// On their turn a senator bans one senator of the other party for good.
// When only one party is left, it wins. Everybody plays the best strategy.
// Return "Radiant" or "Dire".
//
// Run: java Dota2Senate.java

public class Dota2Senate {

    static String predictPartyVictory(String senate) {
        return "";
    }

    public static void main(String[] args) {
        check(predictPartyVictory("RD"), "Radiant");
        check(predictPartyVictory("RDD"), "Dire");
    }

    static void check(Object got, Object expected) {
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") + "  got " + got + ", expected " + expected);
    }
}
