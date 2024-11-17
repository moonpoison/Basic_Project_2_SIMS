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
	public ArrayList<String> createIQueryStudent(ArrayList<Student> Students) {
		ArrayList<String> queries = new ArrayList<>();
		for(Student student : Students) {
			String query = String.format("INSERT INTO students(snum, sname, year, grade, department) VALUES ('%s', '%s', %d, '%s', '%s')", student.getNum(), student.getName(), student.getYear(), student.getGrade(), student.getDepartment());
			queries.add(query);
		}
		return queries;
	}
	//students 테이블에 보낼 DELETE 쿼리를 만드는 메소드
	public ArrayList<String> createDQueryStudent(int[] snum) {
		ArrayList<String> queries = new ArrayList<>();
		for(int i : snum) {
			String query = String.format("DELETE FROM students WHERE snum='%d'", i);
			queries.add(query);
		}
		return queries;
	}
	//students 테이블에 보낼 DELETE 쿼리를 만드는 메소드
	public String createSQueryStudent(Student stu) {
		String dep = stu.getDepartment();
		String grade = stu.getGrade();
		int year = stu.getYear();
		
		String query = String.format("SELECT * FROM students WHERE 1=1");
		if(dep!=null && !dep.isEmpty()) {
			query+=String.format(" AND department='%s'", dep);
		}
		if(grade!=null && !grade.isEmpty()) {
			query+=String.format(" AND department='%s'", grade);
		}
		if(dep!=null) {
			query+=String.format(" AND department='%d'", year);
		}
		return query;
	}
}
