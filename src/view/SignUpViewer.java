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
	
	private String auth[] =  {"일반회원","관리자"};

	public SignUpPanel() {
		this.setLayout(new BorderLayout(20,20));
		/* 회원가입 상단 부분 시작 */
		this.signUpCenterPanel = new JPanel();
		this.signUpCenterPanel.setLayout(new GridLayout(3,2,0,50));
		
			/* 그리드 패널 시작 */
			CenterAlignTextPanel textId = new CenterAlignTextPanel("아이디");
			this.signUpCenterPanel.add(textId);
			inputId = new CenterAlignFieldPanel(10);
			this.signUpCenterPanel.add(inputId);
			
			CenterAlignTextPanel textPassword = new CenterAlignTextPanel("비밀번호");
			this.signUpCenterPanel.add(textPassword);
			inputPassword = new CenterAlignFieldPanel(10);
			this.signUpCenterPanel.add(inputPassword);
			
			CenterAlignTextPanel textAuth = new CenterAlignTextPanel("권한");
			this.signUpCenterPanel.add(textAuth);
			inputAuth = new JComboBox<String>(auth);
			this.signUpCenterPanel.add(inputAuth);
			/* 그리드 패널 끝 */
		
		add("Center",this.signUpCenterPanel);
		/* 회원가입 상단 부분 끝 */
		
		/* 회원가입 하단 부분 시작 */
		this.signUpBottomPanel = new JPanel();
		this.signUpBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			
			JButton signUp = new JButton("가입하기");
			this.signUpBottomPanel.add(signUp);
			signUp.addActionListener(EventController.getInstance());
			
			JButton signIn= new JButton("가입취소");
			this.signUpBottomPanel.add(signIn);
			signIn.addActionListener(EventController.getInstance());
		
		add("South",this.signUpBottomPanel);
		/* 회원가입 하단 부분 끝 */
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
		setTitle("회원가입");
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
