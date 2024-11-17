package util;

import dao.DatabaseManager;
import dao.QueryMaker;
import ui.LoginUI;

public class Main {
	public static void main(String[] args) {
		DatabaseManager dm = DatabaseManager.getInstance();

		LoginUI loginUI = new LoginUI();
	}
}
