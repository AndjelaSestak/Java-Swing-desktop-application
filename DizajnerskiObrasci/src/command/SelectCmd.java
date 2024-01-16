package command;

import java.util.ArrayList;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
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
		shape.setSelected(false);
	}
	
	public String toString() {
		if (shape instanceof Point) {
			return("Selected point " + shape.toString());
		} else if (shape instanceof Line) {
			return("Selected line " + shape.toString());
		} else if (shape instanceof Donut) {
			return("Selected donut " + shape.toString());
		} else if (shape instanceof Circle) {
			return("Selected circle " + shape.toString());
		} else if (shape instanceof Rectangle) {
			return("Selected rectangle " + shape.toString());
		} else if (shape instanceof HexagonAdapter) {
			return("Selected hexagon " + shape.toString());
		}
		return "Selected " + shape.toString();
	}

}
