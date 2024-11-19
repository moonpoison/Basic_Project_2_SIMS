package util;

import dao.DatabaseManager;
import dao.QueryMaker;
import model.User;

public class Register {
	//회원가입 진행
	public boolean register(User user) {
		String id = user.getID();
		String pw = user.getPW();
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
