package dao;

import java.util.ArrayList;

import model.User;

public class QueryMaker {
	//user 테이블에 보낼 쿼리를 만드는 메소드
	public ArrayList<String> createQueryUser(ArrayList<User> users) {
		ArrayList<String> queries = new ArrayList<>();
		for(User user : users) {
			String query = String.format("INSERT INTO users VALUES ('%s', '%s')", user.getID(), user.getPW());
			queries.add(query);
		}
		return queries;
	}
}
