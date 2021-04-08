/*
Edits:
    Christina Ng    4/7/21: created file
*/

package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {

    protected View view;
    protected Model model;
    protected JPanel controlPanel;
    protected AppFactory factory;

    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory factory) {

        this.factory = factory;
        model = factory.makeModel();
        view = factory.makeView(model);
        controlPanel = new JPanel();

        setLayout(new GridLayout(1, 2));
        add(controlPanel);
        add(view);

        view.setBackground(Color.GRAY);
        controlPanel.setBackground(Color.PINK);

        frame  = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            // handle control actions here
            String cmmd = ae.getActionCommand();
            if (cmmd == "New") {
                if (Utilities.saveChanges(model)) {
                    model = factory.makeModel();
                    view.setModel(model);
                    frame.validate();
                }
            }
            else if (cmmd == "Save") {
                /*String fName = Utilities.getFileName(null, false);
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                os.writeObject(model);
                os.close();*/
                Utilities.save(model,false);
            }
            else if (cmmd == "SaveAs") {
                Utilities.save(model,true);
            }
            else if (cmmd == "Open") {
                /*String fName = Utilities.getFileName(null, true);
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                this.model = (Model)is.readObject();
                this.view.setModel(model);
                is.close();*/
                this.model = Utilities.open(model);
                this.view.setModel(model);
            }
            else if (cmmd == "Quit") {
                if (Utilities.saveChanges(model)) {
                    System.exit(1);
                }
            }
            else if (cmmd == "Help") {
                Utilities.inform(factory.getHelp());
            }
            else if (cmmd == "About") {
                Utilities.inform(factory.about());
            }
            else {
                Command c = factory.makeEditCommand(model, cmmd);
                c.execute();
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    public void display()	{ frame.setVisible(true); }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"Help", "About"}, this);
        result.add(helpMenu);
        return result;
    }
}