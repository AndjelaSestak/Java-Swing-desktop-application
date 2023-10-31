package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

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
import javax.swing.SwingConstants;
import java.awt.Font;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JButton btnBorderColor;
	private Point point;
	public boolean isPOk;
	Color col;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setFont(new Font("Times New Roman", Font.BOLD, 12));
		getContentPane().setBackground(new Color(255, 245, 238));
		setBounds(100, 100, 269, 271);
		setTitle("Point");
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 245, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
			gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
			gbc_horizontalStrut.gridx = 3;
			gbc_horizontalStrut.gridy = 0;
			contentPanel.add(horizontalStrut, gbc_horizontalStrut);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 2;
			gbc_glue.gridy = 2;
			contentPanel.add(glue, gbc_glue);
		}
		{
			JLabel lblXCoordinate = new JLabel("X coordinate:");
			lblXCoordinate.setFont(new Font("Times New Roman", Font.BOLD, 11));
			lblXCoordinate.setBackground(new Color(255, 105, 180));
			GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
			gbc_lblXCoordinate.anchor = GridBagConstraints.EAST;
			gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblXCoordinate.gridx = 3;
			gbc_lblXCoordinate.gridy = 2;
			contentPanel.add(lblXCoordinate, gbc_lblXCoordinate);
		}
		{
			txtXCoordinate = new JTextField();
			GridBagConstraints gbc_txtXCoordinate = new GridBagConstraints();
			gbc_txtXCoordinate.anchor = GridBagConstraints.WEST;
			gbc_txtXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_txtXCoordinate.gridx = 4;
			gbc_txtXCoordinate.gridy = 2;
			contentPanel.add(txtXCoordinate, gbc_txtXCoordinate);
			txtXCoordinate.setColumns(10);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 3;
			gbc_glue.gridy = 3;
			contentPanel.add(glue, gbc_glue);
		}
		{
			Component glue = Box.createGlue();
			GridBagConstraints gbc_glue = new GridBagConstraints();
			gbc_glue.insets = new Insets(0, 0, 5, 5);
			gbc_glue.gridx = 1;
			gbc_glue.gridy = 4;
			contentPanel.add(glue, gbc_glue);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate:");
			lblYCoordinate.setFont(new Font("Times New Roman", Font.BOLD, 11));
			lblYCoordinate.setBackground(new Color(255, 105, 180));
			GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
			gbc_lblYCoordinate.anchor = GridBagConstraints.EAST;
			gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblYCoordinate.gridx = 3;
			gbc_lblYCoordinate.gridy = 4;
			contentPanel.add(lblYCoordinate, gbc_lblYCoordinate);
		}
		{
			btnBorderColor = new JButton("Border color");
			btnBorderColor.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					col = JColorChooser.showDialog(null, "Choose a color!", btnBorderColor.getBackground());

					if (col != null)
						btnBorderColor.setBackground(col);
				}

			});
			{
				txtYCoordinate = new JTextField();
				GridBagConstraints gbc_txtYCoordinate = new GridBagConstraints();
				gbc_txtYCoordinate.anchor = GridBagConstraints.WEST;
				gbc_txtYCoordinate.insets = new Insets(0, 0, 5, 5);
				gbc_txtYCoordinate.gridx = 4;
				gbc_txtYCoordinate.gridy = 4;
				contentPanel.add(txtYCoordinate, gbc_txtYCoordinate);
				txtYCoordinate.setColumns(10);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
				gbc_horizontalStrut.fill = GridBagConstraints.VERTICAL;
				gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
				gbc_horizontalStrut.gridx = 4;
				gbc_horizontalStrut.gridy = 5;
				contentPanel.add(horizontalStrut, gbc_horizontalStrut);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
				gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
				gbc_horizontalStrut.gridx = 4;
				gbc_horizontalStrut.gridy = 6;
				contentPanel.add(horizontalStrut, gbc_horizontalStrut);
			}

			btnBorderColor.setBackground(Color.black);
			btnBorderColor.setForeground(Color.WHITE);
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnBorderColor.gridx = 4;
			gbc_btnBorderColor.gridy = 7;
			contentPanel.add(btnBorderColor, gbc_btnBorderColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 105, 180));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
				okButton.setBackground(new Color(255, 245, 238));
				okButton.setHorizontalAlignment(SwingConstants.RIGHT);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (txtXCoordinate.getText().trim().isEmpty() || txtYCoordinate.getText().trim().isEmpty())
								JOptionPane.showMessageDialog(null, "Please enter the values in the fields!");
							else {
								setPOk(true);
								setVisible(false);

							}

						} catch (NumberFormatException except) {
							JOptionPane.showMessageDialog(null, "Please, enter the numbers in the fields!");
							except.printStackTrace();
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
						setPOk(false);

						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	public JTextField getTxtXCoordinate() {
		return txtXCoordinate;
	}

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
	}

	public boolean isPOk() {
		return isPOk;
	}

	public void setPOk(boolean isPOk) {
		this.isPOk = isPOk;
	}

	public Point getPoint() {
		return point;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

}
