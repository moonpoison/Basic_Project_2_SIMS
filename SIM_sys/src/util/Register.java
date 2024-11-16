package util;

import dao.DatabaseManager;
import dao.QueryMaker;

public class Register {
	String id; //유저의 아이디
	String pw; //유저의 비밀번호
	
	//생정자
	public Register(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	//회원가입 진행
	public void register(String id, String pw) {
		DatabaseManager dm = new DatabaseManager();
		QueryMaker qm = new QueryMaker();
		boolean valid = new IsValid().isValidID(id);
		String query;
		if(valid) {
			query = qm.createRegisterQuery(id, pw);
			dm.updateQuery(query);
			System.out.println("Successfully complete register");
		}else {
			System.out.println("Failed to register...");
		}
	}
}
