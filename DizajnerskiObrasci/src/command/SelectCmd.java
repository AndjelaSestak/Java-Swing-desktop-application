package command;

import java.util.ArrayList;

import geometry.Shape;

public class SelectCmd implements Command{

	private ArrayList<Shape> selectedShapes;
	private Shape shape;
	
	public SelectCmd(ArrayList<Shape> selectedShapes,Shape shape)
	{
		this.selectedShapes = selectedShapes;
		this.shape=shape;
	}
	@Override
	public void execute() {
		selectedShapes.add(shape);
		shape.setSelected(true);
		
	}

	@Override
	public void unexecute() {
		selectedShapes.remove(shape);
		shape.setSelected(true);
		
	}

}
