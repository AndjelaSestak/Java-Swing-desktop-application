package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
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
		//System.out.println("u unexecute metodi velicina deleteInfo je "+deleteInfo.size());
		/*for (int i=deleteInfo.size()-1;i>=0;i--)
		{*/
			model.getShapes().add(deleteInfo.pop(), shape);
			selectedShapes.add(shape);
			//System.out.println("Velicina od shapes je"+model.getShapes().size());
			//System.out.println("u for petlji velicina deleteInfo je "+deleteInfo.size());
			
		/*model.add(shape);
		selectedShapes.add(shape);*/
		//}
	}
	
	public String toString() {
		
		if (shape instanceof Point) {
			return("Deleted point " + shape.toString());
		} else if (shape instanceof Line) {
			return("Deleted line " + shape.toString());
		} else if (shape instanceof Donut) {
			return("Deleted donut " + shape.toString());
		} else if (shape instanceof Circle) {
			return("Deleted circle " + shape.toString());
		} else if (shape instanceof Rectangle) {
			return("Deleted rectangle " + shape.toString());
		} else if (shape instanceof HexagonAdapter) {
			return("Deleted hexagon " + shape.toString());
		}
		return "Deleted " + shape.toString();
	}

}
