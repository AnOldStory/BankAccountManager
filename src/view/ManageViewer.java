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

class ManagePanel extends JPanel {
	private JPanel manageTopPanel;
	private JPanel manageBottomPanel;
	private JList <String> manageList;
	
	public ManagePanel() {
		this.setLayout(new BorderLayout(0,0));
		
		/* 계좌조회  상단 부분 시작 */
		this.manageTopPanel = new JPanel();
		this.manageTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
			CenterAlignTextPanel textId = new CenterAlignTextPanel("아이디 목록");
			this.manageTopPanel.add(textId);
			
		add("North",this.manageTopPanel);
		/* 계좌조회  상단 부분 끝 */
		
		/* 계좌목록 부분 시작*/
		this.manageBottomPanel = new JPanel();
		this.manageBottomPanel.setLayout(new GridLayout(1,2));
		
			this.manageList = new JList<String>(MainController.getInstance().getUserInfo());
			this.manageBottomPanel.add(this.manageList);
			JScrollPane scroll = new JScrollPane(this.manageList);
			this.manageBottomPanel.add(scroll);
			
			/* 버튼 추가 시작 */
			JPanel manageBottomRightPanel = new JPanel();
			manageBottomRightPanel.setLayout(new GridLayout(3,1,10,10));
			
			JButton selectManage = new JButton("계정로그인");
			manageBottomRightPanel.add(selectManage);
			selectManage.addActionListener(EventController.getInstance());

			JButton createBank = new JButton("로그아웃");
			manageBottomRightPanel.add(createBank);
			createBank.addActionListener(EventController.getInstance());
			
			JButton deleteManage = new JButton("계정삭제");
			manageBottomRightPanel.add(deleteManage);
			deleteManage.addActionListener(EventController.getInstance());
			
			this.manageBottomPanel.add(manageBottomRightPanel);
			/* 버튼 추가 끝 */
			
		add("Center",this.manageBottomPanel);
		/* 계좌목록 부분 끝 */
	}
	
	public int getSelect() {
		return this.manageList.getSelectedIndex();
	}
	
	public void update(int index) {
		this.manageList.clearSelection();
		this.manageList.setListData(MainController.getInstance().getUserInfo());
		this.manageList.setSelectedIndex(index);
		this.manageList.updateUI();
	}
}

public class ManageViewer extends JFrame {
	private static ManageViewer instance = null;
	
	private Container ctPane = null;
	private ManagePanel manage = null;
	
	/* Singleton */
	private ManageViewer() {
		setTitle("관리자 메뉴");
		setSize(500,500);
		setLocationRelativeTo(null);
		this.ctPane = this.getContentPane();
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		manage = new ManagePanel();
		
		this.ctPane.add(manage);
		
		setVisible(true);
	}
	
	public static ManageViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new ManageViewer();
		}
	}
	
	public int getSelect() {
		return this.manage.getSelect();
	}
	
	public void update() {
		this.manage.update(this.getSelect());
	}
}
