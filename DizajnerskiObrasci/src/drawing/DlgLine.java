package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;

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

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoordinateX2;
	private JTextField txtCoordinateY2;
	private JTextField txtCoordinateX1;
	private JTextField txtCoordinateY1;
	private JButton btnLineColor ;
	private Line line;
	Color color;
	public boolean isLOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 276, 300);
		setModal(true);
		setTitle("Line");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 245, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblStartPoint = new JLabel("Start point:");
			lblStartPoint.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
			gbc_lblStartPoint.anchor = GridBagConstraints.WEST;
			gbc_lblStartPoint.insets = new Insets(0, 0, 5, 0);
			gbc_lblStartPoint.gridx = 4;
			gbc_lblStartPoint.gridy = 0;
			contentPanel.add(lblStartPoint, gbc_lblStartPoint);
		}
		{
			JLabel lblCoordinateX1 = new JLabel("Coordinate X:");
			lblCoordinateX1.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateX1 = new GridBagConstraints();
			gbc_lblCoordinateX1.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateX1.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateX1.gridx = 3;
			gbc_lblCoordinateX1.gridy = 1;
			contentPanel.add(lblCoordinateX1, gbc_lblCoordinateX1);
		}
		{
			txtCoordinateX1 = new JTextField();
			GridBagConstraints gbc_txtCoordinateX1 = new GridBagConstraints();
			gbc_txtCoordinateX1.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateX1.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateX1.gridx = 4;
			gbc_txtCoordinateX1.gridy = 1;
			contentPanel.add(txtCoordinateX1, gbc_txtCoordinateX1);
			txtCoordinateX1.setColumns(10);
		}
		{
			JLabel lblCoordinateY1 = new JLabel("Coordinate Y:");
			lblCoordinateY1.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateY1 = new GridBagConstraints();
			gbc_lblCoordinateY1.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateY1.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateY1.gridx = 3;
			gbc_lblCoordinateY1.gridy = 2;
			contentPanel.add(lblCoordinateY1, gbc_lblCoordinateY1);
		}
		{
			txtCoordinateY1 = new JTextField();
			GridBagConstraints gbc_txtCoordinateY1 = new GridBagConstraints();
			gbc_txtCoordinateY1.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateY1.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateY1.gridx = 4;
			gbc_txtCoordinateY1.gridy = 2;
			contentPanel.add(txtCoordinateY1, gbc_txtCoordinateY1);
			txtCoordinateY1.setColumns(10);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(20);
			GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
			gbc_verticalStrut.fill = GridBagConstraints.HORIZONTAL;
			gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
			gbc_verticalStrut.gridx = 2;
			gbc_verticalStrut.gridy = 3;
			contentPanel.add(verticalStrut, gbc_verticalStrut);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.insets = new Insets(0, 0, 5, 0);
			gbc_glue.gridx = 4;
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
			JLabel lblEndPoint = new JLabel("End point:");
			lblEndPoint.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
			gbc_lblEndPoint.anchor = GridBagConstraints.WEST;
			gbc_lblEndPoint.insets = new Insets(0, 0, 5, 0);
			gbc_lblEndPoint.gridx = 4;
			gbc_lblEndPoint.gridy = 5;
			contentPanel.add(lblEndPoint, gbc_lblEndPoint);
		}
		{
			JLabel lblCoordinateX2 = new JLabel("Coordinate X:");
			lblCoordinateX2.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateX2 = new GridBagConstraints();
			gbc_lblCoordinateX2.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateX2.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateX2.gridx = 3;
			gbc_lblCoordinateX2.gridy = 6;
			contentPanel.add(lblCoordinateX2, gbc_lblCoordinateX2);
		}
		{
			txtCoordinateX2 = new JTextField();
			GridBagConstraints gbc_txtCoordinateX2 = new GridBagConstraints();
			gbc_txtCoordinateX2.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateX2.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateX2.gridx = 4;
			gbc_txtCoordinateX2.gridy = 6;
			contentPanel.add(txtCoordinateX2, gbc_txtCoordinateX2);
			txtCoordinateX2.setColumns(10);
		}
		{
			Component verticalGlue = Box.createVerticalGlue();
			GridBagConstraints gbc_verticalGlue = new GridBagConstraints();
			gbc_verticalGlue.insets = new Insets(0, 0, 5, 5);
			gbc_verticalGlue.gridx = 1;
			gbc_verticalGlue.gridy = 7;
			contentPanel.add(verticalGlue, gbc_verticalGlue);
		}
		{
			JLabel lblCoordinateY2 = new JLabel("Coordinate Y:");
			lblCoordinateY2.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateY2 = new GridBagConstraints();
			gbc_lblCoordinateY2.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateY2.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateY2.gridx = 3;
			gbc_lblCoordinateY2.gridy = 7;
			contentPanel.add(lblCoordinateY2, gbc_lblCoordinateY2);
		}
		{
			txtCoordinateY2 = new JTextField();
			GridBagConstraints gbc_txtCoordinateY2 = new GridBagConstraints();
			gbc_txtCoordinateY2.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateY2.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateY2.gridx = 4;
			gbc_txtCoordinateY2.gridy = 7;
			contentPanel.add(txtCoordinateY2, gbc_txtCoordinateY2);
			txtCoordinateY2.setColumns(10);
		}
		{
			btnLineColor = new JButton("Color");
			btnLineColor.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnLineColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color=JColorChooser.showDialog(null, "Choose a color!", btnLineColor.getBackground());
					
					if(color !=null) {
						btnLineColor.setBackground(color);
					}
				}
			});
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
				gbc_horizontalStrut.fill = GridBagConstraints.VERTICAL;
				gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
				gbc_horizontalStrut.gridx = 4;
				gbc_horizontalStrut.gridy = 8;
				contentPanel.add(horizontalStrut, gbc_horizontalStrut);
			}
			GridBagConstraints gbc_btnLineColor = new GridBagConstraints();
			gbc_btnLineColor.anchor = GridBagConstraints.WEST;
			gbc_btnLineColor.gridx = 4;
			gbc_btnLineColor.gridy = 9;
			contentPanel.add(btnLineColor, gbc_btnLineColor);
		}
		
		btnLineColor.setBackground(Color.black);
		btnLineColor.setForeground(Color.WHITE);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 105, 180));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
				okButton.setBackground(new Color(255, 245, 238));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 try {
							 if(txtCoordinateX1.getText().trim().isEmpty() || txtCoordinateX2.getText().trim().isEmpty() || txtCoordinateY1.getText().trim().isEmpty() || txtCoordinateY2.getText().trim().isEmpty()) {
								 JOptionPane.showMessageDialog(null, "Please, enter values in the fields!");
							 }
							 else {
								 setLOk(true);
								 setVisible(false);
							 }
							 
						 }catch(NumberFormatException me) {
								
								JOptionPane.showMessageDialog(null, "Please enter numbers in the fields!");
							}
						 
					}
					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
				cancelButton.setBackground(new Color(255, 245, 238));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
                        setLOk(false);
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtCoordinateX2() {
		return txtCoordinateX2;
	}

	public void setTxtCoordinateX2(JTextField txtCoordinateX2) {
		this.txtCoordinateX2 = txtCoordinateX2;
	}

	public JTextField getTxtCoordinateY2() {
		return txtCoordinateY2;
	}

	public void setTxtCoordinateY2(JTextField txtCoordinateY2) {
		this.txtCoordinateY2 = txtCoordinateY2;
	}

	public JTextField getTxtCoordinateX1() {
		return txtCoordinateX1;
	}

	public void setTxtCoordinateX1(JTextField txtCoordinateX1) {
		this.txtCoordinateX1 = txtCoordinateX1;
	}

	public JTextField getTxtCoordinateY1() {
		return txtCoordinateY1;
	}

	public void setTxtCoordinateY1(JTextField txtCoordinateY1) {
		this.txtCoordinateY1 = txtCoordinateY1;
	}

	public boolean isLOk() {
		return isLOk;
	}

	public void setLOk(boolean isLOk) {
		this.isLOk = isLOk;
	}

	public JButton getBtnLineColor() {
		return btnLineColor;
	}

	public void setBtnLineColor(JButton btnLineColor) {
		this.btnLineColor = btnLineColor;
	}

	public Line getLine() {
		return line;
	}

}
