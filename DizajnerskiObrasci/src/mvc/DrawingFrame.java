package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import drawing.PnlDrawing;
import geometry.Point;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.Action;

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
	private JPanel panelWest;
	private JTextArea textArea_3;
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnNext;
	private JPanel panel;
	private final Action action = new SwingAction();

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
		tglbtnHexagon.setBackground(new Color(255, 245, 238));
		tglbtnHexagon.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnHexagon);
		pnlNorth.add(tglbtnHexagon);

		btnSave = new JButton("Save");
		btnSave.setBackground(new Color(218, 112, 214));
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				int choice = file.showSaveDialog(file);
				if (choice == JFileChooser.APPROVE_OPTION) {
					controller.saveData(file.getSelectedFile().getPath());
				}
			}
		});
		pnlNorth.add(btnSave);

		btnLoad = new JButton("Load");
		btnLoad.setBackground(new Color(218, 112, 214));
		btnLoad.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				int choice = file.showSaveDialog(file);
				if (choice == JFileChooser.APPROVE_OPTION) {
					controller.loadData(file.getSelectedFile().getPath());
				}

			}
		});
		btnLoad.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlNorth.add(btnLoad);

		btnNext = new JButton("Next");
		btnNext.setBackground(new Color(218, 112, 214));
		btnNext.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNext.setVisible(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.next();
			}
		});
		pnlNorth.add(btnNext);

		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(255, 105, 180));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		

		tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setEnabled(false);
		tglbtnModify.setBackground(new Color(204, 51, 204));
		tglbtnModify.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnModify);
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});

		btnInner = new JButton("Inner");
		btnInner.setBackground(Color.white);
		btnInner.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnInner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Select a color", innerColor);
				btnInner.setBackground(innerColor);
			}
		});

		btnToBack = new JButton("To back");
		btnToBack.setBackground(new Color(255, 245, 238));
		btnToBack.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});

		btnBringToBack = new JButton("Bring to back");
		btnBringToBack.setBackground(new Color(255, 245, 238));
		btnBringToBack.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});

		btnUndo = new JButton("Undo");
		btnUndo.setBackground(new Color(255, 20, 147));
		btnUndo.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnUndo.setEnabled(false);
		btnUndo.setHorizontalAlignment(SwingConstants.RIGHT);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});

		btnRedo = new JButton("Redo");
		btnRedo.setBackground(new Color(255, 20, 147));
		btnRedo.setFont(new Font("Times New Roman", Font.BOLD, 11));
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
		btnBringToFront.setBackground(new Color(255, 245, 238));
		btnBringToFront.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		pnlSouth.add(btnBringToFront);
		pnlSouth.add(btnToBack);

		btnToFront = new JButton("To front");
		btnToFront.setBackground(new Color(255, 245, 238));
		btnToFront.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		pnlSouth.add(btnToFront);
		pnlSouth.add(btnInner);

		btnBorder = new JButton("Border");
		btnBorder.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnBorder.setBackground(Color.BLACK);
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
		tglbtnSelect.setBackground(new Color(204, 51, 204));
		tglbtnSelect.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnSelect);
		pnlSouth.add(tglbtnSelect);

		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setEnabled(false);
		tglbtnDelete.setBackground(new Color(204, 51, 204));
		tglbtnDelete.setFont(new Font("Times New Roman", Font.BOLD, 11));
		buttonGroup.add(tglbtnDelete);
		tglbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		pnlSouth.add(tglbtnDelete);

		textArea_3 = new JTextArea(10, 80);
		pnlSouth.add(textArea_3);
		textArea_3.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane scroll = new JScrollPane(textArea_3,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlSouth.add(scroll);
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		

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

	public JTextArea getTextArea_3() {
		return textArea_3;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
