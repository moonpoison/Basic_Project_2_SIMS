package dao;

import java.sql.*;

public class DatabaseManager{
	private static final String URL = "jdbc:mysql://localhost:3306/university";
    private static final String USER = "root";
    private static final String PASSWORD = "wjddudwls2!";
    Connection con = null;
    
    //DB연결 메소드
	public void connect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("연결 성공");
        }
        catch(ClassNotFoundException e){
            System.out.println("Failed to loading Driver...");
        }
        catch(SQLException e){
            System.out.println("ERROR: " + e);
        }
    }
	
	//DB연결해제 메소드
	public void disconnect() {
		try {
			if(con!=null && !con.isClosed()) {
				con.close();
				System.out.println("Disconnected Successfully!");
			}
		}catch(SQLException e){
			System.out.println("Failed to disconnect : " + e.getMessage());
		}
	}
	
	//Query 실행 메소드
	public void executeQuery(String query) {
	    try {
	    	Statement stmt = con.createStatement();
	    	stmt.executeQuery(query);
	    	System.out.println("Executed query : "+query);
	    }catch(SQLException e) {
	    	System.out.println("Failed to execute query : " + e.getMessage());
	    }
	}
}