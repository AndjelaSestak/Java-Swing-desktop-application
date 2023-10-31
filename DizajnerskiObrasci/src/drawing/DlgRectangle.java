package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;

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

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private JButton btnBorderColor;
	private JButton btnInnerColor;
	public boolean isROk;
	private Rectangle rect;
	private Color borderColor = null;
	private Color innerColor = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setBounds(100, 100, 362, 282);
		setModal(true);
		setTitle("Rectangle");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 245, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 1;
			gbc_glue.gridy = 1;
			contentPanel.add(glue, gbc_glue);
		}
		{
			JLabel lblULP = new JLabel("Upper left point:");
			lblULP.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblULP = new GridBagConstraints();
			gbc_lblULP.anchor = GridBagConstraints.EAST;
			gbc_lblULP.insets = new Insets(0, 0, 5, 5);
			gbc_lblULP.gridx = 2;
			gbc_lblULP.gridy = 1;
			contentPanel.add(lblULP, gbc_lblULP);
		}
		{
			JLabel lblCoordinateX = new JLabel("Coordinate x:");
			lblCoordinateX.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateX = new GridBagConstraints();
			gbc_lblCoordinateX.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateX.gridx = 2;
			gbc_lblCoordinateX.gridy = 3;
			contentPanel.add(lblCoordinateX, gbc_lblCoordinateX);
		}
		{
			txtCoordinateX = new JTextField();
			GridBagConstraints gbc_txtCoordinateX = new GridBagConstraints();
			gbc_txtCoordinateX.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateX.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateX.gridx = 3;
			gbc_txtCoordinateX.gridy = 3;
			contentPanel.add(txtCoordinateX, gbc_txtCoordinateX);
			txtCoordinateX.setColumns(10);
		}
		{
			JLabel lblCoordinateY = new JLabel("Coordinate Y:");
			lblCoordinateY.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblCoordinateY = new GridBagConstraints();
			gbc_lblCoordinateY.anchor = GridBagConstraints.EAST;
			gbc_lblCoordinateY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCoordinateY.gridx = 2;
			gbc_lblCoordinateY.gridy = 4;
			contentPanel.add(lblCoordinateY, gbc_lblCoordinateY);
		}
		{
			txtCoordinateY = new JTextField();
			GridBagConstraints gbc_txtCoordinateY = new GridBagConstraints();
			gbc_txtCoordinateY.anchor = GridBagConstraints.WEST;
			gbc_txtCoordinateY.insets = new Insets(0, 0, 5, 0);
			gbc_txtCoordinateY.gridx = 3;
			gbc_txtCoordinateY.gridy = 4;
			contentPanel.add(txtCoordinateY, gbc_txtCoordinateY);
			txtCoordinateY.setColumns(10);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.insets = new Insets(0, 0, 5, 0);
			gbc_glue.gridx = 3;
			gbc_glue.gridy = 5;
			contentPanel.add(glue, gbc_glue);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.fill = GridBagConstraints.VERTICAL;
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 2;
			gbc_glue.gridy = 6;
			contentPanel.add(glue, gbc_glue);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			lblHeight.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 2;
			gbc_lblHeight.gridy = 7;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.anchor = GridBagConstraints.WEST;
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.gridx = 3;
			gbc_txtHeight.gridy = 7;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			lblWidth.setFont(new Font("Times New Roman", Font.BOLD, 11));
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.anchor = GridBagConstraints.EAST;
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 2;
			gbc_lblWidth.gridy = 8;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.anchor = GridBagConstraints.WEST;
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.gridx = 3;
			gbc_txtWidth.gridy = 8;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			btnInnerColor = new JButton("Inner color:");
			btnInnerColor.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color inner =JColorChooser.showDialog(null, "Pick inner color", btnInnerColor.getBackground());
					if(inner !=null)
					btnInnerColor.setBackground(inner);
					
				}
			});
			
			btnInnerColor.setBackground(Color.LIGHT_GRAY);
			btnInnerColor.setForeground(Color.BLACK);
			
			
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnInnerColor.gridx = 2;
			gbc_btnInnerColor.gridy = 11;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			btnBorderColor = new JButton("Border Color");
			btnBorderColor.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//JColorChooser colorChooser=new JColorChooser();
					Color borderColor=JColorChooser.showDialog(null, "Pick color for border", btnBorderColor.getBackground());
					if(borderColor !=null)
					btnBorderColor.setBackground(borderColor);
				}
			});
			
			btnBorderColor.setBackground(Color.BLACK);
			btnBorderColor.setForeground(Color.WHITE);
			
			
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.gridx = 3;
			gbc_btnBorderColor.gridy = 11;
			contentPanel.add(btnBorderColor, gbc_btnBorderColor);
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
							if(txtHeight.getText().trim().isEmpty() || txtWidth.getText().trim().isEmpty() || txtCoordinateY.getText().trim().isEmpty() || txtCoordinateX.getText().trim().isEmpty())
								JOptionPane.showMessageDialog(null, "Please, enter values!");
							else if(Integer.parseInt(txtHeight.getText().trim())<=0 || (Integer.parseInt(txtWidth.getText().trim())<=0)) {
								JOptionPane.showMessageDialog(null, "Please, enter values greater than 0!");	
							}
							
							else {
							setROk(true);
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

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public boolean isROk() {
		return isROk;
	}

	public void setROk(boolean isROk) {
		this.isROk = isROk;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}
	
	public void setRectangle(Rectangle rect) {
		txtCoordinateX.setText("" + rect.getUpperLeftPoint().getX());
		txtCoordinateY.setText("" + rect.getUpperLeftPoint().getY());
		txtHeight.setText("" + rect.getHeight());
		txtWidth.setText("" + rect.getWidth());
		borderColor = rect.getColor();
		innerColor = rect.getInnerColor();
	}
	

}
