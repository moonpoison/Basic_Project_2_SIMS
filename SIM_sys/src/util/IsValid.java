package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DatabaseManager;
import dao.QueryMaker;

public class IsValid {
	//입력한 아이디가 유효한 아이디인지 검증하는 메소드
	public boolean isValidID(String id) {
		DatabaseManager dm = DatabaseManager.getInstance();
		QueryMaker qm = new QueryMaker();
		boolean valid = true; //유효성
		
		String query = qm.createExistIDQuery(id);
		ResultSet rs = dm.executeQuery(query);
		try {
			if(rs!=null && rs.next()) {
				valid=false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return valid;
	}
}
