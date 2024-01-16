package strategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class ObjectFileSaveLoad implements SaveLoad,Serializable{
	
	private DrawingModel model;

	public ObjectFileSaveLoad(DrawingModel model) {
		//super();
		this.model = model;
	}


	@Override
	public void saveData(String filePath) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(model.getShapes());
			objectOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void loadData(String filePath) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			@SuppressWarnings("unchecked")
			ArrayList<Shape> modelObjects = (ArrayList<Shape>) objectInputStream.readObject();
			for(Shape shape : modelObjects) {
				model.add(shape);
			}
			objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
