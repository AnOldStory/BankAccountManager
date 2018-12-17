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

import components.*;
import controller.EventController;
import controller.MainController;
import model.BankOrm;

class BankPanel extends JPanel {
	private JPanel bankTopPanel;
	private JPanel bankBottomPanel;
	private JList <String> bankList;
	
	public BankPanel() {
		this.setLayout(new BorderLayout(0,0));
		
		/* 계좌조회  상단 부분 시작 */
		this.bankTopPanel = new JPanel();
		this.bankTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
			CenterAlignTextPanel textId = new CenterAlignTextPanel("계좌 목록");
			this.bankTopPanel.add(textId);
			
		add("North",this.bankTopPanel);
		/* 계좌조회  상단 부분 끝 */
		
		/* 계좌목록 부분 시작*/
		this.bankBottomPanel = new JPanel();
		this.bankBottomPanel.setLayout(new GridLayout(1,2));
		
			this.bankList = new JList<String>(MainController.getInstance().getBankInfo());
			this.bankBottomPanel.add(this.bankList);
			JScrollPane scroll = new JScrollPane(this.bankList);
			this.bankBottomPanel.add(scroll);
			
			/* 버튼 추가 시작 */
			JPanel bankBottomRightPanel = new JPanel();
			bankBottomRightPanel.setLayout(new GridLayout(3,1,10,10));
			
			JButton selectBank = new JButton("계좌선택");
			bankBottomRightPanel.add(selectBank);
			selectBank.addActionListener(EventController.getInstance());

			JButton createBank = new JButton("계좌생성");
			bankBottomRightPanel.add(createBank);
			createBank.addActionListener(EventController.getInstance());
			
			JButton deleteBank = new JButton("계좌삭제");
			bankBottomRightPanel.add(deleteBank);
			deleteBank.addActionListener(EventController.getInstance());
			
			this.bankBottomPanel.add(bankBottomRightPanel);
			/* 버튼 추가 끝 */
			
		add("Center",this.bankBottomPanel);
		/* 계좌목록 부분 끝 */
	}
	
	public int getSelect() {
		return this.bankList.getSelectedIndex();
	}
	
	public void update(int index) {
		this.bankList.clearSelection();
		this.bankList.setListData(MainController.getInstance().getBankInfo());
		this.bankList.setSelectedIndex(index);
		this.bankList.updateUI();
	}
}

public class BankViewer extends JFrame {
	private static BankViewer instance = null;
	
	private Container ctPane = null;
	private BankPanel bank = null;
	
	/* Singleton */
	private BankViewer() {
		setTitle("계좌 목록");
		setSize(500,500);
		setLocationRelativeTo(null);
		this.ctPane = this.getContentPane();
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		bank = new BankPanel();
		
		this.ctPane.add(bank);
		
		setVisible(true);
	}
	
	public static BankViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new BankViewer();
		}
	}
	
	public int getSelect() {
		return this.bank.getSelect();
	}
	
	public void update() {
		this.bank.update(this.getSelect());
	}
}
