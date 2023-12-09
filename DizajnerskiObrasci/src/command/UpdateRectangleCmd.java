package command;

import javax.swing.JOptionPane;

import geometry.Rectangle;

public class UpdateRectangleCmd implements Command{

	private Rectangle rectangle;
	private Rectangle newState;
	private Rectangle temp = new Rectangle();

	public UpdateRectangleCmd(Rectangle rectangle, Rectangle newState) {
		this.rectangle = rectangle;
		this.newState = newState;
	}

	@Override
	public void execute() {
		temp.getUpperLeftPoint().setX(rectangle.getUpperLeftPoint().getX());
		temp.getUpperLeftPoint().setY(rectangle.getUpperLeftPoint().getY());
		temp.setHeight(rectangle.getHeight());
		temp.setWidth(rectangle.getWidth());
		temp.setColor(rectangle.getColor());
		temp.setInnerColor(rectangle.getInnerColor());
		System.out.println("Unutrasnja boja pre izvršavanja execute(): " + newState.getInnerColor());  // Dodajte ovu liniju
		
		rectangle.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		rectangle.setHeight(newState.getHeight());
		rectangle.setWidth(newState.getWidth());
		rectangle.setColor(newState.getColor());
		rectangle.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		rectangle.getUpperLeftPoint().setX(temp.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(temp.getUpperLeftPoint().getY());
		rectangle.setHeight(temp.getHeight());
		rectangle.setWidth(temp.getWidth());
		rectangle.setColor(temp.getColor());
		rectangle.setInnerColor(temp.getInnerColor());
	}
}
