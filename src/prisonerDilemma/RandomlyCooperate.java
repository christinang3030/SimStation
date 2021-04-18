/*
Edits:
    Koosha Kimelman    4/17/21: created file
*/

package prisonerDilemma;

import mvc.Utilities;

public class RandomlyCooperate extends Strategy {
    public boolean cooperate() {
        int rand = Utilities.rng.nextInt(2); // 0 or 1
        return rand != 0; // 0 means cheat, 1 means cooperate
    }
}
