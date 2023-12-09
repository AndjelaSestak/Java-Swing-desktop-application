package command;

import java.util.ArrayList;

import geometry.Shape;

public class DeselectCmd implements Command {

	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private Shape shape;

	public DeselectCmd(ArrayList<Shape> selectedshapes, Shape shape) {

		this.selectedShapes = selectedshapes;
		this.shape = shape;

	}

	@Override
	public void execute() {
		selectedShapes.remove(shape);
		shape.setSelected(false);

	}

	@Override
	public void unexecute() {
		selectedShapes.add(shape);
		shape.setSelected(true);

	}

}
