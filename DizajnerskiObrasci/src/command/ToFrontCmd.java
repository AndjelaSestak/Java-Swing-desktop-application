package command;

import javax.swing.JOptionPane;

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

}
