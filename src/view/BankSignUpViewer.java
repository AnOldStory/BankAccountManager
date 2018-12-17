package view;

import java.awt.*;
import javax.swing.*;

import components.*;
import controller.EventController;

class BankSignUp extends JPanel {
	private JPanel bankSignUpTopPanel;
	private JPanel bankSignUpCenterPanel;
	private CenterAlignFieldPanel inputName;
	
	private String type[]= {"�Ϲ� ����","�Աݿ� ����","��ݿ� ����(�⺻�ݾ� 10000��)"};
	private JComboBox<String> inputType;
	
	private JPanel loginBottomPanel;
	
	public BankSignUp() {
		this.setLayout(new BorderLayout(20,20));
		
		/* �α��� ��� �κ� ���� */
		this.bankSignUpTopPanel = new JPanel();
		
			JLabel title = new JLabel("���� �����ϱ�");
			this.bankSignUpTopPanel.add(title);
		
		add("North",this.bankSignUpTopPanel);
		/* �α��� ��� �κ� �� */
		
		/* �α��� �߹� �κ� ���� */
		this.bankSignUpCenterPanel = new JPanel();
		this.bankSignUpCenterPanel.setLayout(new GridLayout(2,2,0,50));
		
			/* �׸��� �г� ���� */
			CenterAlignTextPanel textName = new CenterAlignTextPanel("���� ��Ī");
			this.bankSignUpCenterPanel.add(textName);
			inputName = new CenterAlignFieldPanel(10);
			this.bankSignUpCenterPanel.add(inputName);
			
			CenterAlignTextPanel textType = new CenterAlignTextPanel("���� ����");
			this.bankSignUpCenterPanel.add(textType);
			inputType = new JComboBox<String>(type);
			this.bankSignUpCenterPanel.add(inputType);
			/* �׸��� �г� �� */
		
		add("Center",this.bankSignUpCenterPanel);
		/* �α��� �߹� �κ� �� */
		
		/* �α��� �ϴ� �κ� ���� */
		this.loginBottomPanel = new JPanel();
		this.loginBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
			JButton bankSignUp = new JButton("���¸����");
			this.loginBottomPanel.add(bankSignUp);
			bankSignUp.addActionListener(EventController.getInstance());
			
			JButton banKSignUpCancel= new JButton("�������");
			this.loginBottomPanel.add(banKSignUpCancel);
			banKSignUpCancel.addActionListener(EventController.getInstance());
		
		add("South",this.loginBottomPanel);
		/* �α��� �ϴ� �κ� �� */

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
		setTitle("���� ����");
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
