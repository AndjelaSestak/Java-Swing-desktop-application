package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {

	private DrawingModel model;
	private Shape shape;
	private ArrayList<Shape>selectedShapes;
	private Stack<Integer> deleteInfo;

	public RemoveShapeCmd(DrawingModel model, Shape shape, ArrayList<Shape>selectedShapes, Stack<Integer> deleteInfo) {
		this.model = model;
		this.shape = shape;
		this.selectedShapes=selectedShapes;
		this.deleteInfo=deleteInfo;
	}

	@Override
	public void execute() {
		
		model.remove(shape);

	}

	@Override
	public void unexecute() {
		System.out.println("u unexecute metodi velicina deleteInfo je "+deleteInfo.size());
		/*for (int i=deleteInfo.size()-1;i>=0;i--)
		{*/
			model.getShapes().add(deleteInfo.pop(), shape);
			selectedShapes.add(shape);
			System.out.println("Velicina od shapes je"+model.getShapes().size());
			System.out.println("u for petlji velicina deleteInfo je "+deleteInfo.size());
			
		/*model.add(shape);
		selectedShapes.add(shape);*/
		//}
	}

}
