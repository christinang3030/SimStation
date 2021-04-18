/*
Edits:
    Koosha Kimelman    4/17/21: created file
*/

package prisonerDilemma;

public class Tit4Tat extends Strategy {
    public boolean cooperate() {
        /*
        Note that cheatedLast is true if the previous interaction was a cheat, however
        cooperate() returning false is what indicates cheating the current interaction
         */
        return !myPrisoner.getCheatedLast();
    }
}
