package model;
public class User {
	String id;
	String pw;
	boolean author;
	//생성자
	public User(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	//아이디 반환
	public String getID(){
		return id;
	}
	//비밀번호 반환
	public String getPW() {
		return pw;
	}
}
