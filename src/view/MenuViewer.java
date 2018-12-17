package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.*;
import controller.EventController;



class MenuPanel extends JPanel {
	private JPanel menuCenterPanel;
	public MenuPanel() {
		this.setLayout(new BorderLayout(20,20));
		
		/* 메뉴 패널 시작 */
			this.menuCenterPanel = new JPanel();
			this.menuCenterPanel.setLayout(new GridLayout(4,1,0,30));
			
				/* 메뉴 시작*/
				CenterAlignTextPanel text = new CenterAlignTextPanel("안녕하세요.");
				this.menuCenterPanel.add(text);
				
				JButton showBank = new JButton("계좌조회");
				this.menuCenterPanel.add(showBank);
				showBank.addActionListener(EventController.getInstance());
				
				JButton logout = new JButton("로그아웃");
				this.menuCenterPanel.add(logout);
				logout.addActionListener(EventController.getInstance());
				
				JButton quit = new JButton("탈퇴");
				this.menuCenterPanel.add(quit);
				quit.addActionListener(EventController.getInstance());
				/* 메뉴 끝 */
			
			add("Center",this.menuCenterPanel);
		/* 메뉴 패널 끝 */
	}
}

public class MenuViewer extends JFrame{
	private static MenuViewer instance = null;
	
	private Container ctPane = null;
	private MenuPanel menu = null;
	
	/* Singleton */
	private MenuViewer() {
		setTitle("계정관리");
		setSize(350,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ctPane = this.getContentPane();
		this.ctPane.setLayout(new BorderLayout(20,20));
		
		menu = new MenuPanel();
		
		this.ctPane.add(menu);
		
		setVisible(true);
	}
	
	public static MenuViewer getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new MenuViewer();
		}
	}
}
