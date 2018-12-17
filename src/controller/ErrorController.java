package controller;

import javax.swing.JOptionPane;

public class ErrorController {
	private static ErrorController instance = null;
	/* Singleton */
	private ErrorController() {}
	
	public static ErrorController getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new ErrorController();
		}
	}
	
	public void alert(String error) {
		JOptionPane.showMessageDialog(null,error);
	}
}
