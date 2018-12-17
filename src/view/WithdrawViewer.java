package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.CenterAlignFieldPanel;
import components.CenterAlignTextPanel;
import controller.EventController;

class WithdrawPanel extends JPanel {
	private JPanel WithdrawTopPanel;
	private JPanel WithdrawCenterPanel;
	private JPanel WithdrawBottomPanel;
	
	private CenterAlignFieldPanel inputAmount;
	
	public WithdrawPanel() {
		this.setLayout(new BorderLayout(20,20));
		
		/* 출금 상단 부분 시작 */
		this.WithdrawTopPanel = new JPanel();
		this.WithdrawTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
			CenterAlignTextPanel textTitle = new CenterAlignTextPanel("출금 할 금액을 입력해주세요.");
			this.WithdrawTopPanel.add(textTitle);
		
		add("North",this.WithdrawTopPanel);
		/* 출금 상단 부분 끝 */
		
		/* 출금 중반 부분 시작 */
		this.WithdrawCenterPanel = new JPanel();
		this.WithdrawCenterPanel.setLayout(new GridLayout(1,2,0,0));
		
			CenterAlignTextPanel textAmount = new CenterAlignTextPanel("출금할 돈");
			this.WithdrawCenterPanel.add(textAmount);
			inputAmount = new CenterAlignFieldPanel(10);
			this.WithdrawCenterPanel.add(inputAmount);
		
		add("Center",this.WithdrawCenterPanel);
		/* 출금 중반 부분 끝 */
		
		/* 출금 하단 부분 시작 */
		this.WithdrawBottomPanel = new JPanel();
		this.WithdrawBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			
			JButton deposit = new JButton("출금하기");
			this.WithdrawBottomPanel.add(deposit);
			deposit.addActionListener(EventController.getInstance());
		
		add("South",this.WithdrawBottomPanel);
		/* 출금 하단 부분 끝 */
		
	}
	
	public String getId() {
		return this.inputAmount.getText();
	}
	
}

public class WithdrawViewer extends JFrame {
	private static WithdrawViewer  instance = null;
	private Container ctPane = null;
	private WithdrawPanel withdraw = null;
	
	/* Singleton */
	private WithdrawViewer() {
		setTitle("출금 하기");
		setSize(350,350);
		setLocationRelativeTo(null);
		this.ctPane = this.getContentPane();
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		withdraw = new WithdrawPanel();
		
		this.ctPane.add(withdraw);
		
		setVisible(true);
	}
	
	public static WithdrawViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new WithdrawViewer();
		}
	}
	
	public String getId() {
		return this.withdraw.getId();
	}
}
