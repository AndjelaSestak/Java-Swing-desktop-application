package drawing;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class PnlDrawing extends JPanel {

	// LISTA OBLIKA
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Shape selectedShape;
	private int selectedOption;
	private int i;

	/**
	 * Create the panel.
	 */
	public PnlDrawing() {

	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public void setShape(int index, Shape shape) {
		shapes.set(index, shape);
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}

	// DODAVANJE U LISTU
	public void addToShapes(Shape shape) {
		shapes.add(shape);
		repaint();
	}

	// REDEFINISANJE METODE PAINT
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext())
			it.next().draw(g);
	}

	// METODA ZA SELEKTOVANJE
	public void selectShape(int x, int y) {
		if (counter() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty", "Error", JOptionPane.ERROR_MESSAGE);
		}
		selectedShape = null;
		Iterator<Shape> iterator = shapes.iterator();

		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			shape.setSelected(false);
			if (shape.contains(x, y))
				selectedShape = shape;

		}

		if (selectedShape != null)
			selectedShape.setSelected(true);

		repaint();

	}

	public int counter() {
		int i;
		for (i = 0; i < shapes.size(); i++)
			;

		return i;
	}

	// METODA ZA BRISANJE
	public void delete() {

		if (selectedShape != null) {
			selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this shape?",
					"Warning message", JOptionPane.YES_NO_OPTION);

			if (selectedOption == JOptionPane.YES_OPTION) {
				getShapes().remove(selectedShape);
				setSelectedShape(null);
			}

			else {
				selectedShape.setSelected(false);
				setSelectedShape(null);
				repaint();
			}

		}

		else if (counter() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");

		}

		else {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}

		repaint();

	}

	// METODA ZA IZMENU OBLIKA

	public void modify() {

		Shape selectedShape = getSelectedShape();

		int index = getIndex();
		if (selectedShape != null) {

			if (selectedShape instanceof Point) {

				DlgPoint dlgP = new DlgPoint();
				Point originalPoint = (Point) selectedShape;
				dlgP.getTxtXCoordinate().setText(Integer.toString(originalPoint.getX()));
				dlgP.getTxtYCoordinate().setText(Integer.toString(originalPoint.getY()));
				dlgP.getBtnBorderColor().setBackground(originalPoint.getColor());
				dlgP.setVisible(true);

				if (dlgP.isPOk()) {
					int XCoordinate = (Integer.parseInt(dlgP.getTxtXCoordinate().getText()));
					int YCoordinate = (Integer.parseInt(dlgP.getTxtYCoordinate().getText()));
					Point p = new Point(XCoordinate, YCoordinate, dlgP.getBtnBorderColor().getBackground());

					originalPoint.setX(p.getX());
					originalPoint.setY(p.getY());
					originalPoint.setColor(dlgP.getBtnBorderColor().getBackground());

					setShape(index, originalPoint);
					repaint();

				}

				/*
				 * else { selectedShape.setSelected(false); repaint(); }
				 */

			}

			if (selectedShape instanceof Line) {

				DlgLine dlgL = new DlgLine();
				Line originalLine = (Line) selectedShape;
				dlgL.getTxtCoordinateX1().setText(Integer.toString(originalLine.getStartPoint().getX()));
				dlgL.getTxtCoordinateY1().setText(Integer.toString(originalLine.getStartPoint().getY()));
				dlgL.getTxtCoordinateX2().setText(Integer.toString(originalLine.getEndPoint().getX()));
				dlgL.getTxtCoordinateY2().setText(Integer.toString(originalLine.getEndPoint().getY()));
				dlgL.getBtnLineColor().setBackground(originalLine.getColor());
				dlgL.setVisible(true);

				if (dlgL.isLOk()) {

					Point startPoint1 = new Point(Integer.parseInt(dlgL.getTxtCoordinateX1().getText()),
							Integer.parseInt(dlgL.getTxtCoordinateY1().getText()));
					Point endPoint1 = new Point(Integer.parseInt(dlgL.getTxtCoordinateX2().getText()),
							Integer.parseInt(dlgL.getTxtCoordinateY2().getText()));

					originalLine.setStartPoint(startPoint1);
					originalLine.setEndPoint(endPoint1);
					originalLine.setColor(dlgL.getBtnLineColor().getBackground());

					setShape(index, originalLine);
					repaint();
				}
			}

			if (selectedShape instanceof Rectangle) {

				DlgRectangle dlgRect = new DlgRectangle();
				Rectangle originalRect = (Rectangle) selectedShape;
				dlgRect.setRectangle(originalRect);
				dlgRect.setVisible(true);

				if (dlgRect.isROk()) {
					Point upperLeft = new Point(Integer.parseInt(dlgRect.getTxtCoordinateX().getText()),
							Integer.parseInt(dlgRect.getTxtCoordinateY().getText()));
					originalRect.setUpperLeftPoint(upperLeft);
					originalRect.setWidth(Integer.parseInt(dlgRect.getTxtWidth().getText()));
					originalRect.setHeight(Integer.parseInt(dlgRect.getTxtHeight().getText()));
					originalRect.setInnerColor(dlgRect.getBtnInnerColor().getBackground());
					originalRect.setColor(dlgRect.getBtnBorderColor().getBackground());

					setShape(index, originalRect);
					repaint();
				}
			}

			if (selectedShape instanceof Circle && (selectedShape instanceof Donut == false)) {
				Circle circle = (Circle) selectedShape;
				DlgCircle dialog = new DlgCircle();

				dialog.getTxtCoordinateX().setText("" + Integer.toString(circle.getCenter().getX()));
				dialog.getTxtCoordinateY().setText("" + Integer.toString(circle.getCenter().getY()));
				dialog.getTxtRadius().setText("" + Integer.toString(circle.getRadius()));
				dialog.getBtnInner().setBackground(circle.getInnerColor());
				dialog.getBtnBorder().setBackground(circle.getColor());

				dialog.setVisible(true);
				dialog.setModal(true);

				if (dialog.isCOk()) {
					shapes.remove(selectedShape);
					shapes.add(dialog.getCircle());
					repaint();
				}

				/*
				 * Circle c = (Circle) selectedShape; DlgCircle dlgCircle = new DlgCircle();
				 * dlgCircle.getTxtCoordinateX().setText(Integer.toString(c.getCenter().getX()))
				 * ;
				 * dlgCircle.getTxtCoordinateY().setText(Integer.toString(c.getCenter().getY()))
				 * ; dlgCircle.getTxtRadius().setText(Integer.toString(c.getRadius()));
				 * dlgCircle.getBtnBorder().setBackground(c.getColor());
				 * dlgCircle.getBtnInner().setBackground(c.getInnerColor());
				 * dlgCircle.setVisible(true); if (dlgCircle.isCOk()) { c.setCenter(new
				 * Point(Integer.parseInt(dlgCircle.getTxtCoordinateX().getText()),
				 * Integer.parseInt(dlgCircle.getTxtCoordinateY().getText())));
				 * c.setRadius(Integer.parseInt(dlgCircle.getTxtRadius().getText()));
				 * c.setColor(dlgCircle.getBtnBorderColor().getBackground());
				 * c.setInnerColor(dlgCircle.getBtnInnerColor().getBackground());
				 * setShape(index, c); repaint();
				 */

			}

			if (selectedShape instanceof Donut) {
				DlgDonut dlgDonut = new DlgDonut();
				Donut originalDonut = (Donut) selectedShape;
				dlgDonut.setDonut(originalDonut);
				dlgDonut.setVisible(true);

				if (dlgDonut.isDOk()) {
					Point center1 = new Point(Integer.parseInt(dlgDonut.getTxtCoordinateX().getText()),
							Integer.parseInt(dlgDonut.getTxtCoordinateY().getText()));
					originalDonut.setCenter(center1);
					try {
						originalDonut.setRadius(Integer.parseInt(dlgDonut.getTxtRadius().getText()));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					originalDonut.setInnerRadius(Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
					originalDonut.setColor(dlgDonut.getBtnDonutBorderColor().getBackground());
					originalDonut.setInnerColor(dlgDonut.getBtnDonutInnerColor().getBackground());

					setShape(index, originalDonut);
					repaint();
				}
			}
		}

	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public int getIndex() {
		for (i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}

}
