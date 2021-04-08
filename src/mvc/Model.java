/*
Edits:
    Christina Ng    4/7/21: created file
*/

package mvc;

public abstract class Model extends Bean {
    Boolean unsavedChanges = false;
    String fileName = null;

    public void changed() {
        unsavedChanges = true;
        firePropertyChange(fileName, false, unsavedChanges);
    }

    public boolean getUnsavedChanges() { return unsavedChanges; }
    public void setUnsavedChanges(boolean uc) { unsavedChanges = uc; }
    public String getFileName() { return fileName; }
    public void setFileName(String fName) { fileName = fName; }
}
