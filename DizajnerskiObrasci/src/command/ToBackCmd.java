package command;

import javax.swing.JOptionPane;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {

	DrawingModel drawingModel;
	Shape shape;

	public ToBackCmd(DrawingModel drawingModel, Shape shape) {
		this.drawingModel = drawingModel;
		this.shape = shape;
	}

	@Override
	public void execute() {
		Shape shapeToFront = null;
		int index = drawingModel.getShapes().indexOf(shape);
		if (index > 0) {
			/*
			 * drawingModel.remove(shape); 
			 * drawingModel.getShapes().add(0, shape);
			 */
		
			shapeToFront = drawingModel.getShapes().get(index - 1);
			drawingModel.getShapes().set(index, shapeToFront);
			drawingModel.getShapes().set(index - 1, shape);
		}

	}

	@Override
	public void unexecute() {
		Shape shapeToBack = null;
		int index = drawingModel.getShapes().indexOf(shape);
		if (index < drawingModel.getShapes().size() - 1) {
			/*
			 * drawingModel.remove(shape); 
			 * drawingModel.getShapes().add(shape);
			 */
			
			shapeToBack = drawingModel.get(index + 1);
			drawingModel.getShapes().set(index + 1, shape);
			drawingModel.getShapes().set(index, shapeToBack);
		}

	}
	
	public String toString() {
		if (shape instanceof Point) {
			return("To back " + shape.toString());
		} else if (shape instanceof Line) {
			return("To back " + shape.toString());
		} else if (shape instanceof Donut) {
			return("To back " + shape.toString());
		} else if (shape instanceof Circle) {
			return("To back " + shape.toString());
		} else if (shape instanceof Rectangle) {
			return("To back " + shape.toString());
		} else if (shape instanceof HexagonAdapter) {
			return("To back " + shape.toString());
		}
		return "To back " + shape.toString();
	}

}
