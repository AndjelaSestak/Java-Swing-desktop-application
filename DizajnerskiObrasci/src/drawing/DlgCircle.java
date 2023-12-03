package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;

public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;
	private JTextField txtRadius;
	private JButton btnBorder;
	private JButton btnInner;
	Color border;
	Color inner;
	public boolean isCOk;
	private Circle circle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setBounds(100, 100, 300, 277);
		setModal(true);
		setTitle("Circle");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 245, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenter = new JLabel("Center");
			lblCenter.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCenter = new GridBagConstraints();
			gbc_lblCenter.anchor = GridBagConstraints.WEST;
			gbc_lblCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenter.gridx = 3;
			gbc_lblCenter.gridy = 1;
			contentPanel.add(lblCenter, gbc_lblCenter);
		}
		{
			JLabel lblCoordinateX = new JLabel("X coordinate:");
			lblCoordinateX.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateX = new GridBagConstraints();
			gbc_lblCoordinateX.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateX.gridx = 2;
			gbc_lblCoordinateX.gridy = 2;
			contentPanel.add(lblCoordinateX, gbc_lblCoordinateX);
		}
		{
			txtCoordinateX = new JTextField();
			GridBagConstraints gbc_txtCoordinateX = new GridBagConstraints();
			gbc_txtCoordinateX.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateX.insets = new Insets(0, 0, 5, 5);
			gbc_txtCoordinateX.gridx = 3;
			gbc_txtCoordinateX.gridy = 2;
			contentPanel.add(txtCoordinateX, gbc_txtCoordinateX);
			txtCoordinateX.setColumns(10);
		}
		{
			JLabel lblCoordinateY = new JLabel("Y coordinate:");
			lblCoordinateY.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateY = new GridBagConstraints();
			gbc_lblCoordinateY.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateY.gridx = 2;
			gbc_lblCoordinateY.gridy = 3;
			contentPanel.add(lblCoordinateY, gbc_lblCoordinateY);
		}
		{
			txtCoordinateY = new JTextField();
			GridBagConstraints gbc_txtCoordinateY = new GridBagConstraints();
			gbc_txtCoordinateY.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateY.insets = new Insets(0, 0, 5, 5);
			gbc_txtCoordinateY.gridx = 3;
			gbc_txtCoordinateY.gridy = 3;
			contentPanel.add(txtCoordinateY, gbc_txtCoordinateY);
			txtCoordinateY.setColumns(10);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.fill = GridBagConstraints.HORIZONTAL;
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 2;
			gbc_glue.gridy = 4;
			contentPanel.add(glue, gbc_glue);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 3;
			gbc_glue.gridy = 5;
			contentPanel.add(glue, gbc_glue);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			lblRadius.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 2;
			gbc_lblRadius.gridy = 6;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.anchor = GridBagConstraints.WEST;
			gbc_txtRadius.insets = new Insets(0, 0, 5, 5);
			gbc_txtRadius.gridx = 3;
			gbc_txtRadius.gridy = 6;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			btnInner = new JButton("Inner color");
			btnInner.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnInner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inner = JColorChooser.showDialog(null, "Choose a inner color!", btnInner.getBackground());
					if (inner != null)
						btnInner.setBackground(inner);
				}
			});

			btnInner.setBackground(Color.LIGHT_GRAY);
			btnInner.setForeground(Color.BLACK);

			GridBagConstraints gbc_btnInner = new GridBagConstraints();
			gbc_btnInner.insets = new Insets(0, 0, 0, 5);
			gbc_btnInner.gridx = 2;
			gbc_btnInner.gridy = 8;
			contentPanel.add(btnInner, gbc_btnInner);
		}
		{
			btnBorder = new JButton("Border color");
			btnBorder.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnBorder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					border = JColorChooser.showDialog(null, "Choose a border color!", btnBorder.getBackground());
					if (border != null)
						btnBorder.setBackground(border);
				}

			});

			btnBorder.setBackground(Color.BLACK);
			btnBorder.setForeground(Color.WHITE);

			GridBagConstraints gbc_btnBorder = new GridBagConstraints();
			gbc_btnBorder.insets = new Insets(0, 0, 0, 5);
			gbc_btnBorder.gridx = 3;
			gbc_btnBorder.gridy = 8;
			contentPanel.add(btnBorder, gbc_btnBorder);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 105, 180));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(255, 245, 238));
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (txtRadius.getText().trim().isEmpty())
								JOptionPane.showMessageDialog(null, "Please enter value in the field!");
							else if ((Integer.parseInt(txtRadius.getText().trim()) <= 0))
								JOptionPane.showMessageDialog(null, "Please, enter value greater than 0!");
							else {
								/*circle=new Circle(new Point(Integer.parseInt(getTxtCoordinateX().getText().toString()),
										Integer.parseInt(getTxtCoordinateY().getText().toString())),Integer.parseInt(getTxtRadius().getText()),
										btnBorder.getBackground(),btnInner.getBackground());*/
								
								setCOk(true);
								
								setVisible(false);
							}

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Please, enter the number!");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(255, 245, 238));
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtCoordinateX() {
		return txtCoordinateX;
	}

	public void setTxtCoordinateX(JTextField txtCoordinateX) {
		this.txtCoordinateX = txtCoordinateX;
	}

	public JTextField getTxtCoordinateY() {
		return txtCoordinateY;
	}

	public void setTxtCoordinateY(JTextField txtCoordinateY) {
		this.txtCoordinateY = txtCoordinateY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public boolean isCOk() {
		return isCOk;
	}

	public void setCOk(boolean isCOk) {
		this.isCOk = isCOk;
	}

	public Color getBorder() {
		return border;
	}

	public void setBorder(Color border) {
		this.border = border;
	}

	public Color getInner() {
		return inner;
	}

	public void setInner(Color inner) {
		this.inner = inner;
	}

	public JButton getBtnBorder() {
		return btnBorder;
	}

	public void setBtnBorder(JButton btnBorder) {
		this.btnBorder = btnBorder;
	}

	public JButton getBtnInner() {
		return btnInner;
	}

	public void setBtnInner(JButton btnInner) {
		this.btnInner = btnInner;
	}

	public void setCircle(Circle circle) {
		txtCoordinateX.setText(" " + circle.getCenter().getX());
		txtCoordinateY.setText(" " + circle.getCenter().getY());
		txtRadius.setText(" " + circle.getRadius());
		btnBorder.setBackground(circle.getColor());
		btnInner.setBackground(circle.getInnerColor());
	}

	public Circle getCircle() {
		return circle;
	}
	
	

}
