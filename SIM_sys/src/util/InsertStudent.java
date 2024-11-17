package util;

import java.util.ArrayList;

import dao.DatabaseManager;
import dao.QueryMaker;
import model.Student;

public class InsertStudent {
	//학생정보를 삽입하는 메소드
	public void insertStudent(ArrayList<Student> stu) {
		//쿼리 생성 및 쿼리 실행을 위한 생성
		ArrayList<String> queries= new QueryMaker().createIQueryStudent(stu);;
		DatabaseManager dm = new DatabaseManager();
		//INSERT 쿼리를 진행
		for(String i : queries) {
			dm.updateQuery(i);
		}
	}
}
