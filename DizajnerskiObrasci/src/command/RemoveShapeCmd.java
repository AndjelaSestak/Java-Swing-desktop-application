package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {

	private DrawingModel model;
	private Shape shape;
	private ArrayList<Shape>selectedShapes;

	public RemoveShapeCmd(DrawingModel model, Shape shape, ArrayList<Shape>selectedShapes) {
		this.model = model;
		this.shape = shape;
		this.selectedShapes=selectedShapes;
	}

	@Override
	public void execute() {
		model.remove(shape);

	}

	@Override
	public void unexecute() {
		model.add(shape);
		selectedShapes.add(shape);
	}

}
