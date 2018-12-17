package view;

import java.awt.*;
import javax.swing.*;

import components.*;
import controller.EventController;

class BankSignUp extends JPanel {
	private JPanel bankSignUpTopPanel;
	private JPanel bankSignUpCenterPanel;
	private CenterAlignFieldPanel inputName;
	
	private String type[]= {"일반 통장","입금용 통장","출금용 통장(기본금액 10000원)"};
	private JComboBox<String> inputType;
	
	private JPanel loginBottomPanel;
	
	public BankSignUp() {
		this.setLayout(new BorderLayout(20,20));
		
		/* 로그인 상단 부분 시작 */
		this.bankSignUpTopPanel = new JPanel();
		
			JLabel title = new JLabel("계좌 생성하기");
			this.bankSignUpTopPanel.add(title);
		
		add("North",this.bankSignUpTopPanel);
		/* 로그인 상단 부분 끝 */
		
		/* 로그인 중반 부분 시작 */
		this.bankSignUpCenterPanel = new JPanel();
		this.bankSignUpCenterPanel.setLayout(new GridLayout(2,2,0,50));
		
			/* 그리드 패널 시작 */
			CenterAlignTextPanel textName = new CenterAlignTextPanel("계좌 별칭");
			this.bankSignUpCenterPanel.add(textName);
			inputName = new CenterAlignFieldPanel(10);
			this.bankSignUpCenterPanel.add(inputName);
			
			CenterAlignTextPanel textType = new CenterAlignTextPanel("계좌 종류");
			this.bankSignUpCenterPanel.add(textType);
			inputType = new JComboBox<String>(type);
			this.bankSignUpCenterPanel.add(inputType);
			/* 그리드 패널 끝 */
		
		add("Center",this.bankSignUpCenterPanel);
		/* 로그인 중반 부분 끝 */
		
		/* 로그인 하단 부분 시작 */
		this.loginBottomPanel = new JPanel();
		this.loginBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
			JButton bankSignUp = new JButton("계좌만들기");
			this.loginBottomPanel.add(bankSignUp);
			bankSignUp.addActionListener(EventController.getInstance());
			
			JButton banKSignUpCancel= new JButton("생성취소");
			this.loginBottomPanel.add(banKSignUpCancel);
			banKSignUpCancel.addActionListener(EventController.getInstance());
		
		add("South",this.loginBottomPanel);
		/* 로그인 하단 부분 끝 */

	}
	
	public String getId() {
		return this.inputName.getText();
	}
	
	public String getPassword() {
		return this.inputType.getSelectedItem().toString();
	}
	
}

public class BankSignUpViewer extends JFrame {
	private static BankSignUpViewer instance = null;
	
	private Container ctPane = null;
	private BankSignUp bankSignUp = null;
	
	/* Singleton */
	private BankSignUpViewer(){
		//GridLayout layout = new GridLayout(6,3);
		setTitle("계좌 생성");
		setSize(350,350);
		//setBounds(100,100,300,200);
		setLocationRelativeTo(null);
		this.ctPane = this.getContentPane();
		// ctPane.setLayout(new BorderLayout());
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		bankSignUp = new BankSignUp();
		
		this.ctPane.add(bankSignUp);

		setVisible(true);
		
	}
	
	public static BankSignUpViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new BankSignUpViewer();
		}
	}
	
	public String getId() {
		return this.bankSignUp.getId();
	}
	public String getPassword() {
		return this.bankSignUp.getPassword();
	}
}
