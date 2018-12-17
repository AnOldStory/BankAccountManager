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

class DepositPanel extends JPanel {
	private JPanel depositTopPanel;
	private JPanel depositCenterPanel;
	private JPanel depositBottomPanel;
	
	private CenterAlignFieldPanel inputAmount;
	
	public DepositPanel() {
		this.setLayout(new BorderLayout(20,20));
		
		/* �Ա� ��� �κ� ���� */
		this.depositTopPanel = new JPanel();
		this.depositTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
			CenterAlignTextPanel textTitle = new CenterAlignTextPanel("�Ա� �� �ݾ��� �Է����ּ���.");
			this.depositTopPanel.add(textTitle);
		
		add("North",this.depositTopPanel);
		/* �Ա� ��� �κ� �� */
		
		/* �Ա� �߹� �κ� ���� */
		this.depositCenterPanel = new JPanel();
		this.depositCenterPanel.setLayout(new GridLayout(1,2,0,0));
		
			CenterAlignTextPanel textAmount = new CenterAlignTextPanel("�Ա��� ��");
			this.depositCenterPanel.add(textAmount);
			inputAmount = new CenterAlignFieldPanel(10);
			this.depositCenterPanel.add(inputAmount);
		
		add("Center",this.depositCenterPanel);
		/* �Ա� �߹� �κ� �� */
		
		/* �Ա� �ϴ� �κ� ���� */
		this.depositBottomPanel = new JPanel();
		this.depositBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			
			JButton deposit = new JButton("�Ա��ϱ�");
			this.depositBottomPanel.add(deposit);
			deposit.addActionListener(EventController.getInstance());
		
		add("South",this.depositBottomPanel);
		/* �Ա� �ϴ� �κ� �� */
		
	}
	
	public String getId() {
		return this.inputAmount.getText();
	}
	
}

public class DepositViewer extends JFrame {
	private static DepositViewer instance = null;
	private Container ctPane = null;
	private DepositPanel deposit = null;
	
	/* Singleton */
	private DepositViewer() {
		setTitle("�Ա� �ϱ�");
		setSize(350,350);
		setLocationRelativeTo(null);
		this.ctPane = this.getContentPane();
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		deposit = new DepositPanel();
		
		this.ctPane.add(deposit);
		
		setVisible(true);
	}
	
	public static DepositViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new DepositViewer();
		}
	}
	
	public String getId() {
		return this.deposit.getId();
	}
}
