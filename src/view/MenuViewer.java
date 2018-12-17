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
		
		/* �޴� �г� ���� */
			this.menuCenterPanel = new JPanel();
			this.menuCenterPanel.setLayout(new GridLayout(4,1,0,30));
			
				/* �޴� ����*/
				CenterAlignTextPanel text = new CenterAlignTextPanel("�ȳ��ϼ���.");
				this.menuCenterPanel.add(text);
				
				JButton showBank = new JButton("������ȸ");
				this.menuCenterPanel.add(showBank);
				showBank.addActionListener(EventController.getInstance());
				
				JButton logout = new JButton("�α׾ƿ�");
				this.menuCenterPanel.add(logout);
				logout.addActionListener(EventController.getInstance());
				
				JButton quit = new JButton("Ż��");
				this.menuCenterPanel.add(quit);
				quit.addActionListener(EventController.getInstance());
				/* �޴� �� */
			
			add("Center",this.menuCenterPanel);
		/* �޴� �г� �� */
	}
}

public class MenuViewer extends JFrame{
	private static MenuViewer instance = null;
	
	private Container ctPane = null;
	private MenuPanel menu = null;
	
	/* Singleton */
	private MenuViewer() {
		setTitle("��������");
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
