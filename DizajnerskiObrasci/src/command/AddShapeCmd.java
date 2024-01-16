package command;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingModel;
import strategy.Logger;

public class AddShapeCmd implements Command {

	private DrawingModel model;
	private Shape shape;

	public AddShapeCmd(DrawingModel model, Shape shape) {

		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		this.model.add(shape);

	}

	@Override
	public void unexecute() {
		this.model.remove(shape);
	}
	
	public String toString() {
		if (shape instanceof Point) {
			return("Added point " + shape.toString());
		} else if (shape instanceof Line) {
			return("Added line " + shape.toString());
		} else if (shape instanceof Donut) {
			return("Added donut " + shape.toString());
		} else if (shape instanceof Circle) {
			return("Added circle " + shape.toString());
		} else if (shape instanceof Rectangle) {
			return("Added rectangle " + shape.toString());
		} else if (shape instanceof HexagonAdapter) {
			return("Added hexagon " + shape.toString());
		}
		return "Added " + shape.toString();
	}

}
