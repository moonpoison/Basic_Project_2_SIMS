package util;

import dao.DatabaseManager;
import dao.QueryMaker;

public class Main {
	public static void main(String[] args) {
		DatabaseManager dbManager = new DatabaseManager();
		dbManager.connect(); //DB 연결
		
		
		
		dbManager.disconnect(); //DB 연결해제
	}
}
