package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class ButtonsUpdate implements PropertyChangeListener{

	private boolean selectButton;
	private boolean deleteButton;
	private boolean modifyButton;
	private boolean undoButton;
	private boolean redoButton;
	private boolean bringToFrontButton;
	private boolean bringToBackButton;
	private boolean toFrontButton;
	private boolean toBackButton;
	private DrawingFrame frame;
	
	public ButtonsUpdate(DrawingFrame frame){
		this.frame=frame;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		System.out.println("Property changed: " + evt.getPropertyName());
		if(evt.getPropertyName().equals("buttonSelect")) {
			
			this.selectButton=(boolean)evt.getNewValue();
			frame.getTglbtnSelect().setEnabled(selectButton);

		} 
		else if (evt.getPropertyName().equals("buttonDelete")) {
			
			this.deleteButton=(boolean)evt.getNewValue();
			this.frame.getTglbtnDelete().setEnabled(deleteButton);
		} 
		else if (evt.getPropertyName().equals("buttonModify")) {
			
			this.modifyButton=(boolean)evt.getNewValue();
			this.frame.getTglbtnModify().setEnabled(modifyButton);
		}
		else if(evt.getPropertyName().equals("buttonUndo"))
		{
			this.undoButton=(boolean)evt.getNewValue();
			this.frame.getBtnUndo().setEnabled(undoButton);
		}
		else if(evt.getPropertyName().equals("buttonRedo"))
		{
			this.redoButton=(boolean)evt.getNewValue();
			this.frame.getBtnRedo().setEnabled(redoButton);
		}
		else if(evt.getPropertyName().equals("buttonBringToFront"))
		{
			this.bringToFrontButton=(boolean)evt.getNewValue();
			this.frame.getBtnBringToFront().setEnabled(bringToFrontButton);
		}
		else if(evt.getPropertyName().equals("buttonBringToBack"))
		{
			this.bringToBackButton=(boolean)evt.getNewValue();
			this.frame.getBtnBringToBack().setEnabled(bringToBackButton);
		}
		else if(evt.getPropertyName().equals("buttonToFront"))
		{
			this.toFrontButton=(boolean)evt.getNewValue();
			this.frame.getBtnToFront().setEnabled(toFrontButton);
		}
		else if(evt.getPropertyName().equals("buttonToBack"))
		{
			this.toBackButton=(boolean)evt.getNewValue();
			this.frame.getBtnToBack().setEnabled(toBackButton);
		}
		
	}

}
