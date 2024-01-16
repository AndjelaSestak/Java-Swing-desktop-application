package strategy;

import javax.swing.JTextArea;

public class Logger {

	private String text= "";
	private JTextArea textArea;
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	public Logger(JTextArea textArea) {
		this.textArea=textArea;
		
	}
	
	public void log(String input) {
		text += input + "\n";
		textArea.setText(text);
	}
}
