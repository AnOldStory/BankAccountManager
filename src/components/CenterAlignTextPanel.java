package components;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenterAlignTextPanel extends JPanel{
	private JLabel text;
	public CenterAlignTextPanel(String input) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		this.text = new JLabel(input);
		add("Center",this.text);
	}
	public void update(String str) {
		text.setText(str);
	}
}