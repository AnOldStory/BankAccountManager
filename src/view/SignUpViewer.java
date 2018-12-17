package view;

import java.awt.*;
import javax.swing.*;

import components.*;
import controller.EventController;

class SignUpPanel extends JPanel {
	private JPanel signUpCenterPanel;
	private CenterAlignFieldPanel inputId;
	private CenterAlignFieldPanel inputPassword;
	private JComboBox<String> inputAuth;
	private JPanel signUpBottomPanel;
	
	private String auth[] =  {"�Ϲ�ȸ��","������"};

	public SignUpPanel() {
		this.setLayout(new BorderLayout(20,20));
		/* ȸ������ ��� �κ� ���� */
		this.signUpCenterPanel = new JPanel();
		this.signUpCenterPanel.setLayout(new GridLayout(3,2,0,50));
		
			/* �׸��� �г� ���� */
			CenterAlignTextPanel textId = new CenterAlignTextPanel("���̵�");
			this.signUpCenterPanel.add(textId);
			inputId = new CenterAlignFieldPanel(10);
			this.signUpCenterPanel.add(inputId);
			
			CenterAlignTextPanel textPassword = new CenterAlignTextPanel("��й�ȣ");
			this.signUpCenterPanel.add(textPassword);
			inputPassword = new CenterAlignFieldPanel(10);
			this.signUpCenterPanel.add(inputPassword);
			
			CenterAlignTextPanel textAuth = new CenterAlignTextPanel("����");
			this.signUpCenterPanel.add(textAuth);
			inputAuth = new JComboBox<String>(auth);
			this.signUpCenterPanel.add(inputAuth);
			/* �׸��� �г� �� */
		
		add("Center",this.signUpCenterPanel);
		/* ȸ������ ��� �κ� �� */
		
		/* ȸ������ �ϴ� �κ� ���� */
		this.signUpBottomPanel = new JPanel();
		this.signUpBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			
			JButton signUp = new JButton("�����ϱ�");
			this.signUpBottomPanel.add(signUp);
			signUp.addActionListener(EventController.getInstance());
			
			JButton signIn= new JButton("�������");
			this.signUpBottomPanel.add(signIn);
			signIn.addActionListener(EventController.getInstance());
		
		add("South",this.signUpBottomPanel);
		/* ȸ������ �ϴ� �κ� �� */
	}
	
	public String getId() {
		return this.inputId.getText();
	}
	
	public String getPassword() {
		return this.inputPassword.getText();
	}
	
	public String getAuth() {
		return this.inputAuth.getSelectedItem().toString();
	}

}

public class SignUpViewer extends JFrame {
	private static SignUpViewer instance = null;
	
	private Container ctPane = null;
	private SignUpPanel signUp = null;
	
	/* Singleton */
	private SignUpViewer() {
		setTitle("ȸ������");
		setSize(350,350);
		setLocationRelativeTo(null);
		this.ctPane = this.getContentPane();
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		signUp = new SignUpPanel();
		
		this.ctPane.add(signUp);
		
		setVisible(true);
	}
	
	public static SignUpViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new SignUpViewer();
		}
	}
	
	public String getId() {
		return this.signUp.getId();
	}
	
	public String getPassword() {
		return this.signUp.getPassword();
	}
	
	public String getAuth() {
		return this.signUp.getAuth();
	}
}
