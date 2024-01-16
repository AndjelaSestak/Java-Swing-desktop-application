package strategy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import mvc.DrawingController;

public class TextFileSaveLoad implements SaveLoad,Serializable{

	//private ArrayList<String> shapes;
	private String [] lines;
	private DrawingController controller;
	private Logger logger;

	/*public TextFileSaveLoad(ArrayList<String> shapes, Logger logger) {
		this.shapes = shapes;
		this.logger=logger;
	}*/

	public TextFileSaveLoad(String[] lines, Logger logger) {
		this.lines = lines;
		this.logger=logger;
	}
	public TextFileSaveLoad(DrawingController controller, Logger logger) {
		this.controller = controller;
		this.logger=logger;
	}

	@Override
	public void saveData(String filePath) {
		// TODO Auto-generated method stub
		try {
			PrintWriter printWriter = new PrintWriter(filePath + ".txt");
			/*for(String line: shapes) {
				printWriter.println(line);
			}*/
			for(int i=0;i<lines.length;i++)
			{
				printWriter.println(lines[i]);
			}
			printWriter.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadData(String filePath) {
		// TODO Auto-generated method stub
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = br.readLine()) != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator()); 
		    }
		    String s = sb.toString();
		    controller.setLoadSplit(s.split("\n"));

		    //logger.getTextArea().setText(s);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
