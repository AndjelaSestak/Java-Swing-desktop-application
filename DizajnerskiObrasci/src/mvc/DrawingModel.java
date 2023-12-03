package mvc;

import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;

public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>(); 
			
	public void add (Shape newShape) {
			   shapes.add(newShape);
   }
	
	public void remove(Shape p) {
		shapes.remove(p);
	}
	
	public Shape get(int index) {
		return shapes.get(index);
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}

}
