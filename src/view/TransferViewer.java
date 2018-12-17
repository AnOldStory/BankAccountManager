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

class TransferPanel extends JPanel {
	private JPanel TransferTopPanel;
	private JPanel TransferCenterPanel;
	private JPanel TransferBottomPanel;
	
	private CenterAlignFieldPanel inputTarget;
	private CenterAlignFieldPanel inputAmount;
	
	public TransferPanel() {
		this.setLayout(new BorderLayout(20,20));
		
		/* ��� ��� �κ� ���� */
		this.TransferTopPanel = new JPanel();
		this.TransferTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
			CenterAlignTextPanel textTitle = new CenterAlignTextPanel("��ü �� �ݾ��� �Է����ּ���.");
			this.TransferTopPanel.add(textTitle);
		
		add("North",this.TransferTopPanel);
		/* ��� ��� �κ� �� */
		
		/* ��� �߹� �κ� ���� */
		this.TransferCenterPanel = new JPanel();
		this.TransferCenterPanel.setLayout(new GridLayout(2,2,0,0));
		
			CenterAlignTextPanel textTarget = new CenterAlignTextPanel("������ ���� ��ȣ");
			this.TransferCenterPanel.add(textTarget);
			inputTarget = new CenterAlignFieldPanel(10);
			this.TransferCenterPanel.add(inputTarget);
		
			CenterAlignTextPanel textAmount = new CenterAlignTextPanel("��ü�� ��");
			this.TransferCenterPanel.add(textAmount);
			inputAmount = new CenterAlignFieldPanel(10);
			this.TransferCenterPanel.add(inputAmount);
		
		add("Center",this.TransferCenterPanel);
		/* ��� �߹� �κ� �� */
		
		/* ��� �ϴ� �κ� ���� */
		this.TransferBottomPanel = new JPanel();
		this.TransferBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			
			JButton transfer = new JButton("��ü�ϱ�");
			this.TransferBottomPanel.add(transfer);
			transfer.addActionListener(EventController.getInstance());
		
		add("South",this.TransferBottomPanel);
		/* ��� �ϴ� �κ� �� */
		
	}
	
	public String getId() {
		return this.inputAmount.getText();
	}
	
	public String getTarget() {
		return this.inputTarget.getText();
	}
}

public class TransferViewer extends JFrame {
	private static TransferViewer  instance = null;
	private Container ctPane = null;
	private TransferPanel transfer = null;
	
	/* Singleton */
	private TransferViewer() {
		setTitle("��ü �ϱ�");
		setSize(350,350);
		setLocationRelativeTo(null);
		this.ctPane = this.getContentPane();
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		transfer = new TransferPanel();
		
		this.ctPane.add(transfer);
		
		setVisible(true);
	}
	
	public static TransferViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new TransferViewer();
		}
	}
	
	public String getId() {
		return this.transfer.getId();
	}
	
	public String getTarget() {
		return this.transfer.getTarget();
	}
}
