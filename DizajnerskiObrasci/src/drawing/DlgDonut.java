package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private JButton btnDonutBorderColor;
	private JButton btnDonutInnerColor;
	public boolean isDOk;
	Color border;
	Color inner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setBounds(100, 100, 291, 300);
		setModal(true);
		setTitle("Donut");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 245, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenter = new JLabel("Center");
			lblCenter.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCenter = new GridBagConstraints();
			gbc_lblCenter.anchor = GridBagConstraints.WEST;
			gbc_lblCenter.insets = new Insets(0, 0, 5, 0);
			gbc_lblCenter.gridx = 6;
			gbc_lblCenter.gridy = 1;
			contentPanel.add(lblCenter, gbc_lblCenter);
		}
		{
			JLabel lblCoordinateX = new JLabel("X coordinate:");
			lblCoordinateX.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateX = new GridBagConstraints();
			gbc_lblCoordinateX.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateX.gridx = 5;
			gbc_lblCoordinateX.gridy = 2;
			contentPanel.add(lblCoordinateX, gbc_lblCoordinateX);
		}
		{
			txtCoordinateX = new JTextField();
			GridBagConstraints gbc_txtCoordinateX = new GridBagConstraints();
			gbc_txtCoordinateX.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateX.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateX.gridx = 6;
			gbc_txtCoordinateX.gridy = 2;
			contentPanel.add(txtCoordinateX, gbc_txtCoordinateX);
			txtCoordinateX.setColumns(10);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.fill = GridBagConstraints.HORIZONTAL;
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 2;
			gbc_glue.gridy = 3;
			contentPanel.add(glue, gbc_glue);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.fill = GridBagConstraints.HORIZONTAL;
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 4;
			gbc_glue.gridy = 3;
			contentPanel.add(glue, gbc_glue);
		}
		{
			JLabel lblCoordinateY = new JLabel("Y coordinate:");
			lblCoordinateY.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateY = new GridBagConstraints();
			gbc_lblCoordinateY.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateY.gridx = 5;
			gbc_lblCoordinateY.gridy = 3;
			contentPanel.add(lblCoordinateY, gbc_lblCoordinateY);
		}
		{
			txtCoordinateY = new JTextField();
			GridBagConstraints gbc_txtCoordinateY = new GridBagConstraints();
			gbc_txtCoordinateY.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateY.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateY.gridx = 6;
			gbc_txtCoordinateY.gridy = 3;
			contentPanel.add(txtCoordinateY, gbc_txtCoordinateY);
			txtCoordinateY.setColumns(10);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(20);
			GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
			gbc_verticalStrut.fill = GridBagConstraints.HORIZONTAL;
			gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
			gbc_verticalStrut.gridx = 1;
			gbc_verticalStrut.gridy = 4;
			contentPanel.add(verticalStrut, gbc_verticalStrut);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.fill = GridBagConstraints.HORIZONTAL;
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 5;
			gbc_glue.gridy = 5;
			contentPanel.add(glue, gbc_glue);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.fill = GridBagConstraints.HORIZONTAL;
			gbc_glue.insets = new Insets(0, 0, 5, 0);
			gbc_glue.gridx = 6;
			gbc_glue.gridy = 6;
			contentPanel.add(glue, gbc_glue);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			lblRadius.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 5;
			gbc_lblRadius.gridy = 7;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.anchor = GridBagConstraints.WEST;
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.gridx = 6;
			gbc_txtRadius.gridy = 7;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner radius:");
			lblInnerRadius.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 5;
			gbc_lblInnerRadius.gridy = 8;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			txtInnerRadius = new JTextField();
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.anchor = GridBagConstraints.WEST;
			gbc_txtInnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtInnerRadius.gridx = 6;
			gbc_txtInnerRadius.gridy = 8;
			contentPanel.add(txtInnerRadius, gbc_txtInnerRadius);
			txtInnerRadius.setColumns(10);
		}
		{
			btnDonutInnerColor = new JButton("Inner color");
			btnDonutInnerColor.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnDonutInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inner = JColorChooser.showDialog(null, "Choose a inner color!", btnDonutInnerColor.getBackground());
					if (inner != null)
						btnDonutInnerColor.setBackground(inner);
				}

			});

			btnDonutInnerColor.setBackground(Color.LIGHT_GRAY);
			btnDonutInnerColor.setForeground(Color.BLACK);

			GridBagConstraints gbc_btnDonutInnerColor = new GridBagConstraints();
			gbc_btnDonutInnerColor.anchor = GridBagConstraints.EAST;
			gbc_btnDonutInnerColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnDonutInnerColor.gridx = 5;
			gbc_btnDonutInnerColor.gridy = 10;
			contentPanel.add(btnDonutInnerColor, gbc_btnDonutInnerColor);
		}
		{
			btnDonutBorderColor = new JButton("Border color");
			btnDonutBorderColor.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnDonutBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					border = JColorChooser.showDialog(null, "Choose a border color!",
							btnDonutBorderColor.getBackground());
					if (border != null)
						btnDonutBorderColor.setBackground(border);
				}

			});

			btnDonutBorderColor.setBackground(Color.BLACK);
			btnDonutBorderColor.setForeground(Color.WHITE);

			GridBagConstraints gbc_btnDonutBorderColor = new GridBagConstraints();
			gbc_btnDonutBorderColor.anchor = GridBagConstraints.WEST;
			gbc_btnDonutBorderColor.gridx = 6;
			gbc_btnDonutBorderColor.gridy = 10;
			contentPanel.add(btnDonutBorderColor, gbc_btnDonutBorderColor);
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
							if (txtRadius.getText().trim().isEmpty() || txtInnerRadius.getText().trim().isEmpty())
								JOptionPane.showMessageDialog(null, "Please enter value in the field!");
							else if ((Integer.parseInt(txtRadius.getText().trim()) <= 0)
									|| (Integer.parseInt(txtInnerRadius.getText().trim()) <= 0))
								JOptionPane.showMessageDialog(null, "Please, enter value greater than 0!");
							else if ((Integer.parseInt(txtRadius.getText().trim())) < (Integer
									.parseInt(txtInnerRadius.getText().trim())))
								JOptionPane.showMessageDialog(null, "Inner radius mustn't be greater than radius!");
							else {
								setDOk(true);
								setVisible(false);
							}
						} catch (Exception exc) {
							JOptionPane.showMessageDialog(null, "Please, enter the numbers in the fields!");
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
                        setDOk(false);
						
						dispose();
					}
				});
				cancelButton.setBackground(new Color(255, 245, 238));
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
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

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
	}

	public boolean isDOk() {
		return isDOk;
	}

	public void setDOk(boolean isDOk) {
		this.isDOk = isDOk;
	}

	public JButton getBtnDonutBorderColor() {
		return btnDonutBorderColor;
	}

	public void setBtnDonutBorderColor(JButton btnDonutBorderColor) {
		this.btnDonutBorderColor = btnDonutBorderColor;
	}

	public JButton getBtnDonutInnerColor() {
		return btnDonutInnerColor;
	}

	public void setBtnDonutInnerColor(JButton btnDonutInnerColor) {
		this.btnDonutInnerColor = btnDonutInnerColor;
	}

	public void setDonut(Donut donut) {
		txtCoordinateX.setText("" + donut.getCenter().getX());
		txtCoordinateY.setText("" + donut.getCenter().getY());
		txtRadius.setText("" + donut.getRadius());
		txtInnerRadius.setText("" + donut.getInnerRadius());
		btnDonutBorderColor.setBackground(donut.getColor());
		btnDonutInnerColor.setBackground(donut.getInnerColor());
	}

}
