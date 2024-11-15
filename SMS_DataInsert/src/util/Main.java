package util;

import java.util.ArrayList;

import dao.DataReader;
import dao.DatabaseManager;
import dao.QueryMaker;
import model.User;

public class Main {
	public static void main(String[] args) {
		DatabaseManager dbManager = new DatabaseManager();
		QueryMaker qMaker = new QueryMaker();
		DataReader reader = new DataReader();
		
		dbManager.connect();
		
		ArrayList<User> users = reader.getUser();
		ArrayList<String> queries = qMaker.createQueryUser(users);
		for(String i : queries) {
			dbManager.executeQuery(i);
		}
		
		dbManager.disconnect();
	}
}
