package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JColorChooser;

public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Point startPoint;
	private JToggleButton tglbtnSelect;
	private JButton btnInner ;
	private JButton btnBorder;
	private Color borderColor;
	private Color innerColor;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setFont(new Font("Times New Roman", Font.PLAIN, 12));
		setBackground(new Color(255, 228, 225));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("IT 4/2021 Sestak Andjela");
		PnlDrawing pnlDrawing = new PnlDrawing();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(pnlDrawing, BorderLayout.CENTER);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(255, 105, 180));
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		JToggleButton tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.setBackground(new Color(255, 245, 238));
		tglbtnPoint.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnPoint);
		pnlNorth.add(tglbtnPoint);

		JToggleButton tglbtnLine = new JToggleButton("Line");
		tglbtnLine.setBackground(new Color(255, 245, 238));
		tglbtnLine.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnLine);
		pnlNorth.add(tglbtnLine);

		JToggleButton tglbtnRect = new JToggleButton("Rectangle");
		tglbtnRect.setBackground(new Color(255, 245, 238));
		tglbtnRect.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnRect);
		pnlNorth.add(tglbtnRect);

		JToggleButton tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.setBackground(new Color(255, 245, 238));
		tglbtnCircle.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnCircle);
		pnlNorth.add(tglbtnCircle);

		JToggleButton tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.setBackground(new Color(255, 245, 238));
		tglbtnDonut.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnDonut);
		pnlNorth.add(tglbtnDonut);

		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(255, 105, 180));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		JToggleButton tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setBackground(new Color(255, 245, 238));
		tglbtnModify.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnModify);
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (pnlDrawing.getSelectedShape() != null) {
					pnlDrawing.modify();
					pnlDrawing.getSelectedShape().setSelected(false);
					repaint();

				}

				else {
					JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error",
							JOptionPane.ERROR_MESSAGE);
					tglbtnSelect.setSelected(true);
				}

				pnlDrawing.setSelectedShape(null);
				tglbtnSelect.setSelected(false);

			}
		});
		
		btnInner = new JButton("Inner");
		btnInner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Select a color", innerColor);
				btnInner.setBackground(borderColor);
			}
		});
		pnlSouth.add(btnInner);
		
		btnBorder = new JButton("Border");
		btnBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor = JColorChooser.showDialog(null, "Select a color", borderColor);
				btnBorder.setBackground(borderColor);
			}
		});
		pnlSouth.add(btnBorder);
		pnlSouth.add(tglbtnModify);

		tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPoint = null;
			}
		});
		tglbtnSelect.setBackground(new Color(255, 245, 238));
		tglbtnSelect.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnSelect);
		pnlSouth.add(tglbtnSelect);

		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setBackground(new Color(255, 245, 238));
		tglbtnDelete.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnDelete);
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.delete();
			}
		});
		pnlSouth.add(tglbtnDelete);
		
		
//////////KLIK MISA NA PANEL
		pnlDrawing.setBackground(Color.white);
		pnlDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Shape newShape;
				Point clickPoint = new Point(e.getX(), e.getY());

				if (tglbtnPoint.isSelected()) {
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.getTxtXCoordinate().setText(Integer.toString(e.getX()));
					dlgPoint.getTxtXCoordinate().setEditable(false);
					dlgPoint.getTxtYCoordinate().setText(Integer.toString(e.getY()));
					dlgPoint.getTxtYCoordinate().setEditable(false);
					dlgPoint.setVisible(true);
					Color pointBorder = dlgPoint.getBtnBorderColor().getBackground();
					if (dlgPoint.isPOk) {
						newShape = new Point(e.getX(), e.getY(), pointBorder);
						pnlDrawing.addToShapes(newShape);
					}
					startPoint = null;

				}

				if (tglbtnLine.isSelected()) {

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
						dlgLine.setVisible(true);
						Color lineColor = dlgLine.getBtnLineColor().getBackground();
						if (dlgLine.isLOk) {
							newShape = new Line(startPoint, new Point(e.getX(), e.getY()), lineColor);
							pnlDrawing.addToShapes(newShape);
						}
						startPoint = null;
					}
				}

				if (tglbtnCircle.isSelected()) {
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.getTxtCoordinateX().setText(Integer.toString(e.getX()));
					dlgCircle.getTxtCoordinateY().setText(Integer.toString(e.getY()));
					dlgCircle.getTxtCoordinateX().setEditable(false);
					dlgCircle.getTxtCoordinateY().setEditable(false);
					dlgCircle.setVisible(true);

					if (dlgCircle.isCOk()) {
						newShape = new Circle(new Point(e.getX(), e.getY()),
								(Integer.parseInt(dlgCircle.getTxtRadius().getText())),
								dlgCircle.getBtnBorder().getBackground(), dlgCircle.getBtnInner().getBackground());
						pnlDrawing.addToShapes(newShape);
						startPoint = null;
					}
				}

				if (tglbtnRect.isSelected()) {
					DlgRectangle dlgRect = new DlgRectangle();
					dlgRect.getTxtCoordinateX().setText(Integer.toString(e.getX()));
					dlgRect.getTxtCoordinateX().setEditable(false);
					dlgRect.getTxtCoordinateY().setText(Integer.toString(e.getY()));
					dlgRect.getTxtCoordinateY().setEditable(false);
					dlgRect.setVisible(true);

					if (dlgRect.isROk()) {
						int height = Integer.parseInt(dlgRect.getTxtHeight().getText());
						int width = Integer.parseInt(dlgRect.getTxtWidth().getText());
						Color innerColor = dlgRect.getBtnInnerColor().getBackground();
						Color borderColor = dlgRect.getBtnBorderColor().getBackground();
						newShape = new Rectangle(new Point(e.getX(), e.getY()), width, height, borderColor, innerColor);
						pnlDrawing.addToShapes(newShape);
						startPoint = null;
					}

				}

				if (tglbtnDonut.isSelected()) {
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.getTxtCoordinateX().setText(Integer.toString(e.getX()));
					dlgDonut.getTxtCoordinateX().setEditable(false);
					dlgDonut.getTxtCoordinateY().setText(Integer.toString(e.getY()));
					dlgDonut.getTxtCoordinateY().setEditable(false);
					dlgDonut.setVisible(true);

					if (dlgDonut.isDOk()) {
						int radius = (Integer.parseInt(dlgDonut.getTxtRadius().getText()));
						int innerRadius = (Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()));

						newShape = new Donut(new Point(e.getX(), e.getY()), radius, innerRadius,
								dlgDonut.getBtnDonutBorderColor().getBackground(),
								dlgDonut.getBtnDonutInnerColor().getBackground());
						pnlDrawing.addToShapes(newShape);
						startPoint = null;
					}
				}

				if (tglbtnSelect.isSelected()) {
					Point point = new Point(e.getX(), e.getY());
					pnlDrawing.selectShape(point.getX(), point.getY());
				}

			}
		});

	}
	
	public JButton getBtnInner() {
		return btnInner;
	}

	public JButton getBtnBorder() {
		return btnBorder;
	}
}
