/*
Edits:
    Christina Ng    4/14/21: created file
*/

package simStation;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class StatsCommand extends Command {

    public StatsCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Simulation sim = (Simulation)model;
        // stats
        String[] stats = sim.stats();
        Utilities.inform(stats);
    }
}