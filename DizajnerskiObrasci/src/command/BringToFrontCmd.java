package command;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
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
	
	public String toString() {
		if (shape instanceof Point) {
			return("Bring to front point " + shape.toString());
		} else if (shape instanceof Line) {
			return("Bring to front line " + shape.toString());
		} else if (shape instanceof Donut) {
			return("Bring to front donut " + shape.toString());
		} else if (shape instanceof Circle) {
			return("Bring to front circle " + shape.toString());
		} else if (shape instanceof Rectangle) {
			return("Bring to front rectangle " + shape.toString());
		} else if (shape instanceof HexagonAdapter) {
			return("Bring to front hexagon " + shape.toString());
		}
		return "Bring to front " + shape.toString();
	}
}
