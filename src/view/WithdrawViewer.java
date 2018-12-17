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
		
		/* ��� ��� �κ� ���� */
		this.WithdrawTopPanel = new JPanel();
		this.WithdrawTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
			CenterAlignTextPanel textTitle = new CenterAlignTextPanel("��� �� �ݾ��� �Է����ּ���.");
			this.WithdrawTopPanel.add(textTitle);
		
		add("North",this.WithdrawTopPanel);
		/* ��� ��� �κ� �� */
		
		/* ��� �߹� �κ� ���� */
		this.WithdrawCenterPanel = new JPanel();
		this.WithdrawCenterPanel.setLayout(new GridLayout(1,2,0,0));
		
			CenterAlignTextPanel textAmount = new CenterAlignTextPanel("����� ��");
			this.WithdrawCenterPanel.add(textAmount);
			inputAmount = new CenterAlignFieldPanel(10);
			this.WithdrawCenterPanel.add(inputAmount);
		
		add("Center",this.WithdrawCenterPanel);
		/* ��� �߹� �κ� �� */
		
		/* ��� �ϴ� �κ� ���� */
		this.WithdrawBottomPanel = new JPanel();
		this.WithdrawBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			
			JButton deposit = new JButton("����ϱ�");
			this.WithdrawBottomPanel.add(deposit);
			deposit.addActionListener(EventController.getInstance());
		
		add("South",this.WithdrawBottomPanel);
		/* ��� �ϴ� �κ� �� */
		
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
		setTitle("��� �ϱ�");
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
