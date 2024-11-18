package dao;

import java.util.ArrayList;

import model.Student;
import model.User;

public class QueryMaker {
	//로그인을 위한 쿼리를 만드는 메소드
	public String createLoginQuery(String id, String pw) {
		String query = String.format("SELECT * FROM user WHERE id='%s' AND pw='%s'", id, pw);
		return query;
	}
	//ID 존재여부를 위한 쿼리를 만드는 메소드
	public String createExistIDQuery(String id) {
		String query = String.format("SELECT * FROM user WHERE id='%s'", id);
		return query;
	}
	//회원가입을 위한 메소드
	public String createRegisterQuery(String id, String pw) {
		String query = String.format("INSERT INTO user VALUES('%s', '%s')", id, pw);
		return query;
	}
	//students 테이블에 보낼 INSERT 쿼리를 만드는 메소드
	public String createIQueryStudent(Student student) {
		String query = String.format("INSERT INTO students(snum, sname, year, grade, department) VALUES ('%s', '%s', %d, '%s', '%s')", student.getNum(), student.getName(), student.getYear(), student.getGrade(), student.getDepartment());
		return query;
	}
	//students 테이블에 보낼 DELETE 쿼리를 만드는 메소드
	public String createDQueryStudent(String snum) {
		String query = String.format("DELETE FROM students WHERE snum='%s'", snum);
		return query;
	}
	//students 테이블에 보낼 DELETE 쿼리를 만드는 메소드
	public String createSQueryStudent(Student stu) {
		String dep = stu.getDepartment();
		String grade = stu.getGrade();
		Integer year = stu.getYear();
		String snum = stu.getNum();
		
		String query = String.format("SELECT * FROM students WHERE 1=1");
		if(dep!=null && !dep.isEmpty()) {
			query+=String.format(" AND department='%s'", dep);
		}
		if(snum!=null && !snum.isEmpty()) {
			query+=String.format(" AND snum='%s'", snum);
		}
		if(grade!=null && !grade.isEmpty()) {
			query+=String.format(" AND grade='%s'", grade);
		}
		if(year!=null) {
			query+=String.format(" AND year='%d'", year);
		}
		return query;
	}
	//students 테이블에 보낼 SELECT ALL 쿼리를 만드는 메소드
	public String createSAQueryStudent() {
		String query = "SELECT * FROM students";
		return query;
	}
	//departments 테이블에 보낼 SELECT ALL 쿼리를 만드는 메소드
		public String createSQueryDepartment() {
			String query = "SELECT * FROM departments";
			return query;
		}
}
