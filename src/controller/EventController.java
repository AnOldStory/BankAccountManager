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
			case "로그인":
				this.id = LoginViewer.getInstance().getId();
				this.password = LoginViewer.getInstance().getPassword();
				if (this.id.isEmpty()) {
					ErrorController.getInstance().alert("아이디란이 비었습니다.");
				}
				else if (this.password.isEmpty()) {
					ErrorController.getInstance().alert("비밀번호란이 비었습니다.");
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
			case "회원가입":
				SignUpViewer.getInstance().setVisible(true);
				break;
			case "가입하기":
				this.id = SignUpViewer.getInstance().getId();
				this.password = SignUpViewer.getInstance().getPassword();
				if (this.id.isEmpty()) {
					ErrorController.getInstance().alert("아이디란이 비었습니다.");
				}
				else if (this.password.isEmpty()) {
					ErrorController.getInstance().alert("비밀번호란이 비었습니다.");
				}
				else {
					if(UserOrm.getInstance().createUser(this.id, this.password, SignUpViewer.getInstance().getAuth())) {
						ErrorController.getInstance().alert("가입완료.");
						SignUpViewer.getInstance().setVisible(false);
					}
				}
				break;
			case "가입취소":
				SignUpViewer.getInstance().setVisible(false);
				break;
			case "계좌조회":
				MainController.getInstance().update();
				BankViewer.getInstance().update();
				BankViewer.getInstance().setVisible(true);
				break;
			case "탈퇴":
				UserOrm.getInstance().deleteUser(MainController.getInstance().getIdNumber());
			case "로그아웃":
				ManageViewer.getInstance().setVisible(false);
				MenuViewer.getInstance().setVisible(false);
				LoginViewer.getInstance().setVisible(true);
				break;
			case "계좌선택":
				this.select = BankViewer.getInstance().getSelect();
				if(this.select != -1) {
					MainController.getInstance().update();
					BankDetailViewer.getInstance().update();
					BankDetailViewer.getInstance().setVisible(true);
				}
				else {
					ErrorController.getInstance().alert("계좌를 선택해주세요!");
				}
				break;
			case "계좌생성":
				BankSignUpViewer.getInstance().setVisible(true);
				break;
			case "생성취소":
				BankSignUpViewer.getInstance().setVisible(false);
				break;
			case "계좌삭제":
				this.select = BankViewer.getInstance().getSelect();
				if (this.select != -1) {
					MainController.getInstance().deleteBank(select);
					MainController.getInstance().update();
					BankViewer.getInstance().update();
				}
				else {
					ErrorController.getInstance().alert("계좌를 선택해주세요!");
				}
				break;
			case "계좌만들기":
				this.id = BankSignUpViewer.getInstance().getId();
				this.password = BankSignUpViewer.getInstance().getPassword();
				if (this.id.isEmpty()) {
					ErrorController.getInstance().alert("계좌 별칭란이 비었습니다.");
				}
				else {
					if(UserOrm.getInstance().createBank(MainController.getInstance().getIdNumber(),this.id, this.password)) {
						ErrorController.getInstance().alert("생성완료.");
						BankSignUpViewer.getInstance().setVisible(false);
						MainController.getInstance().update();
						BankViewer.getInstance().update();
					}
				}
				break;
			case "입금":
				DepositViewer.getInstance().setVisible(true);
				break;
			case "입금하기":
				try {
					this.strAmount=DepositViewer.getInstance().getId();
					if(strAmount.isEmpty()) {
						ErrorController.getInstance().alert("금액란이 비었습니다.");
					}
					else {
						this.intAmount=Integer.parseInt(strAmount);
						if(BankOrm.getInstance().depositBank(UserOrm.getInstance().getBankNumber(MainController.getInstance().getIdNumber(), BankViewer.getInstance().getSelect()), intAmount)) {
							ErrorController.getInstance().alert("입금완료.");
							MainController.getInstance().update();
							BankViewer.getInstance().update();
							BankDetailViewer.getInstance().update();
							DepositViewer.getInstance().setVisible(false);
						};
					}
				}
				catch(NumberFormatException err) {
					ErrorController.getInstance().alert("정수만 입력해주세요!");
				}
				break;
			case "출금":
				WithdrawViewer.getInstance().setVisible(true);
				break;
			case "출금하기":
				try {
					this.strAmount=WithdrawViewer.getInstance().getId();
					if(strAmount.isEmpty()) {
						ErrorController.getInstance().alert("금액란이 비었습니다.");
					}
					else{
						this.intAmount=Integer.parseInt(strAmount);
						if(BankOrm.getInstance().withdrawBank(UserOrm.getInstance().getBankNumber(MainController.getInstance().getIdNumber(), BankViewer.getInstance().getSelect()), intAmount)) {
							ErrorController.getInstance().alert("출금완료.");
							MainController.getInstance().update();
							BankViewer.getInstance().update();
							BankDetailViewer.getInstance().update();
							WithdrawViewer.getInstance().setVisible(false);
						};
					}
				}
				catch(NumberFormatException err) {
					ErrorController.getInstance().alert("정수만 입력해주세요!");
				}
				break;
			case "이체":
				TransferViewer.getInstance().setVisible(true);
				break;
			case "이체하기":
				try {
					this.strAmount = TransferViewer.getInstance().getId();
					int myBank  = BankViewer.getInstance().getSelect();
					String strTarget = TransferViewer.getInstance().getTarget();
					if(strAmount.isEmpty()) {
						ErrorController.getInstance().alert("금액란이 비었습니다.");
					}
					else if(strTarget.isEmpty()) {
						ErrorController.getInstance().alert("상대 계좌 번호란이 비었습니다.");
					}
					else{
						this.intAmount = Integer.parseInt(strAmount);
						int intTarget = Integer.parseInt(strTarget);
						if(BankOrm.getInstance().transferBank(UserOrm.getInstance().getBankNumber(MainController.getInstance().getIdNumber(), myBank),intTarget, intAmount)) {
							ErrorController.getInstance().alert("이체 완료.");
							MainController.getInstance().update();
							BankViewer.getInstance().update();
							BankDetailViewer.getInstance().update();
							TransferViewer.getInstance().setVisible(false);
						};
					}
				}
				catch(NumberFormatException err){
					ErrorController.getInstance().alert("정수만 입력해주세요!");
				}
				break;
			case "계정로그인":
				if (ManageViewer.getInstance().getSelect() >= 0) {
					MainController.getInstance().adminUpdate(ManageViewer.getInstance().getSelect());
					BankViewer.getInstance().update();
					BankViewer.getInstance().setVisible(true);
				}
				else {
					ErrorController.getInstance().alert("아이디를 선택해주세요!");
				}
				break;
			case "계정삭제":
				if (ManageViewer.getInstance().getSelect()>=0) {
					UserOrm.getInstance().adminDeleteUser(ManageViewer.getInstance().getSelect());
					ErrorController.getInstance().alert("아이디 삭제 완료!");
					ManageViewer.getInstance().update();
				}
				else {
					ErrorController.getInstance().alert("아이디를 선택해주세요!");
				}
				break;
		}
	}
}
