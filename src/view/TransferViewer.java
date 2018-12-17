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
		
		/* 출금 상단 부분 시작 */
		this.TransferTopPanel = new JPanel();
		this.TransferTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
			CenterAlignTextPanel textTitle = new CenterAlignTextPanel("이체 할 금액을 입력해주세요.");
			this.TransferTopPanel.add(textTitle);
		
		add("North",this.TransferTopPanel);
		/* 출금 상단 부분 끝 */
		
		/* 출금 중반 부분 시작 */
		this.TransferCenterPanel = new JPanel();
		this.TransferCenterPanel.setLayout(new GridLayout(2,2,0,0));
		
			CenterAlignTextPanel textTarget = new CenterAlignTextPanel("상대방의 계좌 번호");
			this.TransferCenterPanel.add(textTarget);
			inputTarget = new CenterAlignFieldPanel(10);
			this.TransferCenterPanel.add(inputTarget);
		
			CenterAlignTextPanel textAmount = new CenterAlignTextPanel("이체할 돈");
			this.TransferCenterPanel.add(textAmount);
			inputAmount = new CenterAlignFieldPanel(10);
			this.TransferCenterPanel.add(inputAmount);
		
		add("Center",this.TransferCenterPanel);
		/* 출금 중반 부분 끝 */
		
		/* 출금 하단 부분 시작 */
		this.TransferBottomPanel = new JPanel();
		this.TransferBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			
			JButton transfer = new JButton("이체하기");
			this.TransferBottomPanel.add(transfer);
			transfer.addActionListener(EventController.getInstance());
		
		add("South",this.TransferBottomPanel);
		/* 출금 하단 부분 끝 */
		
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
		setTitle("이체 하기");
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
