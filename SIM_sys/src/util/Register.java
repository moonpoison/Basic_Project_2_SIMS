package util;

import dao.DatabaseManager;
import dao.QueryMaker;
import model.User;

public class Register {
	String id; //유저의 아이디
	String pw; //유저의 비밀번호
	
	//생정자
	public Register(User user) {
		this.id = user.getID();
		this.pw = user.getPW();
	}
	
	//회원가입 진행
	public boolean register() {
		DatabaseManager dm = DatabaseManager.getInstance();
		QueryMaker qm = new QueryMaker();
		boolean valid = new IsValid().isValidID(id);
		String query;
		if(valid) {
			query = qm.createRegisterQuery(id, pw);
			dm.updateQuery(query);
			System.out.println("Successfully complete register");
			valid = true;
		}else {
			System.out.println("Failed to register...");
			valid = false;
		}
		return valid;
	}
}
