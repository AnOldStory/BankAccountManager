package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;
import model.*;

public class EventController implements ActionListener{
	private static EventController instance = null;
	private String id;
	private String password;
	private int select;
	private String strAmount;
	private int intAmount;
	
	/* Singleton */
	private EventController() {}

	public static EventController getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new EventController();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "�α���":
				this.id = LoginViewer.getInstance().getId();
				this.password = LoginViewer.getInstance().getPassword();
				if (this.id.isEmpty()) {
					ErrorController.getInstance().alert("���̵���� ������ϴ�.");
				}
				else if (this.password.isEmpty()) {
					ErrorController.getInstance().alert("��й�ȣ���� ������ϴ�.");
				}
				else {
					int idNumber = UserOrm.getInstance().selectUser(id,password);
					if (idNumber >= 0) {
						MainController.getInstance().setIdNumber(idNumber);
						MainController.getInstance().update();
						MenuViewer.getInstance().setVisible(true);
						LoginViewer.getInstance().setVisible(false);
					}
					else if(idNumber==-99) {
						MainController.getInstance().setIdNumber(idNumber);
						ManageViewer.getInstance().update();
						ManageViewer.getInstance().setVisible(true);
						LoginViewer.getInstance().setVisible(false);
					}
				}
				break;
			case "ȸ������":
				SignUpViewer.getInstance().setVisible(true);
				break;
			case "�����ϱ�":
				this.id = SignUpViewer.getInstance().getId();
				this.password = SignUpViewer.getInstance().getPassword();
				if (this.id.isEmpty()) {
					ErrorController.getInstance().alert("���̵���� ������ϴ�.");
				}
				else if (this.password.isEmpty()) {
					ErrorController.getInstance().alert("��й�ȣ���� ������ϴ�.");
				}
				else {
					if(UserOrm.getInstance().createUser(this.id, this.password, SignUpViewer.getInstance().getAuth())) {
						ErrorController.getInstance().alert("���ԿϷ�.");
						SignUpViewer.getInstance().setVisible(false);
					}
				}
				break;
			case "�������":
				SignUpViewer.getInstance().setVisible(false);
				break;
			case "������ȸ":
				MainController.getInstance().update();
				BankViewer.getInstance().update();
				BankViewer.getInstance().setVisible(true);
				break;
			case "Ż��":
				UserOrm.getInstance().deleteUser(MainController.getInstance().getIdNumber());
			case "�α׾ƿ�":
				ManageViewer.getInstance().setVisible(false);
				MenuViewer.getInstance().setVisible(false);
				LoginViewer.getInstance().setVisible(true);
				break;
			case "���¼���":
				this.select = BankViewer.getInstance().getSelect();
				if(this.select != -1) {
					MainController.getInstance().update();
					BankDetailViewer.getInstance().update();
					BankDetailViewer.getInstance().setVisible(true);
				}
				else {
					ErrorController.getInstance().alert("���¸� �������ּ���!");
				}
				break;
			case "���»���":
				BankSignUpViewer.getInstance().setVisible(true);
				break;
			case "�������":
				BankSignUpViewer.getInstance().setVisible(false);
				break;
			case "���»���":
				this.select = BankViewer.getInstance().getSelect();
				if (this.select != -1) {
					MainController.getInstance().deleteBank(select);
					MainController.getInstance().update();
					BankViewer.getInstance().update();
				}
				else {
					ErrorController.getInstance().alert("���¸� �������ּ���!");
				}
				break;
			case "���¸����":
				this.id = BankSignUpViewer.getInstance().getId();
				this.password = BankSignUpViewer.getInstance().getPassword();
				if (this.id.isEmpty()) {
					ErrorController.getInstance().alert("���� ��Ī���� ������ϴ�.");
				}
				else {
					if(UserOrm.getInstance().createBank(MainController.getInstance().getIdNumber(),this.id, this.password)) {
						ErrorController.getInstance().alert("�����Ϸ�.");
						BankSignUpViewer.getInstance().setVisible(false);
						MainController.getInstance().update();
						BankViewer.getInstance().update();
					}
				}
				break;
			case "�Ա�":
				DepositViewer.getInstance().setVisible(true);
				break;
			case "�Ա��ϱ�":
				try {
					this.strAmount=DepositViewer.getInstance().getId();
					if(strAmount.isEmpty()) {
						ErrorController.getInstance().alert("�ݾ׶��� ������ϴ�.");
					}
					else {
						this.intAmount=Integer.parseInt(strAmount);
						if(BankOrm.getInstance().depositBank(UserOrm.getInstance().getBankNumber(MainController.getInstance().getIdNumber(), BankViewer.getInstance().getSelect()), intAmount)) {
							ErrorController.getInstance().alert("�ԱݿϷ�.");
							MainController.getInstance().update();
							BankViewer.getInstance().update();
							BankDetailViewer.getInstance().update();
							DepositViewer.getInstance().setVisible(false);
						};
					}
				}
				catch(NumberFormatException err) {
					ErrorController.getInstance().alert("������ �Է����ּ���!");
				}
				break;
			case "���":
				WithdrawViewer.getInstance().setVisible(true);
				break;
			case "����ϱ�":
				try {
					this.strAmount=WithdrawViewer.getInstance().getId();
					if(strAmount.isEmpty()) {
						ErrorController.getInstance().alert("�ݾ׶��� ������ϴ�.");
					}
					else{
						this.intAmount=Integer.parseInt(strAmount);
						if(BankOrm.getInstance().withdrawBank(UserOrm.getInstance().getBankNumber(MainController.getInstance().getIdNumber(), BankViewer.getInstance().getSelect()), intAmount)) {
							ErrorController.getInstance().alert("��ݿϷ�.");
							MainController.getInstance().update();
							BankViewer.getInstance().update();
							BankDetailViewer.getInstance().update();
							WithdrawViewer.getInstance().setVisible(false);
						};
					}
				}
				catch(NumberFormatException err) {
					ErrorController.getInstance().alert("������ �Է����ּ���!");
				}
				break;
			case "��ü":
				TransferViewer.getInstance().setVisible(true);
				break;
			case "��ü�ϱ�":
				try {
					this.strAmount = TransferViewer.getInstance().getId();
					int myBank  = BankViewer.getInstance().getSelect();
					String strTarget = TransferViewer.getInstance().getTarget();
					if(strAmount.isEmpty()) {
						ErrorController.getInstance().alert("�ݾ׶��� ������ϴ�.");
					}
					else if(strTarget.isEmpty()) {
						ErrorController.getInstance().alert("��� ���� ��ȣ���� ������ϴ�.");
					}
					else{
						this.intAmount = Integer.parseInt(strAmount);
						int intTarget = Integer.parseInt(strTarget);
						if(BankOrm.getInstance().transferBank(UserOrm.getInstance().getBankNumber(MainController.getInstance().getIdNumber(), myBank),intTarget, intAmount)) {
							ErrorController.getInstance().alert("��ü �Ϸ�.");
							MainController.getInstance().update();
							BankViewer.getInstance().update();
							BankDetailViewer.getInstance().update();
							TransferViewer.getInstance().setVisible(false);
						};
					}
				}
				catch(NumberFormatException err){
					ErrorController.getInstance().alert("������ �Է����ּ���!");
				}
				break;
			case "�����α���":
				if (ManageViewer.getInstance().getSelect() >= 0) {
					MainController.getInstance().adminUpdate(ManageViewer.getInstance().getSelect());
					BankViewer.getInstance().update();
					BankViewer.getInstance().setVisible(true);
				}
				else {
					ErrorController.getInstance().alert("���̵� �������ּ���!");
				}
				break;
			case "��������":
				if (ManageViewer.getInstance().getSelect()>=0) {
					UserOrm.getInstance().adminDeleteUser(ManageViewer.getInstance().getSelect());
					ErrorController.getInstance().alert("���̵� ���� �Ϸ�!");
					ManageViewer.getInstance().update();
				}
				else {
					ErrorController.getInstance().alert("���̵� �������ּ���!");
				}
				break;
		}
	}
}
