package command;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command{

	private DrawingModel drawingModel;
	private Shape shape;
	private int index;
	private int originalIndex;
	
	public BringToBackCmd(DrawingModel drawingModel, Shape shape)
	{
		this.drawingModel = drawingModel;
		this.shape=shape;
	}
	
	public BringToBackCmd(DrawingModel drawingModel, Shape shape, int originalIndex) {
		this.drawingModel = drawingModel;
		this.shape = shape;
		this.originalIndex = originalIndex;
	}
	
	@Override
	public void execute() {
		int index = drawingModel.getShapes().indexOf(shape);
		drawingModel.getShapes().remove(shape);
		drawingModel.getShapes().add(0, shape);
		
	}

	@Override
	public void unexecute() {
		
		drawingModel.getShapes().remove(shape);
		drawingModel.getShapes().add(this.originalIndex, shape);
	}
	
	public String toString() {
		if (shape instanceof Point) {
			return("Bring to back point " + shape.toString());
		} else if (shape instanceof Line) {
			return("Bring to back line " + shape.toString());
		} else if (shape instanceof Donut) {
			return("Bring to back donut " + shape.toString());
		} else if (shape instanceof Circle) {
			return("Bring to back circle " + shape.toString());
		} else if (shape instanceof Rectangle) {
			return("Bring to back rectangle " + shape.toString());
		} else if (shape instanceof HexagonAdapter) {
			return("Bring to back hexagon " + shape.toString());
		}
		return "Bring to back " + shape.toString();
	}

}
