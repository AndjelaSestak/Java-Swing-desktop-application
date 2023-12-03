package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;

import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Point startPoint;
	private Shape selectedShape;

	private int selectedOption;
	private int i;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}

	/*----------------------------------METODA ZA ISCRTAVANJE-------------------------------*/
	public void mouseClicked(MouseEvent e) {
		Shape newShape;
		Point clickPoint = new Point(e.getX(), e.getY());

		if (frame.getTglbtnPoint().isSelected()) {
			DlgPoint dlgPoint = new DlgPoint();
			dlgPoint.getTxtXCoordinate().setText(Integer.toString(e.getX()));
			dlgPoint.getTxtXCoordinate().setEditable(false);
			dlgPoint.getTxtYCoordinate().setText(Integer.toString(e.getY()));
			dlgPoint.getTxtYCoordinate().setEditable(false);
			dlgPoint.getBtnBorderColor().setBackground(frame.getBtnBorder().getBackground());
			dlgPoint.setVisible(true);
			Color pointBorder = dlgPoint.getBtnBorderColor().getBackground();
			if (dlgPoint.isPOk) {
				newShape = new Point(e.getX(), e.getY(), pointBorder);
				model.add(newShape);
				;
			}
			startPoint = null;

		}

		if (frame.getTglbtnLine().isSelected()) {

			if (startPoint == null) {
				startPoint = clickPoint;
			}

			else {
				DlgLine dlgLine = new DlgLine();
				dlgLine.getTxtCoordinateX1().setText(Integer.toString(startPoint.getX()));
				dlgLine.getTxtCoordinateY1().setText(Integer.toString(startPoint.getY()));
				dlgLine.getTxtCoordinateX2().setText(Integer.toString(e.getX()));
				dlgLine.getTxtCoordinateY2().setText(Integer.toString(e.getY()));
				dlgLine.getTxtCoordinateX1().setEditable(false);
				dlgLine.getTxtCoordinateY1().setEditable(false);
				dlgLine.getTxtCoordinateX2().setEditable(false);
				dlgLine.getTxtCoordinateY2().setEditable(false);
				dlgLine.getBtnLineColor().setBackground(frame.getBtnBorder().getBackground());
				dlgLine.setVisible(true);
				Color lineColor = dlgLine.getBtnLineColor().getBackground();
				if (dlgLine.isLOk) {
					newShape = new Line(startPoint, new Point(e.getX(), e.getY()), lineColor);
					model.add(newShape);
				}
				startPoint = null;
			}
		}

		if (frame.getTglbtnCircle().isSelected()) {
			DlgCircle dlgCircle = new DlgCircle();
			dlgCircle.getTxtCoordinateX().setText(Integer.toString(e.getX()));
			dlgCircle.getTxtCoordinateY().setText(Integer.toString(e.getY()));
			dlgCircle.getTxtCoordinateX().setEditable(false);
			dlgCircle.getTxtCoordinateY().setEditable(false);
			dlgCircle.getBtnBorder().setBackground(frame.getBtnBorder().getBackground());
			dlgCircle.getBtnInner().setBackground(frame.getBtnInner().getBackground());
			dlgCircle.setVisible(true);

			if (dlgCircle.isCOk()) {
				newShape = new Circle(new Point(e.getX(), e.getY()),
						(Integer.parseInt(dlgCircle.getTxtRadius().getText())),
						dlgCircle.getBtnBorder().getBackground(), dlgCircle.getBtnInner().getBackground());
				model.add(newShape);
				startPoint = null;
			}
		}

		if (frame.getTglbtnRect().isSelected()) {
			DlgRectangle dlgRect = new DlgRectangle();
			dlgRect.getTxtCoordinateX().setText(Integer.toString(e.getX()));
			dlgRect.getTxtCoordinateX().setEditable(false);
			dlgRect.getTxtCoordinateY().setText(Integer.toString(e.getY()));
			dlgRect.getTxtCoordinateY().setEditable(false);
			dlgRect.getBtnBorderColor().setBackground(frame.getBtnBorder().getBackground());
			dlgRect.getBtnInnerColor().setBackground(frame.getBtnInner().getBackground());
			dlgRect.setVisible(true);

			if (dlgRect.isROk()) {
				int height = Integer.parseInt(dlgRect.getTxtHeight().getText());
				int width = Integer.parseInt(dlgRect.getTxtWidth().getText());
				Color innerColor = dlgRect.getBtnInnerColor().getBackground();
				Color borderColor = dlgRect.getBtnBorderColor().getBackground();
				newShape = new Rectangle(new Point(e.getX(), e.getY()), width, height, borderColor, innerColor);
				model.add(newShape);
				startPoint = null;
			}

		}

		if (frame.getTglbtnDonut().isSelected()) {
			DlgDonut dlgDonut = new DlgDonut();
			dlgDonut.getTxtCoordinateX().setText(Integer.toString(e.getX()));
			dlgDonut.getTxtCoordinateX().setEditable(false);
			dlgDonut.getTxtCoordinateY().setText(Integer.toString(e.getY()));
			dlgDonut.getTxtCoordinateY().setEditable(false);
			dlgDonut.getBtnDonutBorderColor().setBackground(frame.getBtnBorder().getBackground());
			dlgDonut.getBtnDonutInnerColor().setBackground(frame.getBtnInner().getBackground());
			dlgDonut.setVisible(true);

			if (dlgDonut.isDOk()) {
				int radius = (Integer.parseInt(dlgDonut.getTxtRadius().getText()));
				int innerRadius = (Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));

				newShape = new Donut(new Point(e.getX(), e.getY()), radius, innerRadius,
						dlgDonut.getBtnDonutBorderColor().getBackground(),
						dlgDonut.getBtnDonutInnerColor().getBackground());
				model.add(newShape);
				startPoint = null;
			}
		}

		if (frame.getTglbtnSelect().isSelected()) {
			Point point = new Point(e.getX(), e.getY());
			selectShape(point.getX(), point.getY());
		}

		frame.repaint();
	}

	/*----------------------------------METODA ZA MODIFIKACIJU-------------------------------*/
	public void modify() {

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
					frame.repaint();

				}
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
					frame.repaint();
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
					frame.repaint();
				}
			}
			if (selectedShape instanceof Circle && (selectedShape instanceof Donut == false)) {
				Circle originalCircle = (Circle) selectedShape;
				DlgCircle dialog = new DlgCircle();
				dialog.setCircle(originalCircle);
				dialog.setVisible(true);
				dialog.setModal(true);

				if (dialog.isCOk()) {

					originalCircle.getCenter().moveTo(Integer.parseInt(dialog.getTxtCoordinateX().getText().trim()),
							Integer.parseInt(dialog.getTxtCoordinateY().getText().trim()));
					try {
						originalCircle.setRadius(Integer.parseInt(dialog.getTxtRadius().getText().trim()));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Error");
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error");
					}
					originalCircle.setColor(dialog.getBtnBorder().getBackground());
					originalCircle.setInnerColor(dialog.getBtnInner().getBackground());
					setShape(index, originalCircle);
					frame.repaint();

				}

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
					frame.repaint();
				}
			}
		}

		else {
			JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error",
					JOptionPane.ERROR_MESSAGE);
			frame.getTglbtnSelect().setSelected(true);
		}
		setSelectedShape(null);
		frame.getTglbtnSelect().setSelected(false);

	}

	/*----------------------------------METODA ZA BRISANJE-------------------------------*/
	public void delete() {

		if (selectedShape != null) {
			selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this shape?",
					"Warning message", JOptionPane.YES_NO_OPTION);

			if (selectedOption == JOptionPane.YES_OPTION) {
				model.getShapes().remove(selectedShape);
				// setSelectedShape(null);
				selectedShape = null;
			}

			else {
				selectedShape.setSelected(false);
				selectedShape = null;
				// repaint();
			}

		}

		else if (counter() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");

		}

		else {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}

		frame.repaint();

	}

	public void selectShape(int x, int y) {
		if (counter() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty", "Error", JOptionPane.ERROR_MESSAGE);
		}
		selectedShape = null;
		Iterator<Shape> iterator = model.getShapes().iterator();

		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			shape.setSelected(false);
			if (shape.contains(x, y))
				selectedShape = shape;

		}

		if (selectedShape != null)
			selectedShape.setSelected(true);

		frame.repaint();

	}

	public int counter() {
		int i;
		for (i = 0; i < model.getShapes().size(); i++)
			;

		return i;
	}

	public int getIndex() {
		for (i = model.getShapes().size() - 1; i >= 0; i--) {
			if (model.getShapes().get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}

	public void setShape(int index, Shape shape) {
		model.getShapes().set(index, shape);
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

}
