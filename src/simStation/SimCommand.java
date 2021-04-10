/*
Edits:
    Christina Ng    4/10/21: created file
*/

package simStation;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class SimCommand extends Command {
    String type;

    public SimCommand(Model model, String type) {
        super(model);
        this.type = type;
    }

    public void execute() {
        Simulation s = (Simulation)model;
        switch(type) {
            case "Start": {
                s.start();
                break;
            }
            case "Suspend": {
                s.suspend();
                break;
            }
            case "Resume": {
                s.resume();
                break;
            }
            case "Stop": {
                s.stop();
                break;
            }
            case "Stats": {
                String[] stats = s.stats();
                Utilities.inform(stats);
                break;
            }
        }
    }
}
