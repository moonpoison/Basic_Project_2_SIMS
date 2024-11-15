package dao;


import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import model.Student;
import model.User;

public class DataReader {
	String userData = "data/users.txt";
	String studentsData = "data/students.txt";
	
	//user.txt에서 정제된 데이터를 반환하는 메소드
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
			System.exit(0);
		}
		return users;
	}
	//students.txt에서 정제된 데이터를 반환하는 메소드
		public ArrayList<Student> getStudent() {
			ArrayList<Student> students = new ArrayList<>();
			try(BufferedReader br = new BufferedReader(new FileReader(studentsData))){
				String line;
				while((line = br.readLine())!=null) {
					StringTokenizer st = new StringTokenizer(line, " ");
					try {
						String dname = st.nextToken();
						int year = Integer.parseInt(st.nextToken());
						String sname = st.nextToken();
						String grade = st.nextToken();
						String snum = st.nextToken();
						students.add(new Student(sname, snum, year, grade, dname));
					}catch(NumberFormatException e) {
						System.out.println(0);
					}catch(NoSuchElementException e) {
						System.out.println(1);
					}
					
				}
			}catch(IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
			return students;
		}
}
