package model;

import java.util.ArrayList;

import controller.ErrorController;

/* Main Schema*/
class BankModel {
	protected String bankName;
	/* 1: Normal 2: OnlyIn 3: OnlyOut*/
	protected int type = 0;
	protected double balance;
	protected String owner;
	protected int bankNumber;

	ArrayList<String> tradeHistory = new ArrayList<>();
	
	String getBankName() {
		return this.bankName;
	}
	
	String getOwner() {
		return this.owner;
	}
	
	double getBalance() {
		return this.balance;
	}
	
	int getType() {
		return this.type;
	}
	
	int getBankNumber() {
		return this.bankNumber;
	}
	
	String[] getHistory() {
		String[] arr = new String[tradeHistory.size()];
		return (String[]) tradeHistory.toArray(arr);
	}
}

/* NormalAccount */
class NormalAccount extends BankModel{
	NormalAccount (String owner,String bankName,int bankNumber) {
		super.bankName = bankName;
		super.type = 1;
		super.balance = 0.0;
		super.owner = owner;
		super.bankNumber = bankNumber;
	}
	boolean deposit(double amount) {
		if (amount < 0) {
			ErrorController.getInstance().alert("음수는 불가능합니다.");
			return false;
		}
		else{
			super.balance += amount;
			super.tradeHistory.add("deposit : " + amount);
			return true;
		}
	}
	
	boolean withdraw(double amount) {
		if (amount < 0 ) {
			ErrorController.getInstance().alert("음수는 불가능합니다.");
			return false;
		}
		else if(super.balance < amount){
			ErrorController.getInstance().alert("돈이 부족합니다.");
			return false;
		}
		else {
			super.balance -= amount;
			super.tradeHistory.add("withdraw : -" + amount);
			return true;
		}
	}
}

/* DepositOnly  */
class OnlyInAccount extends BankModel{
	OnlyInAccount (String owner,String bankName,int bankNumber) {
		super.bankName = bankName;
		super.type = 2;
		super.balance = 0.0;
		super.owner = owner;
		super.bankNumber = bankNumber;
	}	
	
	boolean deposit(double amount) {
		if (amount < 0) {
			ErrorController.getInstance().alert("음수는  불가능합니다.");
			return false;
		}
		else{
			super.balance += amount;
			super.tradeHistory.add("deposit : " + amount);
			return true;
		}
	}
}

/* Withdraw Only */
class OnlyOutAccount extends BankModel{
	OnlyOutAccount (String owner,String bankName,int bankNumber, double amount) {
		super.bankName = bankName;
		super.type = 3;
		super.balance = amount;
		super.owner = owner;
		super.bankNumber = bankNumber;
	}
	
	boolean withdraw(double amount) {
		/* Invalid amount */
		if (amount < 0) {
			ErrorController.getInstance().alert("음수는  불가능합니다.");
			return false;
		}
		/* Not enough money */
		else if(super.balance < amount){
			ErrorController.getInstance().alert("돈이 부족합니다.");
			return false; 
		}
		else {
			super.balance -= amount;
			return true;
		}
	}
}

public class BankOrm {
	private static BankOrm instance = null;
	
	private ArrayList<BankModel> bankDb = new ArrayList<>();
	private int bankCount = 0;
	
	/* Singleton */
	private BankOrm() {}
	
	public static BankOrm getInstance(){
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new BankOrm();
		}
	}
	
	
	
	
	/* Select bankName from bankDb */
	private int findOne(int bankNumber) {
		for (int i=0;i<bankDb.size();i++) {
			BankModel model = bankDb.get(i);
			if (model.getBankNumber()==bankNumber) {
				return i;
			}
		}
		return -7;
	}

	
	public int createBank(String owner,String bankName,int type) {
		/* type => 1 : NormalAccount, 2 : OnlyInAccount, 3 : OnlyOutAccount */
		if (type==1) {
			bankDb.add(new NormalAccount(owner,bankName,this.bankCount));
			this.bankCount+=1;
		}
		else if(type==2) {
			bankDb.add(new OnlyInAccount(owner,bankName,this.bankCount));
			this.bankCount+=1;
		}
		else if(type==3) {
			bankDb.add(new OnlyOutAccount(owner,bankName,this.bankCount,10000));
			this.bankCount+=1;
		}
		else {
			/* Invalid type defined */
			return -3;
		}
		/* normally created */
		return this.bankCount-1;
	}
	
	/* Overriding */
	public int createBank(String owner,String bankName,int type,int amount) {
		if(type==3) {
			bankDb.add(new OnlyOutAccount(owner,bankName,this.bankCount,amount));
			this.bankCount+=1;
			return this.bankCount-1;
		}
		else {
			/* Invalid type defined */
			return -3;
		}
	}
	
	public boolean deleteBank(int bankNumber) {
		int bankIndex = this.findOne(bankNumber);
		bankDb.remove(bankIndex);
		return true;
	}
	
	public boolean depositBank(int bankNumber,int amount) {
		int bankIndex = this.findOne(bankNumber);
		if (bankIndex < 0) {
			ErrorController.getInstance().alert("존재하지않는 계좌 번호입니다.");
			return false;
		}
		BankModel model = bankDb.get(bankIndex);
		boolean rtn = false;
		switch (bankDb.get(bankIndex).type) {
			case 1:
				rtn = ((NormalAccount) model).deposit(amount);
				bankDb.set(bankIndex, model);
				return rtn;
			case 2:
				rtn = ((OnlyInAccount) model).deposit(amount);
				bankDb.set(bankIndex,model);
				return rtn;
			case 3:
				ErrorController.getInstance().alert("출금용 계좌에는 입금할 수 없습니다.");
				return rtn;
			default:
				return rtn;
		}
	}
	
	public boolean depositBank(int bankNumber,double amount) {
		int bankIndex = this.findOne(bankNumber);
		if (bankIndex < 0) {
			ErrorController.getInstance().alert("존재하지않는 계좌 번호입니다.");
			return false;
		}
		BankModel model = bankDb.get(bankIndex);
		boolean rtn = false;
		switch (bankDb.get(bankIndex).type) {
			case 1:
				rtn = ((NormalAccount) model).deposit(amount);
				bankDb.set(bankIndex, model);
				return rtn;
			case 2:
				rtn = ((OnlyInAccount) model).deposit(amount);
				bankDb.set(bankIndex,model);
				return rtn;
			case 3:
				ErrorController.getInstance().alert("출금용 계좌에는 입금할 수 없습니다.");
				return rtn;
			default:
				return rtn;
		}
	}
	
	public boolean withdrawBank(int bankNumber,int amount) {
		int bankIndex = this.findOne(bankNumber);
		BankModel model = bankDb.get(bankIndex);
		boolean rtn = false;
		switch (bankDb.get(bankIndex).type) {
			case 1:
				rtn = ((NormalAccount) model).withdraw(amount);
				bankDb.set(bankIndex, model);
				return rtn;
			case 2:
				ErrorController.getInstance().alert("입금용 계좌는 출금할 수 없습니다.");
				return rtn;
			case 3:
				rtn = ((OnlyOutAccount) model).withdraw(amount);
				bankDb.set(bankIndex, model);
				return rtn;
			default:
				return rtn;
		}
	}
	
	
	public boolean transferBank(int mybankNumber,int targetBankNumber ,int amount) {
		int mybankIndex = this.findOne(mybankNumber);
		int targetBankIndex = this.findOne(targetBankNumber);
		boolean rtn = false;
		/* valid check*/
		if(targetBankIndex>=0) {
			/* type check*/
			if (bankDb.get(mybankIndex).getType()==2) {
				ErrorController.getInstance().alert("입금용 계좌는 이체 할 수 없습니다.");
				return false;
			}
			if (bankDb.get(targetBankIndex).getType()==3) {
				ErrorController.getInstance().alert("출금용 계좌에는 입금 할 수 없습니다.");
				return false;
			}
			if(mybankIndex == targetBankIndex) {
				ErrorController.getInstance().alert("송금 하는 계좌와 받는 계좌는 일치 할 수 없습니다.");
				return false;
			}
			/* withdraw */
			rtn = withdrawBank(mybankNumber,amount);
			if(rtn) {
				rtn = depositBank(targetBankNumber,amount*0.99);
			}
		}
		else {
			ErrorController.getInstance().alert("존재하지 않는 계좌입니다.");
		}
		return rtn;
	}
	
	public String getInfo(int bankNumber) {
		int bankIndex = this.findOne(bankNumber);
		BankModel model = bankDb.get(bankIndex);
		String info = "";
		info+=model.getBankName();
		info+=" ("+String.valueOf((int)model.getBalance())+") ";
		info+="계좌번호:"+String.valueOf((int)model.getBankNumber())+" ";
		int type = model.getType();
		if(type==1) {
			info+="일반 통장";
		}
		else if(type==2){
			info+="입금용 통장";
		}
		else {
			info+="출금용 통장";
		}
		return info;
	}
	
	public String getBankName(int bankNumber) {
		int bankIndex = this.findOne(bankNumber);
		BankModel model = bankDb.get(bankIndex);
		return model.getBankName();
	}
	
	public String getBalance(int bankNumber) {
		int bankIndex = this.findOne(bankNumber);
		BankModel model = bankDb.get(bankIndex);
		return String.valueOf((int)model.getBalance());
	}
	
	public String getType(int bankNumber) {
		int bankIndex = this.findOne(bankNumber);
		BankModel model = bankDb.get(bankIndex);
		int type = model.getType();
		String info="";
		if(type==1) {
			info+="일반 통장";
		}
		else if(type==2){
			info+="입금용 통장";
		}
		else {
			info+="출금용 통장";
		}
		return info;
	}
	
	public String[] getHistory(int bankNumber) {
		int bankIndex = this.findOne(bankNumber);
		return bankDb.get(bankIndex).getHistory();
	}
}

