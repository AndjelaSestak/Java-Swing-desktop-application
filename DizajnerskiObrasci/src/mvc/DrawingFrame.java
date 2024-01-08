package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import drawing.PnlDrawing;
import geometry.Point;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class DrawingFrame extends JFrame {
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Point startPoint;
	private JToggleButton tglbtnSelect;

	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnRect;
	private JToggleButton tglbtnDonut;

	private JButton btnInner;
	private JButton btnBorder;
	private Color borderColor;
	private Color innerColor;

	public DrawingView view = new DrawingView();
	public DrawingController controller;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnUndo;
	private JButton btnRedo;
	private JToggleButton tglbtnDelete;
	private JToggleButton tglbtnModify; 
	private JToggleButton tglbtnHexagon;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JPanel panelWest;
	private JTextArea textArea_2;
	private JTextArea textArea_3;

	public DrawingFrame() {

		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});

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

		contentPane.add(view, BorderLayout.CENTER);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(255, 105, 180));
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.setBackground(new Color(255, 245, 238));
		tglbtnPoint.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnPoint);
		pnlNorth.add(tglbtnPoint);

		tglbtnLine = new JToggleButton("Line");
		tglbtnLine.setBackground(new Color(255, 245, 238));
		tglbtnLine.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnLine);
		pnlNorth.add(tglbtnLine);

		tglbtnRect = new JToggleButton("Rectangle");
		tglbtnRect.setBackground(new Color(255, 245, 238));
		tglbtnRect.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnRect);
		pnlNorth.add(tglbtnRect);

		tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.setBackground(new Color(255, 245, 238));
		tglbtnCircle.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnCircle);
		pnlNorth.add(tglbtnCircle);

		tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.setBackground(new Color(255, 245, 238));
		tglbtnDonut.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnDonut);
		pnlNorth.add(tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		buttonGroup.add(tglbtnHexagon);
		pnlNorth.add(tglbtnHexagon);

		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(255, 105, 180));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setEnabled(false);
		tglbtnModify.setBackground(new Color(255, 245, 238));
		tglbtnModify.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnModify);
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});

		btnInner = new JButton("Inner");
		btnInner.setBackground(Color.white);
		btnInner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Select a color", innerColor);
				btnInner.setBackground(innerColor);
			}
		});
		
		btnToBack = new JButton("To back");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		
		btnBringToBack = new JButton("Bring to back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.setHorizontalAlignment(SwingConstants.RIGHT);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		pnlSouth.add(btnRedo);
		pnlSouth.add(btnUndo);
		pnlSouth.add(btnBringToBack);
		
		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		pnlSouth.add(btnBringToFront);
		pnlSouth.add(btnToBack);
		
		btnToFront = new JButton("To front");
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		pnlSouth.add(btnToFront);
		pnlSouth.add(btnInner);

		btnBorder = new JButton("Border");
		btnBorder.setBackground(Color.black);
		btnBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor = JColorChooser.showDialog(null, "Select a color", borderColor);
				btnBorder.setBackground(borderColor);
			}
		});
		pnlSouth.add(btnBorder);
		pnlSouth.add(tglbtnModify);

		tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.setEnabled(false);
		tglbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPoint = null;
			}
		});
		tglbtnSelect.setBackground(new Color(255, 245, 238));
		tglbtnSelect.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnSelect);
		pnlSouth.add(tglbtnSelect);

		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setEnabled(false);
		tglbtnDelete.setBackground(new Color(255, 245, 238));
		tglbtnDelete.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnDelete);
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		pnlSouth.add(tglbtnDelete);
		
		textArea_3 = new JTextArea(10,60);
		pnlSouth.add(textArea_3);
		
		pnlDrawing.setBackground(Color.white);
	}

	public JButton getBtnInner() {
		return btnInner;
	}

	public JButton getBtnBorder() {
		return btnBorder;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public JToggleButton getTglbtnRect() {
		return tglbtnRect;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JToggleButton getTglbtnDelete() {
		return tglbtnDelete;
	}

	public JToggleButton getTglbtnModify() {
		return tglbtnModify;
	}
	
	

}
