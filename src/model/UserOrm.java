package model;

import java.util.ArrayList;
import controller.ErrorController;
import view.BankViewer;

class UserModel {
	private String userId;
	private String password;
	private ArrayList<Integer> bankId = new ArrayList<>();
	private int auth;
	private int userNumber;
	
	UserModel(String userId,String password,int auth,int userNumber){
		this.userId=userId;
		this.password=password;
		this.auth=auth;
		this.userNumber=userNumber;
	}
	
	/* 1 = Normal  99 = Admin */
	int getAuth() {
		return this.auth;
	}
	
	String getId() {
		return this.userId;
	}
	
	int getUserNumber() {
		return this.userNumber;
	}
	
	Boolean tryLogin(String password) {
		if (this.password.equals(password)) {
			return true;
		}
		else {
			/* Wrong Id or Password */
			ErrorController.getInstance().alert("아이디 또는 비밀번호가 옳지않습니다.");
			return false;
		}
	}
	
	ArrayList<Integer> getBankId(){
		return this.bankId;
	}
	
	void createBankId(int bankId) {
		this.bankId.add(bankId);
	}
	
	void deleteBankId(int bankId) {
		this.bankId.remove((this.bankId.indexOf(bankId)));
	}
	
}

public class UserOrm {
	private static UserOrm instance = null;
	
	private ArrayList<UserModel> userDb = new ArrayList<>();
	private int userCount = 0;
	
	/* Singleton */
	private UserOrm() {}
	
	public static UserOrm getInstance() {
		if(instance != null) {
			return instance;
		}
		else {
			return instance = new UserOrm();
		}
	}

	/* Select id from userDb */
	private int findOne(String userId) {
		for (int i=0;i<userDb.size();i++) {
			if (userDb.get(i).getId().equals(userId)) {
				return i;
			}
		}
		return -7;
	}
	
	/* Overriding  */
	private int findOne(int userNumber) {
		for (int i=0;i<userDb.size();i++) {
			if(userDb.get(i).getUserNumber()==userNumber) {
				return i;
			}
		}
		return -7;
	}
	
	/* profile Update method*/
	public UserModel update(int userNumber) {
		int userIndex = findOne(userNumber);
		UserModel user = userDb.get(userIndex);
		return user;
	}
	
	public boolean createUser(String id,String password,String auth) {
		int isValid = this.findOne(id);
		if (isValid == -7) {
			int authNumber = 1;
			if (auth.equals("관리자")) {
				authNumber = 99;
			}
			userDb.add(new UserModel(id,password,authNumber,this.userCount));
			this.userCount+=1;
			return true;
		}
		else {
			ErrorController.getInstance().alert("이미 가입된 아이디입니다.");
			return false;
		}
	}
	
	/* login */
	public int selectUser(String userId,String password) {
		int userIndex = this.findOne(userId);
		if (userIndex>=0) {
			UserModel user = userDb.get(userIndex);
			if (user.tryLogin(password)) {
				if(user.getAuth()==99) {
					return -99;
				}
				return user.getUserNumber();
			}
		}
		else{
			ErrorController.getInstance().alert("아이디 또는 비밀번호가 옳지않습니다.");
		}
		return -6;
	}
	
	public boolean createBank(int userNumber,String bankName,String type) {
		int userIndex = findOne(userNumber);
		UserModel user = userDb.get(userIndex);
		int realType=0;
		if(type.equals("일반 통장")) {
			realType=1;
		}
		else if(type.equals("입금용 통장")) {
			realType=2;
		}
		else if(type.equals("출금용 통장(기본금액 10000원)")) {
			realType=3;
		}
		user.createBankId(BankOrm.getInstance().createBank(user.getId(),bankName, realType));
		userDb.set(userIndex, user);
		return true;
	}
	
	public boolean deleteBank(int userNumber,int bankNumber) {
		int userIndex = findOne(userNumber);
		UserModel user = userDb.get(userIndex);
		user.deleteBankId(bankNumber);
		userDb.set(userIndex, user);
		return true;
	}
	
	public boolean deleteUser(int userNumber) {
		int userIndex = findOne(userNumber);
		UserModel user = userDb.get(userIndex);
		ArrayList<Integer> bankId = user.getBankId();
		for(int i=0;i<bankId.size();i++) {
			BankOrm.getInstance().deleteBank(bankId.get(i));
		}
		userDb.remove(userIndex);
		return true;
	}
	
	public boolean adminDeleteUser(int index) {
		UserModel user = userDb.get(index);
		ArrayList<Integer> bankId = user.getBankId();
		for(int i=0;i<bankId.size();i++) {
			BankOrm.getInstance().deleteBank(bankId.get(i));
		}
		userDb.remove(index);
		return true;
	}
	
	public ArrayList<Integer> getBankId(int userNumber){
		System.out.println(userNumber);
		int userIndex = findOne(userNumber);
		UserModel user = userDb.get(userIndex);
		return user.getBankId();
	}
	
	public ArrayList<Integer> adminGetBankId(int userNumber){
		UserModel user = userDb.get(userNumber);
		return user.getBankId();
	}
	
	public String[] getUserInfo(){
		String[] info = new String[userDb.size()];
		for(int i=0;i<userDb.size();i++) {
			info[i] = userDb.get(i).getId();
		}
		return info;
	}
	
	public int getBankNumber(int userNumber,int index) {
		int userIndex = findOne(userNumber);
		UserModel user = userDb.get(userIndex);
		return user.getBankId().get(index);
	}
}
