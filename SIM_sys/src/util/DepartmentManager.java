package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DatabaseManager;
import dao.QueryMaker;

public class DepartmentManager {
	public ArrayList<String> getDepartments() {
		DatabaseManager dm = DatabaseManager.getInstance();
		String query = new QueryMaker().createSQueryDepartment();
		ArrayList<String> departments = new ArrayList<String>();
		ResultSet rs = dm.executeQuery(query);
		//반환받은 결과를 정제
		try {
			while(rs.next()) {
				String department = rs.getString("dname");
				departments.add(department);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return departments;
	}
}
