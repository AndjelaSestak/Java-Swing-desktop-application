package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {

	private DrawingModel drawingModel;
	private Shape shape;
	private int originalIndex;

	public BringToFrontCmd(DrawingModel drawingModel, Shape shape) {
		this.drawingModel = drawingModel;
		this.shape = shape;
	}

	public BringToFrontCmd(DrawingModel drawingModel, Shape shape, int originalIndex) {
		this.drawingModel = drawingModel;
		this.shape = shape;
		this.originalIndex = originalIndex;
	}

	@Override
	public void execute() {
		drawingModel.getShapes().remove(shape);
		drawingModel.getShapes().add(shape);
	}

	@Override
	public void unexecute() {
		drawingModel.getShapes().remove(shape);
		drawingModel.getShapes().add(this.originalIndex, shape);

	}
}
