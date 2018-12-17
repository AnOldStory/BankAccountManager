package view;

import java.awt.*;
import javax.swing.*;

import components.*;
import controller.EventController;

class LoginPanel extends JPanel {
	private JPanel loginTopPanel;
	private JPanel loginCenterPanel;
	private CenterAlignFieldPanel inputId;
	private CenterAlignFieldPanel inputPassword;
	
	private JPanel loginBottomPanel;
	
	public LoginPanel() {
		this.setLayout(new BorderLayout(20,20));
		
		/* 로그인 상단 부분 시작 */
		this.loginTopPanel = new JPanel();
		
			JLabel title = new JLabel("계좌 관리 프로그램");
			this.loginTopPanel.add(title);
		
		add("North",this.loginTopPanel);
		/* 로그인 상단 부분 끝 */
		
		/* 로그인 중반 부분 시작 */
		this.loginCenterPanel = new JPanel();
		this.loginCenterPanel.setLayout(new GridLayout(2,2,0,50));
		
			/* 그리드 패널 시작 */
			CenterAlignTextPanel textId = new CenterAlignTextPanel("아이디");
			this.loginCenterPanel.add(textId);
			inputId = new CenterAlignFieldPanel(10);
			this.loginCenterPanel.add(inputId);
			
			CenterAlignTextPanel textPassword = new CenterAlignTextPanel("비밀번호");
			this.loginCenterPanel.add(textPassword);
			inputPassword = new CenterAlignFieldPanel(10);
			this.loginCenterPanel.add(inputPassword);
			/* 그리드 패널 끝 */
		
		add("Center",this.loginCenterPanel);
		/* 로그인 중반 부분 끝 */
		
		/* 로그인 하단 부분 시작 */
		this.loginBottomPanel = new JPanel();
		this.loginBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
			JButton signUp = new JButton("회원가입");
			this.loginBottomPanel.add(signUp);
			signUp.addActionListener(EventController.getInstance());
			
			JButton signIn= new JButton("로그인");
			this.loginBottomPanel.add(signIn);
			signIn.addActionListener(EventController.getInstance());
		
		add("South",this.loginBottomPanel);
		/* 로그인 하단 부분 끝 */

	}
	
	public String getId() {
		return this.inputId.getText();
	}
	
	public String getPassword() {
		return this.inputPassword.getText();
	}
	
}

public class LoginViewer extends JFrame {
	private static LoginViewer instance = null;
	
	private Container ctPane = null;
	private LoginPanel login = null;
	
	/* Singleton */
	private LoginViewer(){
		//GridLayout layout = new GridLayout(6,3);
		setTitle("로그인");
		setSize(350,350);
		//setBounds(100,100,300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ctPane = this.getContentPane();
		// ctPane.setLayout(new BorderLayout());
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		login = new LoginPanel();
		
		this.ctPane.add(login);

		setVisible(true);
		
	}
	
	public static LoginViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new LoginViewer();
		}
	}
	
	public String getId() {
		return this.login.getId();
	}
	public String getPassword() {
		return this.login.getPassword();
	}
}
