/*
Edits:
    Christina Ng    4/10/21: created file
*/

package simStation;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;

public class SimulationPanel extends AppPanel {
    private JButton START, SUSPEND, RESUME, STOP, STATS;

    public SimulationPanel(AppFactory factory) {
        super(factory);

        START = new JButton("Start");
        SUSPEND = new JButton("Suspend");
        RESUME = new JButton("Resume");
        STOP = new JButton("Stop");
        STATS = new JButton("Stats");

        START.addActionListener(this);
        SUSPEND.addActionListener(this);
        RESUME.addActionListener(this);
        STOP.addActionListener(this);
        STATS.addActionListener(this);

        controlPanel.add(START);
        controlPanel.add(SUSPEND);
        controlPanel.add(RESUME);
        controlPanel.add(STOP);
        controlPanel.add(STATS);

    }

    public static void main(String[] args) {
        AppFactory factory = new SimulationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
