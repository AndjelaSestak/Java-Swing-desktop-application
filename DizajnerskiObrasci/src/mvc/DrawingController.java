package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JOptionPane;

import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.DeselectCmd;
import command.RemoveShapeCmd;
import command.SelectCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
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
	private int originalIndex;
	private AddShapeCmd addShapeCmd;
	private RemoveShapeCmd removeShapeCmd;
	private UpdatePointCmd updatePointCmd;
	private UpdateLineCmd updateLineCmd;
	private UpdateRectangleCmd updateRectangleCmd;
	private UpdateCircleCmd updateCircleCmd;
	private UpdateDonutCmd updateDonutCmd;
	private ToFrontCmd toFrontCmd;
	private ToBackCmd toBackCmd;
	private BringToBackCmd bringToBackCmd;
	private BringToFrontCmd bringToFrontCmd;
	private ArrayList<Shape> selectedShapes = new ArrayList();
	private ArrayList<Command> commands = new ArrayList();
	// private Stack<Command> undoStack= new Stack<>();
	private Stack<Command> redoStack = new Stack<>();


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
				// model.add(newShape);
				addShapeCmd = new AddShapeCmd(model, newShape);
				addShapeCmd.execute();
				commands.add(addShapeCmd);
				redoStack.clear();
				frame.getBtnBorder().setBackground(dlgPoint.getBtnBorderColor().getBackground());
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
					// model.add(newShape);
					addShapeCmd = new AddShapeCmd(model, newShape);
					addShapeCmd.execute();
					commands.add(addShapeCmd);
					redoStack.clear();
					frame.getBtnBorder().setBackground(dlgLine.getBtnLineColor().getBackground());
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
				// model.add(newShape);
				addShapeCmd = new AddShapeCmd(model, newShape);
				addShapeCmd.execute();
				commands.add(addShapeCmd);
				redoStack.clear();
				frame.getBtnBorder().setBackground(dlgCircle.getBtnBorder().getBackground());
				frame.getBtnInner().setBackground(dlgCircle.getBtnInner().getBackground());
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
				addShapeCmd = new AddShapeCmd(model, newShape);
				addShapeCmd.execute();
				commands.add(addShapeCmd);
				redoStack.clear();
				frame.getBtnBorder().setBackground(dlgRect.getBtnBorderColor().getBackground());
				frame.getBtnInner().setBackground(dlgRect.getBtnInnerColor().getBackground());
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
				addShapeCmd = new AddShapeCmd(model, newShape);
				addShapeCmd.execute();
				commands.add(addShapeCmd);
				redoStack.clear();
				frame.getBtnBorder().setBackground(dlgDonut.getBtnDonutBorderColor().getBackground());
				frame.getBtnInner().setBackground(dlgDonut.getBtnDonutInnerColor().getBackground());
				startPoint = null;
			}
		}

		if (frame.getTglbtnSelect().isSelected()) {
			Point point = new Point(e.getX(), e.getY());
			selectShapes(point.getX(), point.getY());
		}
		// undoStack.push(addShapeCmd);
		// redoStack.clear();

		frame.repaint();
	}

	/*----------------------------------METODA ZA MODIFIKACIJU-------------------------------*/
	public void modify() {

		int index = getIndex();
		for (int i = 0; i < model.getShapes().size(); i++) {

			if (model.getShapes().get(i) instanceof Point) {

				if (model.getShapes().get(i).isSelected())
					;
				DlgPoint dlgP = new DlgPoint();
				Point originalPoint = (Point) model.getShapes().get(i);
				dlgP.getTxtXCoordinate().setText(Integer.toString(originalPoint.getX()));
				dlgP.getTxtYCoordinate().setText(Integer.toString(originalPoint.getY()));
				dlgP.getBtnBorderColor().setBackground(originalPoint.getColor());
				dlgP.setVisible(true);

				if (dlgP.isPOk()) {
					int XCoordinate = (Integer.parseInt(dlgP.getTxtXCoordinate().getText()));
					int YCoordinate = (Integer.parseInt(dlgP.getTxtYCoordinate().getText()));
					Point p = new Point(XCoordinate, YCoordinate, dlgP.getBtnBorderColor().getBackground());

					updatePointCmd = new UpdatePointCmd(originalPoint, p);
					updatePointCmd.execute();
					commands.add(updatePointCmd);
					frame.getBtnBorder().setBackground(dlgP.getBtnBorderColor().getBackground());
					frame.repaint();

				}
			}
			if (model.getShapes().get(i) instanceof Line) {

				if (model.getShapes().get(i).isSelected())
					;
				DlgLine dlgL = new DlgLine();
				Line originalLine = (Line) model.getShapes().get(i);
				dlgL.getTxtCoordinateX1().setText(Integer.toString(originalLine.getStartPoint().getX()));
				dlgL.getTxtCoordinateY1().setText(Integer.toString(originalLine.getStartPoint().getY()));
				dlgL.getTxtCoordinateX2().setText(Integer.toString(originalLine.getEndPoint().getX()));
				dlgL.getTxtCoordinateY2().setText(Integer.toString(originalLine.getEndPoint().getY()));
				dlgL.getBtnLineColor().setBackground(originalLine.getColor());
				dlgL.setVisible(true);

				if (dlgL.isLOk()) {

					int X1new = (Integer.parseInt(dlgL.getTxtCoordinateX1().getText()));
					int X2new = (Integer.parseInt(dlgL.getTxtCoordinateX2().getText()));
					int Y1new = (Integer.parseInt(dlgL.getTxtCoordinateY1().getText()));
					int Y2new = (Integer.parseInt(dlgL.getTxtCoordinateY2().getText()));
					Color newBorderColor = dlgL.getBtnLineColor().getBackground();
					Point newPoint1 = new Point(X1new, Y1new, false);
					Point newPoint2 = new Point(X2new, Y2new, false);
					/*
					 * Point startPoint1 = new
					 * Point(Integer.parseInt(dlgL.getTxtCoordinateX1().getText()),
					 * Integer.parseInt(dlgL.getTxtCoordinateY1().getText())); Point endPoint1 = new
					 * Point(Integer.parseInt(dlgL.getTxtCoordinateX2().getText()),
					 * Integer.parseInt(dlgL.getTxtCoordinateY2().getText()));
					 */

					Line l2 = new Line(newPoint1, newPoint2, newBorderColor);
					updateLineCmd = new UpdateLineCmd(originalLine, l2);
					updateLineCmd.execute();
					commands.add(updateLineCmd);
					frame.getBtnBorder().setBackground(dlgL.getBtnLineColor().getBackground());
					/*
					 * originalLine.setStartPoint(startPoint1); originalLine.setEndPoint(endPoint1);
					 * originalLine.setColor(dlgL.getBtnLineColor().getBackground());
					 */

					/* setShape(index, originalLine); */
					frame.repaint();
				}
			}
			if (model.getShapes().get(i) instanceof Rectangle) {

				if (model.getShapes().get(i).isSelected())
					;
				DlgRectangle dlgRect = new DlgRectangle();
				Rectangle originalRect = (Rectangle) model.getShapes().get(i);
				dlgRect.setRectangle(originalRect);
				dlgRect.getBtnBorderColor().setBackground(originalRect.getColor());
				dlgRect.getBtnInnerColor().setBackground(originalRect.getInnerColor());

				dlgRect.setVisible(true);

				if (dlgRect.isROk()) {
					int uX = (Integer.parseInt(dlgRect.getTxtCoordinateX().getText()));
					int uY = (Integer.parseInt(dlgRect.getTxtCoordinateY().getText()));
					int wid = (Integer.parseInt(dlgRect.getTxtWidth().getText()));
					int he = (Integer.parseInt(dlgRect.getTxtHeight().getText()));
					Color colorBorder = dlgRect.getBtnBorderColor().getBackground();
					Color colorInner = dlgRect.getBtnInnerColor().getBackground();

					Rectangle r2 = new Rectangle(new Point(uX, uY), wid, he, colorBorder, colorInner);

					updateRectangleCmd = new UpdateRectangleCmd(originalRect, r2);
					updateRectangleCmd.execute();
					commands.add(updateRectangleCmd);
					frame.getBtnBorder().setBackground(dlgRect.getBtnBorderColor().getBackground());
					frame.getBtnInner().setBackground(dlgRect.getBtnInnerColor().getBackground());

					/* setShape(index, originalRect); */
					frame.repaint();
				}
			}
			if (model.getShapes().get(i) instanceof Circle == true
					&& model.getShapes().get(i) instanceof Donut == false) {

				if (model.getShapes().get(i).isSelected())
					;
				Circle originalCircle = (Circle) model.getShapes().get(i);
				DlgCircle dialog = new DlgCircle();
				dialog.setCircle(originalCircle);
				dialog.setVisible(true);
				dialog.setModal(true);

				if (dialog.isCOk()) {

					int cX = (Integer.parseInt(dialog.getTxtCoordinateX().getText().trim()));
					int cY = (Integer.parseInt(dialog.getTxtCoordinateY().getText().trim()));
					int radius = (Integer.parseInt(dialog.getTxtRadius().getText().trim()));
					Color colorBorder = dialog.getBtnBorder().getBackground();
					Color colorInner = dialog.getBtnInner().getBackground();
					Circle c2 = new Circle(new Point(cX, cY), radius, colorBorder, colorInner);

					updateCircleCmd = new UpdateCircleCmd(originalCircle, c2);
					updateCircleCmd.execute();
					commands.add(updateCircleCmd);
					frame.getBtnBorder().setBackground(dialog.getBtnBorder().getBackground());
					frame.getBtnInner().setBackground(dialog.getBtnInner().getBackground());

					frame.repaint();

				}

			}

			if (model.getShapes().get(i) instanceof Donut) {

				if (model.getShapes().get(i).isSelected())
					;
				DlgDonut dlgDonut = new DlgDonut();
				Donut originalDonut = (Donut) model.getShapes().get(i);
				dlgDonut.setDonut(originalDonut);
				dlgDonut.setVisible(true);

				if (dlgDonut.isDOk()) {
					Point center1 = new Point(Integer.parseInt(dlgDonut.getTxtCoordinateX().getText()),
							Integer.parseInt(dlgDonut.getTxtCoordinateY().getText()));
					int innerRadius = (Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));
					int radius = (Integer.parseInt(dlgDonut.getTxtRadius().getText()));
					Color innerColor = dlgDonut.getBtnDonutInnerColor().getBackground();
					Color borderColor = dlgDonut.getBtnDonutBorderColor().getBackground();

					Donut d2 = new Donut(center1, radius, innerRadius, borderColor, innerColor);

					updateDonutCmd = new UpdateDonutCmd(originalDonut, d2);
					updateDonutCmd.execute();
					commands.add(updateDonutCmd);
					frame.getBtnBorder().setBackground(dlgDonut.getBtnDonutBorderColor().getBackground());
					frame.getBtnInner().setBackground(dlgDonut.getBtnDonutInnerColor().getBackground());

					frame.repaint();
				}
			}
		}

		if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error",
					JOptionPane.ERROR_MESSAGE);
			frame.getTglbtnSelect().setSelected(true);
		}
		// setSelectedShape(null);
		frame.getTglbtnSelect().setSelected(false);

	}

	/*----------------------------------METODA ZA BRISANJE-------------------------------*/
	public void delete() {

		if (counter() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");

		}
		if (selectedShapes.size() == 0) {
			JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
		if (selectedShapes.size() == 1) {
			for (int i = 0; i < model.getShapes().size(); i++) {
				if (model.getShapes().get(i).isSelected()) {
					int selectedOption = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete this shape?", "Warning message",
							JOptionPane.YES_NO_OPTION);

					if (selectedOption == JOptionPane.YES_OPTION) {
						removeShapeCmd = new RemoveShapeCmd(model, model.getShapes().get(i), selectedShapes);
						selectedShapes.remove(model.getShapes().get(i));
						removeShapeCmd.execute();
						commands.add(removeShapeCmd);
						frame.repaint();
					} else {
						DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, model.getShapes().get(i));
						deselectCmd.execute();
						commands.add(deselectCmd);
						frame.repaint();
					}
				}
			}
		} else {
			int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete these shapes?",
					"Warning message", JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				for (int i = selectedShapes.size() - 1; i >= 0; i--) {
					removeShapeCmd = new RemoveShapeCmd(model, selectedShapes.get(i), selectedShapes);
					removeShapeCmd.execute();
					commands.add(removeShapeCmd);
					selectedShapes.remove(i);
				}
				frame.repaint();
			} else {
				for (int i = 0; i < model.getShapes().size(); i++) {
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, model.getShapes().get(i));
					deselectCmd.execute();
					commands.add(deselectCmd);
				}

			}
			frame.repaint();
		}
	}

	/*----------------------------------METODA ZA SELEKCIJU VISE OBLIKA-------------------------------*/
	public void selectShapes(int x, int y) {
		selectedShape = null;
		boolean oneShape = true; // za selekciju jednog oblika ako se selektuje presek dva oblika

		if (counter() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty", "Error", JOptionPane.ERROR_MESSAGE);
		}
		for (int i = model.getShapes().size() - 1; i >= 0; i--) {

			if (model.getShapes().get(i).contains(x, y) && oneShape == true) {
				Shape shape = model.getShapes().get(i);
				oneShape = false;
				if (shape.isSelected() == false) {
					SelectCmd selectCmd = new SelectCmd(selectedShapes, shape);
					// commands.add(selectCmd);
					selectCmd.execute();
					commands.add(selectCmd);
				} else {
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, (Shape) model.getShapes().get(i));
					// commands.add(deselectCmd);
					deselectCmd.execute();
					commands.add(deselectCmd);
				}
			}
		}
	}

	/*----------------------------------TO FRONT I TO BACK-------------------------------*/
	public void toFront() {
		if (selectedShapes.size() > 1) {
			JOptionPane.showMessageDialog(null, "You can select only one shape.");
			return;
		} else if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			return;
		} else if (selectedShapes.size() == 0) {
			JOptionPane.showMessageDialog(null, "Select shape.");
			return;
		} else if (selectedShapes.size() == 1) {
			Shape shapeToMove = selectedShapes.get(selectedShapes.size() - 1);

			toFrontCmd = new ToFrontCmd(model, shapeToMove);
			commands.add(toFrontCmd);
			toFrontCmd.execute();
		}
		frame.repaint();
	}

	public void toBack() {
		if (selectedShapes.size() > 1) {
			JOptionPane.showMessageDialog(null, "You can select only one shape.");
			return;
		} else if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			return;
		} else if (selectedShapes.size() == 0) {
			JOptionPane.showMessageDialog(null, "Select shape.");
			return;
		} else if (selectedShapes.size() == 1) {
			Shape shapeToMove = selectedShapes.get(selectedShapes.size() - 1);

			toBackCmd = new ToBackCmd(model, shapeToMove);
			commands.add(toBackCmd);
			toBackCmd.execute();
		}
		frame.repaint();
	}

	public void bringToBack() {
		if (selectedShapes.size() > 1) {
			JOptionPane.showMessageDialog(null, "You can select only one shape.");
			return;
		} else if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			return;
		} else if (selectedShapes.size() == 0) {
			JOptionPane.showMessageDialog(null, "Select shape.");
			return;
		} else if (selectedShapes.size() == 1) {
			Shape shapeToMove = selectedShapes.get(selectedShapes.size() - 1);

			originalIndex = model.getShapes().indexOf(shapeToMove);
			bringToBackCmd = new BringToBackCmd(model, shapeToMove, originalIndex);
			commands.add(toBackCmd);
			bringToBackCmd.execute();
		}
		frame.repaint();
	}

	public void bringToFront() {
		if (selectedShapes.size() > 1) {
			JOptionPane.showMessageDialog(null, "You can select only one shape.");
			return;
		} else if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "List of shapes is empty!");
			return;
		} else if (selectedShapes.size() == 0) {
			JOptionPane.showMessageDialog(null, "Select shape.");
			return;
		} else if (selectedShapes.size() == 1) {
			Shape shapeToMove = selectedShapes.get(selectedShapes.size() - 1);

			originalIndex = model.getShapes().indexOf(shapeToMove);
			bringToFrontCmd = new BringToFrontCmd(model, shapeToMove);
			commands.add(toBackCmd);
			bringToFrontCmd.execute();
		}
		frame.repaint();
	}

	/*----------------------------------UNDO I REDO-------------------------------*/
	public void undo() {
		if (commands.size() > 0) {
			redoStack.push(commands.get(commands.size() - 1));
			commands.get(commands.size() - 1).unexecute();
			commands.remove(commands.size() - 1);
			frame.repaint();
		}
	}

	public void redo() {
		if (!redoStack.isEmpty()) {
			redoStack.pop().execute();
			commands.add(commands.get(commands.size() - 1));
			frame.repaint();
		}
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
