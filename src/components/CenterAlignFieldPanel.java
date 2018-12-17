package components;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CenterAlignFieldPanel extends JPanel{
	private JTextField field;
	public CenterAlignFieldPanel(int size) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		this.field = new JTextField(size);
		add("Center",this.field);
	}
	
	public String getText() {
		return this.field.getText().trim();
	}
}
