/*
Edits:
    Koosha Kimelman    4/17/21: created file
    Koosha Kimelman    4/18/21: added implements Serializable
*/

package prisonerDilemma;

import java.io.Serializable;

public abstract class Strategy implements Serializable {
    public Prisoner myPrisoner;

    public abstract boolean cooperate();
}
