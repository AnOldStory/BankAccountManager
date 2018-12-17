package controller;

import java.util.ArrayList;

import model.BankOrm;
import model.UserOrm;
import view.BankViewer;
import view.LoginViewer;

public class MainController {
	private static MainController instance = null;
	
	private int idNumber; 
	private ArrayList<Integer> bankId;
	
	private int auth;
	
	/* Singleton */
	private MainController() {}
	
	public static MainController getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new MainController();
		}
	}
	
	public void update() {
		this.bankId = UserOrm.getInstance().getBankId(this.idNumber);
	}
	
	public void adminUpdate(int idNumber) {
		this.bankId = UserOrm.getInstance().adminGetBankId(idNumber);
	}
	
	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	
	public ArrayList<Integer> getBankId(){
		return this.bankId;
	}
	
	public void setBankId(ArrayList<Integer> bankId) {
		this.bankId = bankId;
	}
	
	public String[] getBankInfo() {
		String[] info = new String[this.bankId.size()];
		for(int i=0;i<this.bankId.size();i++) {
			info[i]=BankOrm.getInstance().getInfo(this.bankId.get(i));
		}
		return info;
	}
	
	public String[] getUserInfo() {
		return UserOrm.getInstance().getUserInfo();
	}
	
	public void deleteBank(int index) {
		 int bankNumber = this.bankId.get(index);
		 BankOrm.getInstance().deleteBank(bankNumber);
		 UserOrm.getInstance().deleteBank(this.idNumber, bankNumber);
		 this.update();
	}
	
	public String[] getHistory() {
		return BankOrm.getInstance().getHistory(bankId.get(BankViewer.getInstance().getSelect()));
	}
	
	public String getBankName() {
		return BankOrm.getInstance().getBankName(bankId.get(BankViewer.getInstance().getSelect()));
	}
	
	public String getBalance() {
		return BankOrm.getInstance().getBalance(bankId.get(BankViewer.getInstance().getSelect()));
	}
	
	public String getType() {
		return BankOrm.getInstance().getType(bankId.get(BankViewer.getInstance().getSelect()));
	}
	
	
    public static void main(String args[]) {
    	UserOrm.getInstance();
    	BankOrm.getInstance();
    	LoginViewer.getInstance();
    	ErrorController.getInstance();

    	//Test Data
    	UserOrm.getInstance().createUser("qwer", "qwe","일반회원");
    	UserOrm.getInstance().createBank(0,"뱅크네임1","일반 통장");
    	UserOrm.getInstance().createBank(0,"뱅크네임2","입금용 통장");
    	UserOrm.getInstance().createBank(0,"뱅크네임3","출금용 통장(기본금액 10000원)");
    	UserOrm.getInstance().createBank(0,"뱅크네임4","일반 통장");
    	UserOrm.getInstance().createBank(0,"뱅크네임5","일반 통장");
    	UserOrm.getInstance().createBank(0,"뱅크네임6","일반 통장");
    	UserOrm.getInstance().createBank(0,"뱅크네임7","일반 통장");
    	BankOrm.getInstance().depositBank(0, 10000);
    	
    	UserOrm.getInstance().createUser("qwerr", "qwe","관리자");
    	UserOrm.getInstance().createBank(1,"뱅크네임1","일반 통장");
    	UserOrm.getInstance().createBank(1,"뱅크네임2","일반 통장");
    	UserOrm.getInstance().createBank(1,"뱅크네임3","일반 통장");
    	UserOrm.getInstance().createBank(1,"뱅크네임4","일반 통장");
    	UserOrm.getInstance().createBank(1,"뱅크네임5","일반 통장");
    	UserOrm.getInstance().createBank(1,"뱅크네임6","일반 통장");
    	UserOrm.getInstance().createBank(1,"뱅크네임7","일반 통장");
    	BankOrm.getInstance().depositBank(8, 10000);

    	
    	System.out.println("execute check");
    }
}
