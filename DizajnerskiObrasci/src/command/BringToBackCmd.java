package command;

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
		drawingModel.add(shape);
	}

}
