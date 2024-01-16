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

public class ToFrontCmd implements Command {

	private DrawingModel drawingModel;
	private Shape shape;

	public ToFrontCmd(DrawingModel drawingModel, Shape shape) {
		this.drawingModel = drawingModel;
		this.shape = shape;
	}

	@Override
	public void execute() {
		Shape shapeToBack = null;
		int index = drawingModel.getShapes().indexOf(shape);
		if (index < drawingModel.getShapes().size()-1) {
			/*drawingModel.remove(shape);
			drawingModel.getShapes().add(shape);*/
			
			shapeToBack = drawingModel.get(index + 1);
			drawingModel.getShapes().set(index + 1, shape);
			drawingModel.getShapes().set(index, shapeToBack);
		}

	}

	@Override
	public void unexecute() {
		Shape shapeToFront = null;
		int index = drawingModel.getShapes().indexOf(shape);
		if (index > 0) {
			/*drawingModel.remove(shape);
			drawingModel.getShapes().add(0, shape);*/
		
			shapeToFront = drawingModel.getShapes().get(index-1);
			drawingModel.getShapes().set(index, shapeToFront);
			drawingModel.getShapes().set(index-1, shape);
		}

	}
	
	public String toString() {
		if (shape instanceof Point) {
			return("To front " + shape.toString());
		} else if (shape instanceof Line) {
			return("To front " + shape.toString());
		} else if (shape instanceof Donut) {
			return("To front " + shape.toString());
		} else if (shape instanceof Circle) {
			return("To front " + shape.toString());
		} else if (shape instanceof Rectangle) {
			return("To front " + shape.toString());
		} else if (shape instanceof HexagonAdapter) {
			return("To front " + shape.toString());
		}
		return "To front " + shape.toString();
	}

}
