package command;

import java.util.ArrayList;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
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
	
	public String toString() {
		if (shape instanceof Point) {
			return("Deselected point " + shape.toString());
		} else if (shape instanceof Line) {
			return("Deselected line " + shape.toString());
		} else if (shape instanceof Donut) {
			return("Deselected donut " + shape.toString());
		} else if (shape instanceof Circle) {
			return("Deselected circle " + shape.toString());
		} else if (shape instanceof Rectangle) {
			return("Deselected rectangle " + shape.toString());
		} else if (shape instanceof HexagonAdapter) {
			return("Deselected hexagon " + shape.toString());
		}
		return "Deselected " + shape.toString();

	}

}
