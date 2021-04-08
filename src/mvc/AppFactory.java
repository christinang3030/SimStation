/*
Edits:
    Christina Ng    4/7/21: created file
*/

package mvc;

public interface AppFactory {

    Model makeModel();
    View makeView(Model model);
    String getTitle();
    String[] getHelp();
    String about();
    String[] getEditCommands();
    Command makeEditCommand(Model model, String name);

}

