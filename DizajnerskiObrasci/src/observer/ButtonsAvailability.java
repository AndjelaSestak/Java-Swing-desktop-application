package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ButtonsAvailability {
	
	private boolean selectButton ;
	private boolean deleteButton ;
	private boolean modifyButton ;
	private boolean undoButton ;
	private boolean redoButton ;
	private boolean bringToFrontButton ;
	private boolean bringToBackButton ;
	private boolean toFrontButton ;
	private boolean toBackButton ;

	private PropertyChangeSupport propertyChangeSupport;

	public ButtonsAvailability() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void setSelectButton(boolean selectButton) {
		this.propertyChangeSupport.firePropertyChange("buttonSelect", this.selectButton, selectButton);
		this.selectButton=selectButton;
	}

	public void setDeleteButton(boolean deleteButton) {
		this.propertyChangeSupport.firePropertyChange("buttonDelete", this.deleteButton, deleteButton);
		this.deleteButton = deleteButton;
	}

	public void setModifyButton(boolean modifyButton) {
		this.propertyChangeSupport.firePropertyChange("buttonModify", this.modifyButton, modifyButton);
		this.modifyButton = modifyButton;
	}

	public void setUndoButton(boolean undoButton) {
		this.propertyChangeSupport.firePropertyChange("buttonUndo", this.undoButton, undoButton);
		this.undoButton = undoButton;
	}

	public void setRedoButton(boolean redoButton) {
		this.propertyChangeSupport.firePropertyChange("buttonRedo", this.redoButton, redoButton);
		this.redoButton = redoButton;
	}

	public void setBringToFrontButton(boolean bringToFrontButton) {
		this.propertyChangeSupport.firePropertyChange("buttonBringToFront", this.bringToFrontButton, bringToFrontButton);
		this.bringToFrontButton = bringToFrontButton;
	}

	public void setBringToBackButton(boolean bringToBackButton) {
		this.propertyChangeSupport.firePropertyChange("buttonBringToBack", this.bringToBackButton, bringToBackButton);
		this.bringToBackButton = bringToBackButton;
	}

	public void setToFrontButton(boolean toFrontButton) {
		this.propertyChangeSupport.firePropertyChange("buttonToFront", this.toFrontButton, toFrontButton);
		this.toFrontButton = toFrontButton;
	}

	public void setToBackButton(boolean toBackButton) {
		this.propertyChangeSupport.firePropertyChange("buttonToBack", this.toBackButton, toBackButton);
		this.toBackButton = toBackButton;
	}
	
	public void addListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
}
