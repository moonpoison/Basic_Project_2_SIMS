package util;

import dao.DatabaseManager;
import dao.QueryMaker;
import ui.LoginUI;

public class Main {
	public static void main(String[] args) {
//		DatabaseManager dbManager = new DatabaseManager();
//		dbManager.connect(); //DB 연결
//		
//		
//		
//		dbManager.disconnect(); //DB 연결해제
		new LoginUI();
	}
}
