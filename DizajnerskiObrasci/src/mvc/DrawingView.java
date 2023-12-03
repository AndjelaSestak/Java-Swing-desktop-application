package mvc;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel{
		// DrawingModel model;
		// izuzetak od pocetne mvc arhitekture, jer model pri pokretanju pravi problem 
		//ukoliko je null
		DrawingModel model = new DrawingModel();

		public void setModel(DrawingModel model) {
			this.model = model;
		}
		
		public void paint(Graphics g) {
			Iterator<Shape> it = model.getShapes().iterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
			//pozivanje metode u svakoj milisekundi
			//repaint();
			//System.out.println(System.currentTimeMillis());
		}
}
