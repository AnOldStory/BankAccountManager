package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import components.CenterAlignTextPanel;
import controller.EventController;
import controller.MainController;
import model.BankOrm;

class BankDetailPanel extends JPanel {
	private JPanel bankDetailTopPanel;
	private JPanel bankDetailBottomPanel;
	private CenterAlignTextPanel textId;
	private JList <String> historyList;
	
	public BankDetailPanel() {
		this.setLayout(new BorderLayout(0,0));
		
		/* 계좌상세조회 상단 부분 시작 */
		this.bankDetailTopPanel = new JPanel();
		this.bankDetailTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
			this.textId = new CenterAlignTextPanel(
					MainController.getInstance().getBankName()+" ("+
					MainController.getInstance().getBalance()+") "+
					MainController.getInstance().getType()
					);
			this.bankDetailTopPanel.add(textId);
			
		add("North",this.bankDetailTopPanel);
		/* 계좌상세조회 상단 부분 끝 */
		
		/* 계좌상세조회 하단 부분 시작 */
		this.bankDetailBottomPanel = new JPanel();
		this.bankDetailBottomPanel.setLayout(new GridLayout(1,2));
		
			JPanel bankDetailBottomLeftPanel = new JPanel();
			bankDetailBottomLeftPanel.setLayout(new GridLayout(3,1,10,10));
		
			JButton deposit = new JButton("입금");
			bankDetailBottomLeftPanel.add(deposit);
			deposit.addActionListener(EventController.getInstance());
			
			JButton withdraw = new JButton("출금");
			bankDetailBottomLeftPanel.add(withdraw);
			withdraw.addActionListener(EventController.getInstance());
			
			JButton transfer = new JButton("이체");
			bankDetailBottomLeftPanel.add(transfer);
			transfer.addActionListener(EventController.getInstance());
			this.bankDetailBottomPanel.add(bankDetailBottomLeftPanel);
			
			this.historyList = new JList<String>(MainController.getInstance().getHistory());
			this.bankDetailBottomPanel.add(this.historyList);
			JScrollPane scroll = new JScrollPane(this.historyList);
			this.bankDetailBottomPanel.add(scroll);
			
		add("Center",this.bankDetailBottomPanel);
		/* 계좌상세조회 하단 부분 끝 */
	}
	
	public void update() {
		this.historyList.clearSelection();
		this.historyList.setListData(MainController.getInstance().getHistory());
		this.textId.update(
				MainController.getInstance().getBankName()+" ("+
				MainController.getInstance().getBalance()+") "+
				MainController.getInstance().getType()
				);
		this.historyList.updateUI();
		this.textId.updateUI();
	}
}

public class BankDetailViewer extends JFrame {
	private static BankDetailViewer instance = null;
	
	private Container ctPane = null;
	private BankDetailPanel bankDetail = null;
	
	/* Singletone */
	private BankDetailViewer(){
		setTitle("계좌 상세 관리");
		setSize(350,350);
		setLocationRelativeTo(null);
		this.ctPane = this.getContentPane();
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		bankDetail = new BankDetailPanel();
		
		this.ctPane.add(bankDetail);
		
		setVisible(true);
	}
	
	public static BankDetailViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new BankDetailViewer();
		}
	}
	public void update() {
		this.bankDetail.update();
	}
}
