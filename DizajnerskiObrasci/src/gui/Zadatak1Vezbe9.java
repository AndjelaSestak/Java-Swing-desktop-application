package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;

public class Zadatak1Vezbe9 extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroupColor = new ButtonGroup();
	private JLabel lblPlava;
	private JLabel lblZuta;
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private JList<String> listColor;
	private DlgAddModifyColor dlgColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zadatak1Vezbe9 frame = new Zadatak1Vezbe9();
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
	public Zadatak1Vezbe9() {
		setTitle("Zadatak 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{110, 67, 236, 0};
		gbl_pnlCenter.rowHeights = new int[]{35, 0, 0, 131, 0};
		gbl_pnlCenter.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		
		JLabel lblCrvena = new JLabel("Crvena");
		GridBagConstraints gbc_lblCrvena = new GridBagConstraints();
		gbc_lblCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrvena.gridx = 1;
		gbc_lblCrvena.gridy = 0;
		pnlCenter.add(lblCrvena, gbc_lblCrvena);
		
		JToggleButton tglbtnCrvena = new JToggleButton("Crvena");
		tglbtnCrvena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblZuta.setForeground(Color.black);
				lblPlava.setForeground(Color.black);
				lblCrvena.setForeground(Color.green);
				dlm.addElement(lblCrvena.getText());
			}
		});
		buttonGroupColor.add(tglbtnCrvena);
		GridBagConstraints gbc_tglbtnCrvena = new GridBagConstraints();
		gbc_tglbtnCrvena.fill = GridBagConstraints.BOTH;
		gbc_tglbtnCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCrvena.gridx = 0;
		gbc_tglbtnCrvena.gridy = 0;
		pnlCenter.add(tglbtnCrvena, gbc_tglbtnCrvena);
		
		
		JToggleButton tglbtnPlava = new JToggleButton("Plava");
		tglbtnPlava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblZuta.setForeground(Color.black);
				lblCrvena.setForeground(Color.black);
				lblPlava.setForeground(Color.green);
				dlm.addElement(lblPlava.getText());
			}
		});
		buttonGroupColor.add(tglbtnPlava);
		GridBagConstraints gbc_tglbtnPlava = new GridBagConstraints();
		gbc_tglbtnPlava.fill = GridBagConstraints.BOTH;
		gbc_tglbtnPlava.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPlava.gridx = 0;
		gbc_tglbtnPlava.gridy = 1;
		pnlCenter.add(tglbtnPlava, gbc_tglbtnPlava);
		
		lblPlava = new JLabel("Plava");
		GridBagConstraints gbc_lblPlava = new GridBagConstraints();
		gbc_lblPlava.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlava.gridx = 1;
		gbc_lblPlava.gridy = 1;
		pnlCenter.add(lblPlava, gbc_lblPlava);
		
		JButton btnDodajBoju = new JButton("Dodaj boju");
		btnDodajBoju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dlgColor = new DlgAddModifyColor();
				dlgColor.setVisible(true);
				
				if (dlgColor.isOk()) {
					String color = dlgColor.getTxtRed().getText() + " " + dlgColor.getTxtGreen().getText() + " "
							+ dlgColor.getTxtBlue().getText();
					dlm.addElement(color);
					
					Color colorPnlCenter = new Color(Integer.parseInt(dlgColor.getTxtRed().getText()),
							Integer.parseInt(dlgColor.getTxtGreen().getText()),
							Integer.parseInt(dlgColor.getTxtBlue().getText()));
					pnlCenter.setBackground(colorPnlCenter);
				}
			}
		});
		GridBagConstraints gbc_btnDodajBoju = new GridBagConstraints();
		gbc_btnDodajBoju.insets = new Insets(0, 0, 5, 0);
		gbc_btnDodajBoju.gridx = 2;
		gbc_btnDodajBoju.gridy = 1;
		pnlCenter.add(btnDodajBoju, gbc_btnDodajBoju);
		
		lblZuta = new JLabel("Zuta");
		GridBagConstraints gbc_lblZuta = new GridBagConstraints();
		gbc_lblZuta.insets = new Insets(0, 0, 5, 5);
		gbc_lblZuta.gridx = 1;
		gbc_lblZuta.gridy = 2;
		pnlCenter.add(lblZuta, gbc_lblZuta);
		
		JToggleButton tglbtnZuta = new JToggleButton("Zuta");
		tglbtnZuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCrvena.setForeground(Color.black);
				lblPlava.setForeground(Color.black);
				lblZuta.setForeground(Color.green);
				dlm.addElement(lblZuta.getText());
			}
		});
		buttonGroupColor.add(tglbtnZuta);
		GridBagConstraints gbc_tglbtnZuta = new GridBagConstraints();
		gbc_tglbtnZuta.fill = GridBagConstraints.BOTH;
		gbc_tglbtnZuta.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnZuta.gridx = 0;
		gbc_tglbtnZuta.gridy = 2;
		pnlCenter.add(tglbtnZuta, gbc_tglbtnZuta);
		
		JButton btnIzmeniBoju = new JButton("Izmeni boju");
		btnIzmeniBoju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlgColor = new DlgAddModifyColor();
				if(listColor.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Selektuj boju",
							"Poruka", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					int index = listColor.getSelectedIndex();
					String selectedValue = dlm.get(index);
					String[] colorsRGB = selectedValue.split(" ");
					dlgColor.getTxtRed().setText(colorsRGB[0]);
					dlgColor.getTxtGreen().setText(colorsRGB[1]);
					dlgColor.getTxtBlue().setText(colorsRGB[2]);
					dlgColor.setVisible(true);
					
					if (dlgColor.isOk()) {
						dlm.remove(index);
						String color = dlgColor.getTxtRed().getText() + " " + dlgColor.getTxtGreen().getText() + " "
								+ dlgColor.getTxtBlue().getText();
						dlm.add(index, color);
						
						Color colorPnlCenter = new Color(Integer.parseInt(dlgColor.getTxtRed().getText()),
								Integer.parseInt(dlgColor.getTxtGreen().getText()),
								Integer.parseInt(dlgColor.getTxtBlue().getText()));
						pnlCenter.setBackground(colorPnlCenter);
					}
				}
			}
		});
		GridBagConstraints gbc_btnIzmeniBoju = new GridBagConstraints();
		gbc_btnIzmeniBoju.insets = new Insets(0, 0, 5, 0);
		gbc_btnIzmeniBoju.gridx = 2;
		gbc_btnIzmeniBoju.gridy = 2;
		pnlCenter.add(btnIzmeniBoju, gbc_btnIzmeniBoju);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 3;
		pnlCenter.add(scrollPane, gbc_scrollPane);
		
		listColor = new JList<String>();
		listColor.setModel(dlm);
		scrollPane.setViewportView(listColor);
		
		JLabel lblNaslov = new JLabel("Zadatak 1");
		lblNaslov.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNaslov.setForeground(Color.BLUE);
		lblNaslov.setBackground(Color.GREEN);
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNaslov, BorderLayout.NORTH);
		
		JButton btnKlik = new JButton("Klikni me");
		btnKlik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Antistres dugme :)", "Poruka", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(btnKlik, BorderLayout.SOUTH);
	}
}