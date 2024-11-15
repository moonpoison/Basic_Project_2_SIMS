package dao;

import java.util.ArrayList;

import model.Student;
import model.User;

public class QueryMaker {
	//user 테이블에 보낼 쿼리를 만드는 메소드
	public ArrayList<String> createQueryUser(ArrayList<User> users) {
		ArrayList<String> queries = new ArrayList<>();
		for(User user : users) {
			String query = String.format("INSERT INTO user VALUES ('%s', '%s')", user.getID(), user.getPW());
			queries.add(query);
		}
		return queries;
	}
	//departments 테이블에 보낼 쿼리를 만드는 메소드
	public String createQueryDepartment() {
		String query = "INSERT INTO departments VALUES ('컴퓨터공학부'),('전자공학과'),('기계공학과'),('건축공학과'),('간호학과'),('재료공학과'),('경영학과'),('일어일문학과'),('산업경영공학과'),('체육학과'),('교육학과')";
		return query;
	}
	//students 테이블에 보낼 쿼리를 만드는 메소드
	public ArrayList<String> createQueryStudent(ArrayList<Student> Students) {
		ArrayList<String> queries = new ArrayList<>();
		for(Student student : Students) {
			String query = String.format("INSERT INTO students(snum, sname, year, grade, department) VALUES ('%s', '%s', %d, '%s', '%s')", student.getNum(), student.getName(), student.getYear(), student.getGrade(), student.getDepartment());
			queries.add(query);
		}
		return queries;
	}
}
