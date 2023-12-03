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

		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(255, 105, 180));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		JToggleButton tglbtnModify = new JToggleButton("Modify");
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
				controller.delete();
			}
		});
		pnlSouth.add(tglbtnDelete);
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

}
