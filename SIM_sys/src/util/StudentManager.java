package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DatabaseManager;
import dao.QueryMaker;
import model.Student;

public class StudentManager {
	//학생정보를 삽입하는 메소드
	public void insertStudent(Student stu) {
		//쿼리 생성 및 쿼리 실행을 위한 생성
		String query = new QueryMaker().createIQueryStudent(stu);
		DatabaseManager dm = DatabaseManager.getInstance();
		//INSERT 쿼리를 진행
		dm.updateQuery(query);
	}
	//학생정보를 삭제하는 메소드(PK인 snum을 이용)
	public void deleteStudent(String snum) {
		String query = new QueryMaker().createDQueryStudent(snum);;
		DatabaseManager dm = DatabaseManager.getInstance();
		//DELETE 쿼리를 진행
		dm.updateQuery(query);
	}
	//조건에 맞는 학생정보를 가져오는 메소드
	public ArrayList<Student> selectStudent(Student stu) {
		ArrayList<Student> stuList = new ArrayList<Student>();
		String query = new QueryMaker().createSQueryStudent(stu);
		DatabaseManager dm = DatabaseManager.getInstance();
		//SELECT 쿼리를 진행
		ResultSet rs = dm.executeQuery(query);
		//반환받은 결과를 정제
		try {
			while(rs.next()) {
				String snum = rs.getString("snum");
				String sname = rs.getString("sname");
				int year = rs.getInt("year");
				String grade = rs.getString("grade");
				String department = rs.getString("department");
				stuList.add(new Student(sname, snum, year, grade, department));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return stuList;
	}
	//모든 학생의 정보를 가져오는 메소드
		public ArrayList<Student> selectAllStudent() {
			ArrayList<Student> stuList = new ArrayList<Student>();
			String query = new QueryMaker().createSAQueryStudent();
			DatabaseManager dm = DatabaseManager.getInstance();
			//SELECT 쿼리를 진행
			ResultSet rs = dm.executeQuery(query);
			//반환받은 결과를 정제
			try {
				while(rs.next()) {
					String snum = rs.getString("snum");
					String sname = rs.getString("sname");
					int year = rs.getInt("year");
					String grade = rs.getString("grade");
					String department = rs.getString("department");
					stuList.add(new Student(sname, snum, year, grade, department));
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return stuList;
		}
}
