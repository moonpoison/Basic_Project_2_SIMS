package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DatabaseManager;
import dao.QueryMaker;
import model.User;

public class Login {
	String id; //유저의 아이디
	String pw; //유저의 비밀번호

	//생성자
	public Login(User user) {
		this.id=user.getID();
		this.pw=user.getPW();
	}
	//ID가 존재하는지
	public boolean loginUser() {
		boolean valid=false;
		DatabaseManager dm = DatabaseManager.getInstance();
		QueryMaker qm = new QueryMaker();
		
		String query = qm.createLoginQuery(id, pw);
		ResultSet rs = dm.executeQuery(query);
		try{
			if(rs!=null&&rs.next()) {
				valid = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return valid;
	}
}
