package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import adapter.HexagonAdapter;
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
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import observer.ButtonsAvailability;
import observer.ButtonsUpdate;
import strategy.Logger;
import strategy.ObjectFileSaveLoad;
import strategy.SaveLoad;
import strategy.TextFileSaveLoad;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private Point startPoint;
	private Shape selectedShape;
	private int selectedOption;
	private int i;
	private int originalIndex;
	private DlgPoint dlgP;
	private DlgLine dlgL;
	private DlgRectangle dlgRect;
	private DlgCircle dialog;
	private DlgDonut dlgDonut;
	private DlgHexagon dlgHex;
	private AddShapeCmd addShapeCmd;
	private RemoveShapeCmd removeShapeCmd;
	private UpdatePointCmd updatePointCmd;
	private UpdateLineCmd updateLineCmd;
	private UpdateRectangleCmd updateRectangleCmd;
	private UpdateCircleCmd updateCircleCmd;
	private UpdateDonutCmd updateDonutCmd;
	private UpdateHexagonCmd updateHexagonCmd;
	private ToFrontCmd toFrontCmd;
	private ToBackCmd toBackCmd;
	private BringToBackCmd bringToBackCmd;
	private BringToFrontCmd bringToFrontCmd;
	private ArrayList<Shape> selectedShapes = new ArrayList();
	private ArrayList<Command> commands = new ArrayList();
	private Stack<Integer> deleteInfo = new Stack<Integer>();
	// private Stack<Command> undoStack= new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	private ButtonsAvailability buttonsAvailability;
	private ButtonsUpdate buttonsUpdate;
	private Logger logger;
	private String[] stringLog;
	private String[] lineSplit;
	private String[] loadSplit;
	private int lineNumber;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		buttonsAvailability = new ButtonsAvailability();
		buttonsUpdate = new ButtonsUpdate(frame);
		buttonsAvailability.addListener(buttonsUpdate);
		enableAndDisableButtons();
		logger = new Logger(this.frame.getTextArea_3());
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
				logger.log("Added point " + newShape.toString());
				redoStack.clear();
				frame.getBtnBorder().setBackground(dlgPoint.getBtnBorderColor().getBackground());
			}
			startPoint = null;

			/*String s = "Added point (113,62), borderColor= (0,0,0)";
			String[] split = s.split(" |\\(|\\)|\\=|\\,");
			for (int i = 0; i < split.length; i++) {
				System.out.println("" + split[i] + i);
			}*/

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
					logger.log("Added line " + newShape.toString());
					redoStack.clear();
					frame.getBtnBorder().setBackground(dlgLine.getBtnLineColor().getBackground());
				}
				startPoint = null;

				/*String s = "Added line (55,104) - (288,100), borderColor= (0,0,0)";
				String[] split = s.split(" |\\(|\\)|\\=|\\,");
				for (int i = 0; i < split.length; i++) {
					System.out.println("" + split[i] + i);
				}*/

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
				logger.log("Added circle " + newShape.toString());
				redoStack.clear();
				frame.getBtnBorder().setBackground(dlgCircle.getBtnBorder().getBackground());
				frame.getBtnInner().setBackground(dlgCircle.getBtnInner().getBackground());
				startPoint = null;
			}

			/*String s = "Added circle Center= (183,53), radius= 45, borderColor= (0,0,0), innerColor= (255,255,255)";
			String[] split = s.split(" |\\(|\\)|\\=|\\,");
			for (int i = 0; i < split.length; i++) {
				System.out.println("" + split[i] + i);
			}*/

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
				logger.log("Added rectangle " + newShape.toString());
				redoStack.clear();
				frame.getBtnBorder().setBackground(dlgRect.getBtnBorderColor().getBackground());
				frame.getBtnInner().setBackground(dlgRect.getBtnInnerColor().getBackground());
				startPoint = null;
			}
			/*String s = "Added rectangle Upper left point (197,46), width=56, height=45 , borderColor= (0,0,0), innerColor= (255,255,255)";
			String[] split = s.split(" |\\(|\\)|\\=|\\,");
			for (int i = 0; i < split.length; i++) {
				System.out.println("" + split[i] + i);
			}*/
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
				logger.log("Added donut " + newShape.toString());
				redoStack.clear();
				frame.getBtnBorder().setBackground(dlgDonut.getBtnDonutBorderColor().getBackground());
				frame.getBtnInner().setBackground(dlgDonut.getBtnDonutInnerColor().getBackground());
				startPoint = null;
			}
			/*String s = "Added donut Center= (255,74), radius= 56, borderColor= (0,0,0), innerColor= (255,255,255), innerRadius= 44";
			String[] split = s.split(" |\\(|\\)|\\=|\\,");
			for (int i = 0; i < split.length; i++) {
				System.out.println("" + split[i] + i);
			}*/
		}

		if (frame.getTglbtnHexagon().isSelected()) {
			DlgHexagon dlgHexagon = new DlgHexagon();
			dlgHexagon.getTxtCoordinateX().setText(Integer.toString(e.getX()));
			dlgHexagon.getTxtCoordinateY().setText(Integer.toString(e.getY()));
			dlgHexagon.getTxtCoordinateX().setEditable(false);
			dlgHexagon.getTxtCoordinateY().setEditable(false);
			dlgHexagon.getBtnBorder().setBackground(frame.getBtnBorder().getBackground());
			dlgHexagon.getBtnInner().setBackground(frame.getBtnInner().getBackground());
			dlgHexagon.setVisible(true);

			if (dlgHexagon.isHOk()) {
				/*
				 * newShape = new HexagonAdapter(new Hexagon(e.getX(), e.getY()),
				 * (Integer.parseInt(dlgCircle.getTxtRadius().getText())),
				 * dlgCircle.getBtnBorder().getBackground(),
				 * dlgHexagon.getBtnInner().getBackground());
				 */
				HexagonAdapter ha = new HexagonAdapter(
						new Hexagon(e.getX(), e.getY(), Integer.parseInt(dlgHexagon.getTxtRadius().getText())));
				ha.setColor(dlgHexagon.getBtnBorder().getBackground());
				ha.setInnerColor(dlgHexagon.getBtnInner().getBackground());
				// model.add(newShape);
				addShapeCmd = new AddShapeCmd(model, ha);
				addShapeCmd.execute();
				commands.add(addShapeCmd);
				logger.log("Added hexagon " + ha.toString());
				redoStack.clear();
				frame.getBtnBorder().setBackground(dlgHexagon.getBtnBorder().getBackground());
				frame.getBtnInner().setBackground(dlgHexagon.getBtnInner().getBackground());
				startPoint = null;
			}
			/*String s = "Added hexagon Center (361,36), radius= 45, borderColor= (0,0,0), innerColor= (255,255,255)";
			String[] split = s.split(" |\\(|\\)|\\=|\\,");
			for (int i = 0; i < split.length; i++) {
				System.out.println("" + split[i] + i);
			}*/
		}

		if (frame.getTglbtnSelect().isSelected()) {
			Point point = new Point(e.getX(), e.getY());
			selectShapes(point.getX(), point.getY());
		}
		// undoStack.push(addShapeCmd);
		// redoStack.clear();

		frame.repaint();
		enableAndDisableButtons();
	}

	/*----------------------------------METODA ZA MODIFIKACIJU-------------------------------*/
	public void modify() {

		for (int i = 0; i < model.getShapes().size(); i++) {

			if (model.getShapes().get(i) instanceof Point) {

				if (model.getShapes().get(i).isSelected()) {

					dlgP = new DlgPoint();
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
						logger.log("Modify point -> " + p.toString());
						commands.add(updatePointCmd);
						frame.getBtnBorder().setBackground(dlgP.getBtnBorderColor().getBackground());
						frame.repaint();
						String s = "Modify point -> (186,74), borderColor= (0,0,255)";
						String[] split = s.split(" |\\(|\\)|\\=|\\,");
						for (int e = 0; e < split.length; e++) {
							System.out.println("" + split[e] + e);
						}

					}
				}
			}
			if (model.getShapes().get(i) instanceof Line) {

				if (model.getShapes().get(i).isSelected()) {

					dlgL = new DlgLine();
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
						logger.log("Modify line -> " + l2.toString());
						commands.add(updateLineCmd);
						frame.getBtnBorder().setBackground(dlgL.getBtnLineColor().getBackground());
						/*
						 * originalLine.setStartPoint(startPoint1); originalLine.setEndPoint(endPoint1);
						 * originalLine.setColor(dlgL.getBtnLineColor().getBackground());
						 */

						/* setShape(index, originalLine); */
						
						/*String s = "Modify line -> (105,55) - (359,120), borderColor= (255,0,0)";
						String[] split = s.split(" |\\(|\\)|\\=|\\,");
						for (int e = 0; e < split.length; e++) {
							System.out.println("" + split[e] + e);
						}
						frame.repaint();*/
					}
				}
			}
			if (model.getShapes().get(i) instanceof Rectangle) {

				if (model.getShapes().get(i).isSelected()) {

					dlgRect = new DlgRectangle();
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
						logger.log("Modify rectangle -> " + r2.toString());
						commands.add(updateRectangleCmd);
						frame.getBtnBorder().setBackground(dlgRect.getBtnBorderColor().getBackground());
						frame.getBtnInner().setBackground(dlgRect.getBtnInnerColor().getBackground());

						/* setShape(index, originalRect); */
						
						String s = "Modify rectangle -> Upper left point (173,21), width=34, height=45 , borderColor= (255,153,153), innerColor= (255,255,255)";
						String[] split = s.split(" |\\(|\\)|\\=|\\,");
						for (int e = 0; e < split.length; e++) {
							System.out.println("" + split[e] + e);
						}
						frame.repaint();
					}
				}
			}
			if (model.getShapes().get(i) instanceof Circle == true
					&& model.getShapes().get(i) instanceof Donut == false) {

				if (model.getShapes().get(i).isSelected()) {

					Circle originalCircle = (Circle) model.getShapes().get(i);
					dialog = new DlgCircle();
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
						logger.log("Modify circle -> " + c2.toString());
						commands.add(updateCircleCmd);
						frame.getBtnBorder().setBackground(dialog.getBtnBorder().getBackground());
						frame.getBtnInner().setBackground(dialog.getBtnInner().getBackground());

						/*String s = "Modify circle -> Center= (253,39), radius= 45, borderColor= (255,102,51), innerColor= (255,255,255)";
						String[] split = s.split(" |\\(|\\)|\\=|\\,");
						for (int e = 0; e < split.length; e++) {
							System.out.println("" + split[e] + e);
						}*/
						frame.repaint();

					}
				}

			}

			if (model.getShapes().get(i) instanceof Donut) {

				if (model.getShapes().get(i).isSelected()) {

					dlgDonut = new DlgDonut();
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
						logger.log("Modify donut -> " + d2.toString());
						commands.add(updateDonutCmd);
						frame.getBtnBorder().setBackground(dlgDonut.getBtnDonutBorderColor().getBackground());
						frame.getBtnInner().setBackground(dlgDonut.getBtnDonutInnerColor().getBackground());
						
						frame.repaint();
					}
				}
			}

			if (model.getShapes().get(i) instanceof HexagonAdapter) {
				if (model.getShapes().get(i).isSelected()) {

					dlgHex = new DlgHexagon();
					HexagonAdapter originalHexagon = (HexagonAdapter) model.getShapes().get(i);
					dlgHex.setHexagon(originalHexagon);
					dlgHex.setVisible(true);

					if (dlgHex.isHOk()) {
						int cX = (Integer.parseInt(dlgHex.getTxtCoordinateX().getText().trim()));
						int cY = (Integer.parseInt(dlgHex.getTxtCoordinateY().getText().trim()));
						int radius = (Integer.parseInt(dlgHex.getTxtRadius().getText().trim()));
						Color colorBorder = dlgHex.getBtnBorder().getBackground();
						Color colorInner = dlgHex.getBtnInner().getBackground();
						HexagonAdapter ha = new HexagonAdapter(new Hexagon(cX, cY, radius));
						ha.setColor(colorBorder);
						ha.setInnerColor(colorInner);
						ha.setSelected(true);

						updateHexagonCmd = new UpdateHexagonCmd(originalHexagon, ha);
						updateHexagonCmd.execute();
						logger.log("Modify hexagon -> " + ha.toString());
						commands.add(updateHexagonCmd);
						frame.getBtnBorder().setBackground(dlgHex.getBtnBorder().getBackground());
						frame.getBtnInner().setBackground(dlgHex.getBtnInner().getBackground());
						frame.repaint();
						
						String s = "Modify hexagon -> Center (333,58), radius= 21, borderColor= (51,255,51), innerColor= (255,51,102)";
						String[] split = s.split(" |\\(|\\)|\\=|\\,");
						for (int e = 0; e < split.length; e++) {
							System.out.println("" + split[e] + e);
						}
					}
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
						deleteInfo.push(model.getShapes().indexOf(model.getShapes().get(i)));
						removeShapeCmd = new RemoveShapeCmd(model, model.getShapes().get(i), selectedShapes,
								deleteInfo);// izmena
						/*if (model.getShapes().get(i) instanceof Point) {
							logger.log("Deleted point " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof Line) {
							logger.log("Deleted line " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof Donut) {
							logger.log("Deleted donut " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof Circle) {
							logger.log("Deleted circle " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof Rectangle) {
							logger.log("Deleted rectangle " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof HexagonAdapter) {
							logger.log("Deleted hexagon " + model.getShapes().get(i).toString());
						}*/
						logger.log(removeShapeCmd.toString());
						selectedShapes.remove(model.getShapes().get(i));
						removeShapeCmd.execute();

						commands.add(removeShapeCmd);
						frame.repaint();
					} else {
						DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, model.getShapes().get(i));
						if (model.getShapes().get(i) instanceof Point) {
							logger.log("Deselected point " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof Line) {
							logger.log("Deselected line " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof Donut) {
							logger.log("Deselected donut " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof Circle) {
							logger.log("Deselected circle " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof Rectangle) {
							logger.log("Deselected rectangle " + model.getShapes().get(i).toString());
						} else if (model.getShapes().get(i) instanceof HexagonAdapter) {
							logger.log("Deselected hexagon " + model.getShapes().get(i).toString());
						}
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
					deleteInfo.push(model.getShapes().indexOf(selectedShapes.get(i)));
					removeShapeCmd = new RemoveShapeCmd(model, selectedShapes.get(i), selectedShapes, deleteInfo);// izmena
					/*if (model.getShapes().get(i) instanceof Point) {
						logger.log("Deleted point " + selectedShapes.get(i).toString());
					} else if (model.getShapes().get(i) instanceof Line) {
						logger.log("Deleted line " + selectedShapes.get(i).toString());
					} else if (model.getShapes().get(i) instanceof Donut) {
						logger.log("Deleted donut " + selectedShapes.get(i).toString());
					} else if (model.getShapes().get(i) instanceof Circle) {
						logger.log("Deleted circle " + selectedShapes.get(i).toString());
					} else if (model.getShapes().get(i) instanceof Rectangle) {
						logger.log("Deleted rectangle " + selectedShapes.get(i).toString());
					} else if (model.getShapes().get(i) instanceof HexagonAdapter) {
						logger.log("Deleted hexagon " + selectedShapes.get(i).toString());
					}*/
					logger.log(removeShapeCmd.toString());
					removeShapeCmd.execute();

					commands.add(removeShapeCmd);
					selectedShapes.remove(i);
				}
				frame.repaint();
			} else {
				for (int i = 0; i < model.getShapes().size(); i++) {
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, model.getShapes().get(i));
					if (model.getShapes().get(i) instanceof Point) {
						logger.log("Deselected point " + model.getShapes().get(i).toString());
					} else if (model.getShapes().get(i) instanceof Line) {
						logger.log("Deselected line " + model.getShapes().get(i).toString());
					} else if (model.getShapes().get(i) instanceof Donut) {
						logger.log("Deselected donut " + model.getShapes().get(i).toString());
					} else if (model.getShapes().get(i) instanceof Circle) {
						logger.log("Deselected circle " + model.getShapes().get(i).toString());
					} else if (model.getShapes().get(i) instanceof Rectangle) {
						logger.log("Deselected rectangle " + model.getShapes().get(i).toString());
					} else if (model.getShapes().get(i) instanceof HexagonAdapter) {
						logger.log("Deselected hexagon " + model.getShapes().get(i).toString());
					}
					deselectCmd.execute();

					commands.add(deselectCmd);
				}

			}
			frame.repaint();
			enableAndDisableButtons();
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
					selectCmd.execute();
					if (shape != null) {
						if (shape instanceof Point) {
							logger.log("Selected point " + shape.toString());

							/*
							 * String s = "Selected point (109,46), borderColor= (0,0,0)"; String[] split =
							 * s.split(" |\\(|\\)|\\=|\\,"); for (int e = 0; e < split.length; e++) {
							 * System.out.println("" + split[e] + e);
							 */
						} else if (shape instanceof Line) {

							logger.log("Selected line " + shape.toString());

							/*
							 * String s = "Selected line (102,78) - (300,92), borderColor= (0,0,0)";
							 * String[] split = s.split(" |\\(|\\)|\\=|\\,"); for (int e = 0; e <
							 * split.length; e++) { System.out.println("" + split[e] + e);}
							 */

						} else if (shape instanceof Donut) {

							logger.log("Selected donut " + shape.toString());

						} else if (shape instanceof Circle) {

							logger.log("Selected circle " + shape.toString());

							/*String s = "Selected circle Center= (232,60), radius= 56, borderColor= (0,0,0), innerColor= (255,255,255)";
							String[] split = s.split(" |\\(|\\)|\\=|\\,");
							for (int e = 0; e < split.length; e++) {
								System.out.println("" + split[e] + e);
							}*/

						} else if (shape instanceof Rectangle) {

							
							 logger.log("Selected rectangle " + shape.toString());
							  
							 /* String s =
							 * "Selected rectangle Upper left point (202,54), width=56, height=45 , borderColor= (0,0,0), innerColor= (255,255,255)"
							 * ; String[] split = s.split(" |\\(|\\)|\\=|\\,"); for (int e = 0; e <
							 * split.length; e++) { System.out.println("" + split[e] + e);}
							 */

						} else if (shape instanceof HexagonAdapter) {
							
							  logger.log("Selected hexagon " + shape.toString()); 
							  /*String s = "Selected hexagon Center (356,28), radius= 45, borderColor= (0,0,0), innerColor= (255,255,255)"
							 * ; String[] split = s.split(" |\\(|\\)|\\=|\\,"); for (int e = 0; e <
							 * split.length; e++) { System.out.println("" + split[e] + e);}
							 */
						}
					}
					commands.add(selectCmd);
				} else {
					DeselectCmd deselectCmd = new DeselectCmd(selectedShapes, (Shape) model.getShapes().get(i));
					// commands.add(deselectCmd);
					deselectCmd.execute();

					if (model.getShapes().get(i) instanceof Point) {
						logger.log("Deselected point " + model.getShapes().get(i).toString());
						
						  /*String s = "Deselected point (153,31), borderColor= (0,0,0)"; 
						  String[] split = s.split(" |\\(|\\)|\\=|\\,"); 
						  for (int e = 0; e < split.length; e++) {
							  System.out.println("" + split[e] + e);
						  }*/
						 
					} else if (model.getShapes().get(i) instanceof Line) {
						
						logger.log("Deselected line " + model.getShapes().get(i).toString());
						
						/*String s = "Deselected line (114,52) - (365,53), borderColor= (0,0,0),0"; 
						String[] split = s.split(" |\\(|\\)|\\=|\\,"); 
						  for (int e = 0; e < split.length; e++) {
							  System.out.println("" + split[e] + e);
						  }*/
								
					} else if (model.getShapes().get(i) instanceof Donut) {
						logger.log("Deselected donut " + model.getShapes().get(i).toString());
						
						String s = "Deselected donut Center= (185,52), radius= 56, borderColor= (0,0,0), innerColor= (255,255,255), innerRadius= 34"; 
						String[] split = s.split(" |\\(|\\)|\\=|\\,"); 
						  for (int e = 0; e < split.length; e++) {
							  System.out.println("" + split[e] + e);
						  }
					} else if (model.getShapes().get(i) instanceof Circle) {
						logger.log("Deselected circle " + model.getShapes().get(i).toString());
						
						  /*String s = "Deselected circle Center= (272,42), radius= 45, borderColor= (0,0,0), innerColor= (255,255,255)"; 
						  String[] split = s.split(" |\\(|\\)|\\=|\\,"); 
						  for (int e = 0; e < split.length; e++) {
							  System.out.println("" + split[e] + e);
						  }*/
					} else if (model.getShapes().get(i) instanceof Rectangle) {
						logger.log("Deselected rectangle " + model.getShapes().get(i).toString());
					} else if (model.getShapes().get(i) instanceof HexagonAdapter) {
						logger.log("Deselected hexagon " + model.getShapes().get(i).toString());
					}
					commands.add(deselectCmd);
				}
			}
		}
		enableAndDisableButtons();
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
			
			if (selectedShapes.get(selectedShapes.size() - 1) instanceof Point) {
				logger.log("To front point " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Line) {
				logger.log("To front line " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Donut) {
				logger.log("To front donut " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Circle) {
				logger.log("To front circle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Rectangle) {
				logger.log("To front rectangle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof HexagonAdapter) {
				logger.log("To front hexagon " + selectedShapes.get(selectedShapes.size() - 1));
			}
			//logger.log("To front " + selectedShapes.get(selectedShapes.size() - 1));
			toFrontCmd.execute();
		}
		frame.repaint();
		enableAndDisableButtons();
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
			
			if (selectedShapes.get(selectedShapes.size() - 1) instanceof Point) {
				logger.log("To back point " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Line) {
				logger.log("To back line " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Donut) {
				logger.log("To back donut " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Circle) {
				logger.log("To back circle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Rectangle) {
				logger.log("To back rectangle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof HexagonAdapter) {
				logger.log("To back hexagon " + selectedShapes.get(selectedShapes.size() - 1));
			}
			//logger.log("To_back: " + selectedShapes.get(selectedShapes.size() - 1));
			toBackCmd.execute();
		}
		frame.repaint();
		enableAndDisableButtons();
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
			//System.out.println("Original index in method is"+originalIndex);
			bringToBackCmd = new BringToBackCmd(model, shapeToMove,originalIndex);
			commands.add(bringToBackCmd);
			
			if (selectedShapes.get(selectedShapes.size() - 1) instanceof Point) {
				logger.log("Bring to back point " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Line) {
				logger.log("Bring to back line " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Donut) {
				logger.log("Bring to back donut " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Circle) {
				logger.log("Bring to back circle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Rectangle) {
				logger.log("Bring to back rectangle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof HexagonAdapter) {
				logger.log("Bring to back hexagon " + selectedShapes.get(selectedShapes.size() - 1));
			}
			
			bringToBackCmd.execute();
		}
		frame.repaint();
		enableAndDisableButtons();
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
			bringToFrontCmd = new BringToFrontCmd(model, shapeToMove,originalIndex);
			commands.add(bringToFrontCmd);
			
			if (selectedShapes.get(selectedShapes.size() - 1) instanceof Point) {
				logger.log("Bring to front point " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Line) {
				logger.log("Bring to front line " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Donut) {
				logger.log("Bring to front donut " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Circle) {
				logger.log("Bring to front circle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Rectangle) {
				logger.log("Bring to front rectangle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof HexagonAdapter) {
				logger.log("Bring to front hexagon " + selectedShapes.get(selectedShapes.size() - 1));
			}
			
			bringToFrontCmd.execute();
		}
		frame.repaint();
		enableAndDisableButtons();
	}

	/*----------------------------------UNDO I REDO-------------------------------*/
	public void undo() {
		if (commands.size() > 0) {
			logger.log("Undo " + commands.get(commands.size() - 1));
			redoStack.push(commands.get(commands.size() - 1));
			commands.get(commands.size() - 1).unexecute();
			commands.remove(commands.size() - 1);
			frame.repaint();
		}
		enableAndDisableButtons();
	}

	public void redo() {
		if (!redoStack.isEmpty()) {
			System.out.println("Usao je u redo");
			System.out.println("redo stack poslednji"+ redoStack.peek());
			logger.log("Redo " + redoStack.peek());
			redoStack.peek().execute();
			commands.add(redoStack.pop());
			frame.repaint();
		}
		enableAndDisableButtons();
	}

	/*----------------------------------OBSERVER METODA-------------------------------*/

	public void enableAndDisableButtons1() {

		if (this.model.getShapes().size() != 0) {
			this.buttonsAvailability.setSelectButton(true);

			if (this.selectedShapes.size() == 0) {

				this.buttonsAvailability.setModifyButton(false);
				this.buttonsAvailability.setDeleteButton(false);
			} else if (this.selectedShapes.size() == 1) {
				this.buttonsAvailability.setModifyButton(true);
				this.buttonsAvailability.setDeleteButton(true);
			} else {
				this.buttonsAvailability.setDeleteButton(true);
				this.buttonsAvailability.setSelectButton(true);
				this.buttonsAvailability.setModifyButton(false);
			}
		} else {
			this.buttonsAvailability.setDeleteButton(false);
			this.buttonsAvailability.setModifyButton(false);
			this.buttonsAvailability.setSelectButton(false);
		}
	}

	public void enableAndDisableButtons2() {
		if (model.getShapes().size() > 1) {
			if (selectedShapes.size() == 1) {

				int selectedIndex = getIndex();

				if (selectedIndex > 0) {

					this.buttonsAvailability.setToBackButton(true);
					this.buttonsAvailability.setBringToBackButton(true);

					if (getIndex() < model.getShapes().size() - 1) {
						this.buttonsAvailability.setToFrontButton(true);
						this.buttonsAvailability.setBringToFrontButton(true);
					} else {
						this.buttonsAvailability.setToFrontButton(false);
						this.buttonsAvailability.setBringToFrontButton(false);
					}
				}
				if (selectedIndex < model.getShapes().size() - 1) {

					this.buttonsAvailability.setToFrontButton(true);
					this.buttonsAvailability.setBringToFrontButton(true);

					if (getIndex() > 0) {
						this.buttonsAvailability.setToBackButton(true);
						this.buttonsAvailability.setBringToBackButton(true);
					} else {
						this.buttonsAvailability.setToBackButton(false);
						this.buttonsAvailability.setBringToBackButton(false);
					}
				}

			} else {
				this.buttonsAvailability.setToBackButton(false);
				this.buttonsAvailability.setToFrontButton(false);
				this.buttonsAvailability.setBringToBackButton(false);
				this.buttonsAvailability.setBringToFrontButton(false);
			}
		} else {
			this.buttonsAvailability.setToBackButton(false);
			this.buttonsAvailability.setToFrontButton(false);
			this.buttonsAvailability.setBringToBackButton(false);
			this.buttonsAvailability.setBringToFrontButton(false);
		}
	}

	public void enableAndDisableButtons3() {
		if (commands.size() > 0) {
			this.buttonsAvailability.setUndoButton(true);
		} else {
			this.buttonsAvailability.setUndoButton(false);
		}
		if (redoStack.size() > 0) {
			this.buttonsAvailability.setRedoButton(true);
		} else {
			this.buttonsAvailability.setRedoButton(false);
		}
	}

	public void enableAndDisableButtons() {
		enableAndDisableButtons1();
		enableAndDisableButtons2();
		enableAndDisableButtons3();
	}

	/*----------------------------------SAVE LOAD METODA-------------------------------*/
	public void saveData(String filePath) {
		SaveLoad objectFileSave = new ObjectFileSaveLoad(model);
		objectFileSave.saveData(filePath);
		SaveLoad textFileSave = new TextFileSaveLoad(logger.getTextArea().getText().split("\n"), logger);
		textFileSave.saveData(filePath);
	}

	public void loadData(String filePath) {
		if (filePath.endsWith(".txt")) {
			SaveLoad textFileLoad = new TextFileSaveLoad(this, logger);
			textFileLoad.loadData(filePath);
			frame.getBtnNext().setVisible(true);

			// stringLog = logger.getTextArea().getText().split("\n");
			lineNumber = 0;
		} else {
			SaveLoad objectFileLoad = new ObjectFileSaveLoad(model);
			objectFileLoad.loadData(filePath);
			frame.repaint();
		}
	}
	

	public void next() {
		
		if(lineNumber==loadSplit.length) {
			JOptionPane.showMessageDialog(null, "File is loaded!");
		}
		else {
		String line =loadSplit[lineNumber];
		lineSplit = line.split(" ");
/*---------------------------------------------------ADD---------------------------------------------------------------------*/
		String [] linePart = line.split(" |\\(|\\)|\\=|\\,");
		int option = JOptionPane.showConfirmDialog(null,"Do you want to execute " + loadSplit[lineNumber]); 
		if(option == JOptionPane.YES_OPTION) {
		if(lineSplit[0].equals("Added") && lineSplit[1].equals("point")){
			
			int r = Integer.parseInt(linePart[10]);
			int g = Integer.parseInt(linePart[11]);
			int b = Integer.parseInt(linePart[12]);
			Point p = new Point(Integer.parseInt(linePart[3]),Integer.parseInt(linePart[4]), new Color(r,g,b));
			AddShapeCmd addShape = new AddShapeCmd(model,p);
			commands.add(addShape);
			addShape.execute();
			redoStack.clear();
			logger.log("Added point " + p.toString());
		}
		else if(lineSplit[0].equals("Added") && lineSplit[1].equals("line")) {
			
			int r = Integer.parseInt(linePart[15]);
			int g = Integer.parseInt(linePart[16]);
			int b = Integer.parseInt(linePart[17]);
			Line l = new Line(new Point(Integer.parseInt(linePart[3]),Integer.parseInt(linePart[4])), new Point(Integer.parseInt(linePart[8]),
					Integer.parseInt(linePart[9])), new Color(r,g,b));
			AddShapeCmd addShape = new AddShapeCmd(model,l);
			commands.add(addShape);
			addShape.execute();
			redoStack.clear();
			logger.log("Added line " + l.toString());
		}
		else if(lineSplit[0].equals("Added") && lineSplit[1].equals("circle")) {
			
			int r = Integer.parseInt(linePart[16]);
			int g = Integer.parseInt(linePart[17]);
			int b = Integer.parseInt(linePart[18]);
			int ri = Integer.parseInt(linePart[24]);
			int gi = Integer.parseInt(linePart[25]);
			int bi = Integer.parseInt(linePart[26]);
			Circle c = new Circle(new Point(Integer.parseInt(linePart[5]),Integer.parseInt(linePart[6])), Integer.parseInt(linePart[11])
					,new Color(r,g,b), new Color(ri,gi,bi));
			AddShapeCmd addShape = new AddShapeCmd(model,c);
			commands.add(addShape);
			addShape.execute();
			redoStack.clear();
			logger.log("Added circle " + c.toString());
		}
		else if(lineSplit[0].equals("Added") && lineSplit[1].equals("rectangle")) {
			
			int r = Integer.parseInt(linePart[20]);
			int g = Integer.parseInt(linePart[21]);
			int b = Integer.parseInt(linePart[22]);
			int ri = Integer.parseInt(linePart[28]);
			int gi = Integer.parseInt(linePart[29]);
			int bi = Integer.parseInt(linePart[30]);
			Rectangle rect = new Rectangle(new Point(Integer.parseInt(linePart[6]), Integer.parseInt(linePart[7])), Integer.parseInt(linePart[11]), 
					Integer.parseInt(linePart[14]), new Color(r,g,b), new Color(ri,gi,bi));
			AddShapeCmd addShape = new AddShapeCmd(model,rect);
			commands.add(addShape);
			addShape.execute();
			redoStack.clear();
			logger.log("Added rectangle " + rect.toString());
			frame.repaint();
		}
		else if(lineSplit[0].equals("Added") && lineSplit[1].equals("donut")) {
			
			int r = Integer.parseInt(linePart[16]);
			int g = Integer.parseInt(linePart[17]);
			int b = Integer.parseInt(linePart[18]);
			int ri = Integer.parseInt(linePart[24]);
			int gi = Integer.parseInt(linePart[25]);
			int bi = Integer.parseInt(linePart[26]);
			System.out.println(""+linePart[31]+(linePart.length));//Integer.parseInt
			Donut d = new Donut(new Point(Integer.parseInt(linePart[5]), Integer.parseInt(linePart[6])), Integer.parseInt(linePart[11]), Integer.parseInt(linePart[31].trim()),
					new Color(r,g,b), new Color(ri,gi,bi));
			AddShapeCmd addShape = new AddShapeCmd(model,d);
			commands.add(addShape);
			addShape.execute();
			redoStack.clear();
			logger.log("Added donut " + d.toString());
		}
		else if(lineSplit[0].equals("Added") && lineSplit[1].equals("hexagon")) {
			
			int r = Integer.parseInt(linePart[15]);
			int g = Integer.parseInt(linePart[16]);
			int b = Integer.parseInt(linePart[17]);
			int ri = Integer.parseInt(linePart[23]);
			int gi = Integer.parseInt(linePart[24]);
			int bi = Integer.parseInt(linePart[25]);
			HexagonAdapter ha = new HexagonAdapter(new Hexagon(Integer.parseInt(linePart[4]), Integer.parseInt(linePart[5]), Integer.parseInt(linePart[10])));
			ha.setColor(new Color(r,g,b));
			ha.setInnerColor(new Color(ri,gi,bi));
			AddShapeCmd addShape = new AddShapeCmd(model,ha);
			commands.add(addShape);
			addShape.execute();
			redoStack.clear();
			logger.log("Added hexagon " + ha.toString());
		}

/*-------------------------------------------SELECT-----------------------------------------------------------------*/
		else if(lineSplit[0].equals("Selected")){
			if(lineSplit[1].equals("point")) {
				
				int r = Integer.parseInt(linePart[10]);
				int g = Integer.parseInt(linePart[11]);
				int b = Integer.parseInt(linePart[12]);
				Point p = new Point(Integer.parseInt(linePart[3]),Integer.parseInt(linePart[4]), new Color(r,g,b));
				int index = model.getShapes().indexOf(p);
				SelectCmd selectCmd = new SelectCmd(selectedShapes, model.getShapes().get(index));
				commands.add(selectCmd);
				selectCmd.execute();
				logger.log("Selected point " + model.getShapes().get(index).toString());
				frame.repaint();
			}
			else if(lineSplit[1].equals("line")) {
				
				int r = Integer.parseInt(linePart[15]);
				int g = Integer.parseInt(linePart[16]);
				int b = Integer.parseInt(linePart[17]);
				Line l = new Line(new Point(Integer.parseInt(linePart[3]),Integer.parseInt(linePart[4])), new Point(Integer.parseInt(linePart[8]),
						Integer.parseInt(linePart[9])), new Color(r,g,b));
				int index = model.getShapes().indexOf(l);
				SelectCmd selectCmd = new SelectCmd(selectedShapes, model.getShapes().get(index));
				commands.add(selectCmd);
				selectCmd.execute();
				logger.log("Selected line " + model.getShapes().get(index).toString());
				frame.repaint();
			}
			else if(lineSplit[1].equals("rectangle")) {
				
				int r = Integer.parseInt(linePart[20]);
				int g = Integer.parseInt(linePart[21]);
				int b = Integer.parseInt(linePart[22]);
				int ri = Integer.parseInt(linePart[28]);
				int gi = Integer.parseInt(linePart[29]);
				int bi = Integer.parseInt(linePart[30]);
				Rectangle rect = new Rectangle(new Point(Integer.parseInt(linePart[6]), Integer.parseInt(linePart[7])), Integer.parseInt(linePart[11]), 
						Integer.parseInt(linePart[14]), new Color(r,g,b), new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(rect);
				SelectCmd selectCmd = new SelectCmd(selectedShapes, model.getShapes().get(index));
				commands.add(selectCmd);
				selectCmd.execute();
				logger.log("Selected rectangle " + model.getShapes().get(index).toString());
				frame.repaint();
			}
			else if(lineSplit[1].equals("circle")) {
				
				int r = Integer.parseInt(linePart[16]);
				int g = Integer.parseInt(linePart[17]);
				int b = Integer.parseInt(linePart[18]);
				int ri = Integer.parseInt(linePart[24]);
				int gi = Integer.parseInt(linePart[25]);
				int bi = Integer.parseInt(linePart[26]);
				Circle c = new Circle(new Point(Integer.parseInt(linePart[5]),Integer.parseInt(linePart[6])), Integer.parseInt(linePart[11])
						,new Color(r,g,b), new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(c);
				SelectCmd selectCmd = new SelectCmd(selectedShapes, model.getShapes().get(index));
				commands.add(selectCmd);
				selectCmd.execute();
				logger.log("Selected circle " + model.getShapes().get(index).toString());
				frame.repaint();
			}
			else if(lineSplit[1].equals("donut")) {
				
				int r = Integer.parseInt(linePart[16]);
				int g = Integer.parseInt(linePart[17]);
				int b = Integer.parseInt(linePart[18]);
				int ri = Integer.parseInt(linePart[24]);
				int gi = Integer.parseInt(linePart[25]);
				int bi = Integer.parseInt(linePart[26]);
				Donut d = new Donut(new Point(Integer.parseInt(linePart[5]), Integer.parseInt(linePart[6])), Integer.parseInt(linePart[11]), Integer.parseInt(linePart[31].trim()),
						new Color(r,g,b), new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(d);
				SelectCmd selectCmd = new SelectCmd(selectedShapes, model.getShapes().get(index));
				commands.add(selectCmd);
				selectCmd.execute();
				logger.log("Selected donut " + model.getShapes().get(index).toString());
				frame.repaint();
			}
			else if(lineSplit[1].equals("hexagon")) {
				
				int r = Integer.parseInt(linePart[15]);
				int g = Integer.parseInt(linePart[16]);
				int b = Integer.parseInt(linePart[17]);
				int ri = Integer.parseInt(linePart[23]);
				int gi = Integer.parseInt(linePart[24]);
				int bi = Integer.parseInt(linePart[25]);
				HexagonAdapter ha = new HexagonAdapter(new Hexagon(Integer.parseInt(linePart[4]), Integer.parseInt(linePart[5]), Integer.parseInt(linePart[10])));
				ha.setColor(new Color(r,g,b));
				ha.setInnerColor(new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(ha);
				SelectCmd selectCmd = new SelectCmd(selectedShapes, model.getShapes().get(index));
				commands.add(selectCmd);
				selectCmd.execute();
				logger.log("Selected hexagon " + model.getShapes().get(index).toString());
				frame.repaint();
			}
			
		}
		
/*-------------------------------------------------DESELECT-----------------------------------------------------------*/
		else if(lineSplit[0].equals("Deselected")) {
			
			if(lineSplit[1].equals("point")) {
				
				int r = Integer.parseInt(linePart[10]);
				int g = Integer.parseInt(linePart[11]);
				int b = Integer.parseInt(linePart[12]);
				Point p = new Point(Integer.parseInt(linePart[3]),Integer.parseInt(linePart[4]), new Color(r,g,b));
				int index = model.getShapes().indexOf(p);
				DeselectCmd deselectShape = new DeselectCmd(selectedShapes,model.getShapes().get(index));
				commands.add(deselectShape);
				deselectShape.execute();
				logger.log("Deselected point " + model.getShapes().get(index).toString());
				frame.repaint();
			}
			else if(lineSplit[1].equals("line")) {	
				
				int r = Integer.parseInt(linePart[15]);
				int g = Integer.parseInt(linePart[16]);
				int b = Integer.parseInt(linePart[17]);
				Line l = new Line(new Point(Integer.parseInt(linePart[3]),Integer.parseInt(linePart[4])), new Point(Integer.parseInt(linePart[8]),
						Integer.parseInt(linePart[9])), new Color(r,g,b));
				int index = model.getShapes().indexOf(l);
				DeselectCmd deselectShape = new DeselectCmd(selectedShapes,model.getShapes().get(index));
				commands.add(deselectShape);
				deselectShape.execute();
				logger.log("Deselected line " + model.getShapes().get(index).toString());
			}
			else if(lineSplit[1].equals("rectangle"))
			{
				int r = Integer.parseInt(linePart[20]);
				int g = Integer.parseInt(linePart[21]);
				int b = Integer.parseInt(linePart[22]);
				int ri = Integer.parseInt(linePart[28]);
				int gi = Integer.parseInt(linePart[29]);
				int bi = Integer.parseInt(linePart[30]);
				Rectangle rect = new Rectangle(new Point(Integer.parseInt(linePart[6]), Integer.parseInt(linePart[7])), Integer.parseInt(linePart[11]), 
						Integer.parseInt(linePart[14]), new Color(r,g,b), new Color(ri,gi,bi));
				int index=model.getShapes().indexOf(rect);
				DeselectCmd deselectShape = new DeselectCmd(selectedShapes,model.getShapes().get(index));
				commands.add(deselectShape);
				deselectShape.execute();
				logger.log("Deselected rectangle " + model.getShapes().get(index).toString());
			}
			else if(lineSplit[1].equals("circle"))
			{
				int r = Integer.parseInt(linePart[16]);
				int g = Integer.parseInt(linePart[17]);
				int b = Integer.parseInt(linePart[18]);
				int ri = Integer.parseInt(linePart[24]);
				int gi = Integer.parseInt(linePart[25]);
				int bi = Integer.parseInt(linePart[26]);
				Circle c = new Circle(new Point(Integer.parseInt(linePart[5]),Integer.parseInt(linePart[6])), Integer.parseInt(linePart[11])
						,new Color(r,g,b), new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(c);
				DeselectCmd deselectShape = new DeselectCmd(selectedShapes,model.getShapes().get(index));
				commands.add(deselectShape);
				deselectShape.execute();
				logger.log("Deselected circle " + model.getShapes().get(index).toString());
			}
			else if(lineSplit[1].equals("donut"))
			{
				int r = Integer.parseInt(linePart[16]);
				int g = Integer.parseInt(linePart[17]);
				int b = Integer.parseInt(linePart[18]);
				int ri = Integer.parseInt(linePart[24]);
				int gi = Integer.parseInt(linePart[25]);
				int bi = Integer.parseInt(linePart[26]);
				Donut d = new Donut(new Point(Integer.parseInt(linePart[5]), Integer.parseInt(linePart[6])), Integer.parseInt(linePart[11]), Integer.parseInt(linePart[31].trim()),
						new Color(r,g,b), new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(d);
				DeselectCmd deselectShape = new DeselectCmd(selectedShapes,model.getShapes().get(index));
				commands.add(deselectShape);
				deselectShape.execute();
				logger.log("Deselected donut " + model.getShapes().get(index).toString());
			}
			else if(lineSplit[1].equals("hexagon"))
			{
				int r = Integer.parseInt(linePart[15]);
				int g = Integer.parseInt(linePart[16]);
				int b = Integer.parseInt(linePart[17]);
				int ri = Integer.parseInt(linePart[23]);
				int gi = Integer.parseInt(linePart[24]);
				int bi = Integer.parseInt(linePart[25]);
				HexagonAdapter ha = new HexagonAdapter(new Hexagon(Integer.parseInt(linePart[4]), Integer.parseInt(linePart[5]), Integer.parseInt(linePart[10])));
				ha.setColor(new Color(r,g,b));
				ha.setInnerColor(new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(ha);
				DeselectCmd deselectShape = new DeselectCmd(selectedShapes,model.getShapes().get(index));
				commands.add(deselectShape);
				deselectShape.execute();
				logger.log("Deselected hexagon " + model.getShapes().get(index).toString());
			}
		}
		
/*--------------------------------------------------MODIFY--------------------------------------------------------*/
		else if(linePart[0].equals("Modify")) {
			if(linePart[1].equals("point")) {
				int shapeModify = model.getShapes().indexOf(selectedShapes.get(0));
				int r = Integer.parseInt(linePart[11]);
				int g = Integer.parseInt(linePart[12]);
				int b = Integer.parseInt(linePart[13]);
				Point p = new Point(Integer.parseInt(linePart[4]),Integer.parseInt(linePart[5]), new Color(r,g,b));
				UpdatePointCmd updateShape = new UpdatePointCmd((Point)model.getShapes().get(shapeModify), p);
				updateShape.execute();
				commands.add(updateShape);
				logger.log("Modify point -> " + p.toString());
				
			}
			else if(linePart[1].equals("line"))
			{
				int shapeModify = model.getShapes().indexOf(selectedShapes.get(0));
				int r = Integer.parseInt(linePart[16]);
				int g = Integer.parseInt(linePart[17]);
				int b = Integer.parseInt(linePart[18]);
				Line l = new Line(new Point(Integer.parseInt(linePart[4]),Integer.parseInt(linePart[5])), new Point(Integer.parseInt(linePart[9]),
						Integer.parseInt(linePart[10])), new Color(r,g,b));
				UpdateLineCmd updateShape = new UpdateLineCmd((Line)model.getShapes().get(shapeModify), l);
				updateShape.execute();
				commands.add(updateShape);
				logger.log("Modify line -> " + l.toString());
			}
			else if(linePart[1].equals("rectangle"))
			{
				int shapeModify = model.getShapes().indexOf(selectedShapes.get(0));
				int r = Integer.parseInt(linePart[21]);
				int g = Integer.parseInt(linePart[22]);
				int b = Integer.parseInt(linePart[23]);
				int ri = Integer.parseInt(linePart[29]);
				int gi = Integer.parseInt(linePart[30]);
				int bi = Integer.parseInt(linePart[31]);
				Rectangle rect = new Rectangle(new Point(Integer.parseInt(linePart[7]), Integer.parseInt(linePart[8])), Integer.parseInt(linePart[12]), 
						Integer.parseInt(linePart[15]), new Color(r,g,b), new Color(ri,gi,bi));
				UpdateRectangleCmd updateShape = new UpdateRectangleCmd((Rectangle)model.getShapes().get(shapeModify), rect);
				updateShape.execute();
				commands.add(updateShape);
				logger.log("Modify rectangle -> " + rect.toString());
			}
			else if(linePart[1].equals("circle"))
			{
				int shapeModify = model.getShapes().indexOf(selectedShapes.get(0));
				int r = Integer.parseInt(linePart[17]);
				int g = Integer.parseInt(linePart[18]);
				int b = Integer.parseInt(linePart[19]);
				int ri = Integer.parseInt(linePart[25]);
				int gi = Integer.parseInt(linePart[26]);
				int bi = Integer.parseInt(linePart[27]);
				Circle c = new Circle(new Point(Integer.parseInt(linePart[6]),Integer.parseInt(linePart[7])), Integer.parseInt(linePart[12])
						,new Color(r,g,b), new Color(ri,gi,bi));
				UpdateCircleCmd updateShape = new UpdateCircleCmd((Circle)model.getShapes().get(shapeModify), c);
				updateShape.execute();
				commands.add(updateShape);
				logger.log("Modify circle -> " + c.toString());
			}
			else if(linePart[1].equals("donut"))
			{
				int shapeModify = model.getShapes().indexOf(selectedShapes.get(0));
				int r = Integer.parseInt(linePart[17]);
				int g = Integer.parseInt(linePart[18]);
				int b = Integer.parseInt(linePart[19]);
				int ri = Integer.parseInt(linePart[25]);
				int gi = Integer.parseInt(linePart[26]);
				int bi = Integer.parseInt(linePart[27]);
				Donut d = new Donut(new Point(Integer.parseInt(linePart[6]), Integer.parseInt(linePart[7])), Integer.parseInt(linePart[12]), Integer.parseInt(linePart[32].trim()),
						new Color(r,g,b), new Color(ri,gi,bi));
				UpdateDonutCmd updateShape = new UpdateDonutCmd((Donut)model.getShapes().get(shapeModify), d);
				updateShape.execute();
				commands.add(updateShape);
				logger.log("Modify donut -> " + d.toString());
			}
			else if(linePart[1].equals("hexagon"))
			{
				int shapeModify = model.getShapes().indexOf(selectedShapes.get(0));
				int r = Integer.parseInt(linePart[16]);
				int g = Integer.parseInt(linePart[17]);
				int b = Integer.parseInt(linePart[18]);
				int ri = Integer.parseInt(linePart[24]);
				int gi = Integer.parseInt(linePart[25]);
				int bi = Integer.parseInt(linePart[26]);
				HexagonAdapter ha = new HexagonAdapter(new Hexagon(Integer.parseInt(linePart[5]), Integer.parseInt(linePart[6]), Integer.parseInt(linePart[11])));
				ha.setColor(new Color(r,g,b));
				ha.setInnerColor(new Color(ri,gi,bi));
				UpdateHexagonCmd updateShape = new UpdateHexagonCmd((HexagonAdapter)model.getShapes().get(shapeModify), ha);
				updateShape.execute();
				commands.add(updateShape);
				logger.log("Modify hexagon -> " + ha.toString());
			}
		}
		
/*-----------------------------------------------DELETE-----------------------------------------------------------*/
		else if(linePart[0].equals("Deleted")) {
			
			if(linePart[1].equals("point"))
			{
				int r = Integer.parseInt(linePart[10]);
				int g = Integer.parseInt(linePart[11]);
				int b = Integer.parseInt(linePart[12]);
				Point p = new Point(Integer.parseInt(linePart[3]),Integer.parseInt(linePart[4]), new Color(r,g,b));
				int index = model.getShapes().indexOf(p);
				deleteInfo.push(model.getShapes().indexOf(model.getShapes().get(index)));
				RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(index),selectedShapes,deleteInfo);
				logger.log("Deleted point " + model.getShapes().get(index).toString());
				removeShape.execute();
				commands.add(removeShape);
			}
			if(linePart[1].equals("line"))
			{
				int r = Integer.parseInt(linePart[15]);
				int g = Integer.parseInt(linePart[16]);
				int b = Integer.parseInt(linePart[17]);
				Line l = new Line(new Point(Integer.parseInt(linePart[3]),Integer.parseInt(linePart[4])), new Point(Integer.parseInt(linePart[8]),
						Integer.parseInt(linePart[9])), new Color(r,g,b));
				int index = model.getShapes().indexOf(l);
				deleteInfo.push(model.getShapes().indexOf(model.getShapes().get(index)));
				RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(index),selectedShapes,deleteInfo);
				logger.log("Deleted line " + model.getShapes().get(index).toString());
				removeShape.execute();
				commands.add(removeShape);
			}
			if(linePart[1].equals("rectangle"))
			{
				int r = Integer.parseInt(linePart[20]);
				int g = Integer.parseInt(linePart[21]);
				int b = Integer.parseInt(linePart[22]);
				int ri = Integer.parseInt(linePart[28]);
				int gi = Integer.parseInt(linePart[29]);
				int bi = Integer.parseInt(linePart[30]);
				Rectangle rect = new Rectangle(new Point(Integer.parseInt(linePart[6]), Integer.parseInt(linePart[7])), Integer.parseInt(linePart[11]), 
						Integer.parseInt(linePart[14]), new Color(r,g,b), new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(rect);
				deleteInfo.push(model.getShapes().indexOf(model.getShapes().get(index)));
				RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(index),selectedShapes,deleteInfo);
				logger.log("Deleted rectangle " + model.getShapes().get(index).toString());
				removeShape.execute();
				commands.add(removeShape);
			}
			if(linePart[1].equals("circle"))
			{
				int r = Integer.parseInt(linePart[16]);
				int g = Integer.parseInt(linePart[17]);
				int b = Integer.parseInt(linePart[18]);
				int ri = Integer.parseInt(linePart[24]);
				int gi = Integer.parseInt(linePart[25]);
				int bi = Integer.parseInt(linePart[26]);
				Circle c = new Circle(new Point(Integer.parseInt(linePart[5]),Integer.parseInt(linePart[6])), Integer.parseInt(linePart[11])
						,new Color(r,g,b), new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(c);
				deleteInfo.push(model.getShapes().indexOf(model.getShapes().get(index)));
				RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(index),selectedShapes,deleteInfo);
				logger.log("Deleted circle " + model.getShapes().get(index).toString());
				removeShape.execute();
				commands.add(removeShape);
			}
			if(linePart[1].equals("donut"))
			{
				int r = Integer.parseInt(linePart[16]);
				int g = Integer.parseInt(linePart[17]);
				int b = Integer.parseInt(linePart[18]);
				int ri = Integer.parseInt(linePart[24]);
				int gi = Integer.parseInt(linePart[25]);
				int bi = Integer.parseInt(linePart[26]);
				Donut d = new Donut(new Point(Integer.parseInt(linePart[5]), Integer.parseInt(linePart[6])), Integer.parseInt(linePart[11]), Integer.parseInt(linePart[31].trim()),
						new Color(r,g,b), new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(d);
				deleteInfo.push(model.getShapes().indexOf(model.getShapes().get(index)));
				RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(index),selectedShapes,deleteInfo);
				logger.log("Deleted donut " + model.getShapes().get(index).toString());
				removeShape.execute();
				commands.add(removeShape);
			}
			if(linePart[1].equals("hexagon"))
			{
				int r = Integer.parseInt(linePart[15]);
				int g = Integer.parseInt(linePart[16]);
				int b = Integer.parseInt(linePart[17]);
				int ri = Integer.parseInt(linePart[23]);
				int gi = Integer.parseInt(linePart[24]);
				int bi = Integer.parseInt(linePart[25]);
				HexagonAdapter ha = new HexagonAdapter(new Hexagon(Integer.parseInt(linePart[4]), Integer.parseInt(linePart[5]), Integer.parseInt(linePart[10])));
				ha.setColor(new Color(r,g,b));
				ha.setInnerColor(new Color(ri,gi,bi));
				int index = model.getShapes().indexOf(ha);
				deleteInfo.push(model.getShapes().indexOf(model.getShapes().get(index)));
				RemoveShapeCmd removeShape = new RemoveShapeCmd(model,model.getShapes().get(index),selectedShapes,deleteInfo);
				logger.log("Deleted hexagon " + model.getShapes().get(index).toString());
				removeShape.execute();
				commands.add(removeShape);
			}
		}
/*------------------------------------TOFRONT, TOBACK, BRINGTOFRONT, BRINGTOBACK----------------------------------*/
		else if(linePart[0].equals("To") && linePart[1].equals("front")) {
			
				int selectedShape = model.getShapes().indexOf(selectedShapes.get(0));
				ToFrontCmd toFront = new ToFrontCmd(model,model.getShapes().get(selectedShape));
				toFront.execute();
				commands.add(toFront);
				frame.repaint();
		}
		else if(linePart[0].equals("To") && linePart[1].equals("back")) {
			
			int selectedShape = model.getShapes().indexOf(selectedShapes.get(0));
			ToBackCmd toBack = new ToBackCmd(model,model.getShapes().get(selectedShape));
			toBack.execute();
			commands.add(toBack);
			frame.repaint();
		}
		else if(linePart[0].equals("Bring") && linePart[1].equals("to") && linePart[2].equals("front"))
		{
			int selectedShape = model.getShapes().indexOf(selectedShapes.get(0));
			BringToFrontCmd bringToFront = new BringToFrontCmd(model,model.getShapes().get(selectedShape));
			
			if (selectedShapes.get(selectedShapes.size() - 1) instanceof Point) {
				logger.log("Bring to front point " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Line) {
				logger.log("Bring to front line " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Donut) {
				logger.log("Bring to front donut " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Circle) {
				logger.log("Bring to front circle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Rectangle) {
				logger.log("Bring to front rectangle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof HexagonAdapter) {
				logger.log("Bring to front hexagon " + selectedShapes.get(selectedShapes.size() - 1));
			}
			
			bringToFront.execute();
			commands.add(bringToFront);
			
		}
		else if(linePart[0].equals("Bring") && linePart[1].equals("to") && linePart[2].equals("back"))
		{
			int selectedShape = model.getShapes().indexOf(selectedShapes.get(0));
			BringToBackCmd bringToBack = new BringToBackCmd(model,model.getShapes().get(selectedShape));
			
			if (selectedShapes.get(selectedShapes.size() - 1) instanceof Point) {
				logger.log("Bring to back point " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Line) {
				logger.log("Bring to back line " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Donut) {
				logger.log("Bring to back donut " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Circle) {
				logger.log("Bring to back circle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof Rectangle) {
				logger.log("Bring to back rectangle " + selectedShapes.get(selectedShapes.size() - 1));
			} else if (selectedShapes.get(selectedShapes.size() - 1) instanceof HexagonAdapter) {
				logger.log("Bring to back hexagon " + selectedShapes.get(selectedShapes.size() - 1));
			}
			
			bringToBack.execute();
			commands.add(bringToBack);
			
		}
/*------------------------------------------UNDO, REDO--------------------------------------------------------------*/
		else if(linePart[0].equals("Undo")){
			
			undo();
		}
		else if(linePart[0].equals("Redo"))
		{
			redo();
		}

		frame.repaint();
		enableAndDisableButtons();
		
		
		lineNumber++;
		
		}
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

	public void setLoadSplit(String[] loadSplit) {
		this.loadSplit = loadSplit;
	}

}
