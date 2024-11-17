package util;

import java.util.ArrayList;

import dao.DatabaseManager;
import dao.QueryMaker;

public class DeleteStudent {
	//학생정보를 삭제하는 메소드(PK인 snum을 이용)
	public void deleteStudent(int snum[]) {
		ArrayList<String> queries= new QueryMaker().createDQueryStudent(snum);;
		DatabaseManager dm = new DatabaseManager();
		//DELETE 쿼리를 진행
		for(String i : queries) {
			dm.updateQuery(i);
		}
	}
}
