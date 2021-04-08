/*
Edits:
    Christina Ng    4/7/21: created file
*/

package mvc;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class View extends JPanel implements PropertyChangeListener {
    protected Model model;

    public View(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setModel(Model model) {
        this.model.removePropertyChangeListener(this);
        this.model = model;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        //repaint();
        propertyChange(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}