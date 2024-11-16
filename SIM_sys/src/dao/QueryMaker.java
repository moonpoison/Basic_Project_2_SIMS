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
}
