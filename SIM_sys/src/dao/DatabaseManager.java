package dao;

import java.sql.*;

public class DatabaseManager{
	private static final String URL = "jdbc:mysql://localhost:3306/university";
    private static final String USER = "root";
    private static final String PASSWORD = "wjddudwls2!";
    private static DatabaseManager instance;
    Connection con = null;
    
    //생성과 동시에 연결 및 프로그램 종료 시 연결해제
    private DatabaseManager() {
	    connect();
	    //종료후크
	    Runtime.getRuntime().addShutdownHook(new Thread(this::disconnect));
    }
   
    //인스턴스 반환 메소드(싱글톤패턴 이용 위함 -> ChatGPT 4o 참고...프로그램 구동 간 DB와의 연결 및 연결해제를 최적화 하는 법)
    public static synchronized DatabaseManager getInstance() {
    	if(instance == null) {
    		instance = new DatabaseManager();
    	}
    	return instance;
    }
    
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
	
	//SELECT Query 실행 메소드
	public ResultSet executeQuery(String query) {
		ResultSet rs;
		try {
			Statement stmt = con.createStatement();
			rs=stmt.executeQuery(query);
			System.out.println("Successfully Executed query : "+query);
			return rs;
		}catch(SQLException e) {
			System.out.println("Failed to execute query : " + query);
			return null;
		}
	}
	
	//SELECT 제외 Query 실행 메소드
	public void updateQuery(String query) {
	    try {
	    	Statement stmt = con.createStatement();
	    	stmt.executeUpdate(query);
	    	System.out.println("Successfully Executed query : "+query);
	    }catch(SQLException e) {
	    	System.out.println("Failed to execute query : " + query);
	    	e.printStackTrace();
	    }
	}
}