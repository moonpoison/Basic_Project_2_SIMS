package dao;


import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.User;

public class DataReader {
	String userData = "users.txt";
	String studentsData = "students.txt";
	
	public ArrayList<User> getUser() {
		ArrayList<User> users = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(userData))){
			String line;
			while((line = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line, "//");
				String id = st.nextToken();
				String pw = st.nextToken();
				users.add(new User(id, pw));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return users;
	}
}
