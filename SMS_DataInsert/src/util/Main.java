package util;

import java.util.ArrayList;

import dao.DataReader;
import dao.DatabaseManager;
import dao.QueryMaker;
import model.User;
import model.Student;

public class Main {
	public static void main(String[] args) {
		DatabaseManager dbManager = new DatabaseManager();
		QueryMaker qMaker = new QueryMaker();
		DataReader reader = new DataReader();
		
		dbManager.connect(); //DB 연결
		
		//유저 정보를 DB에 삽입
		ArrayList<User> users = reader.getUser();
		ArrayList<String> queries = qMaker.createQueryUser(users);
		for(String i : queries) {
			dbManager.executeQuery(i);
		}
		//학과 정보를 DB에 삽입
		String query=qMaker.createQueryDepartment();
		dbManager.executeQuery(query);
		//학생 정보를 DB에 삽입
		ArrayList<Student> students = reader.getStudent();
		queries = new ArrayList<String>(); //queries 초기화
		queries = qMaker.createQueryStudent(students);
		for(String i : queries) {
			System.out.println(i);
			dbManager.executeQuery(i);
		}
		
		dbManager.disconnect();//DB 연결해제
	}
}