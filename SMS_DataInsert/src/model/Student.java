package model;
public class
Student {
	String name;
	String num;
	int year;
	String grade;
	String department;
	
	//생성자
	public Student(String name, String num, int year, String grade, String department) {
		this.name=name;
		this.num=num;
		this.year=year;
		this.grade=grade;
		this.department=department;
	}
	//이름반환
	public String getName() {
        return name;
    }
	//학번반환
    public String getNum() {
        return num;
    }
    //학년반환
    public int getYear() {
        return year;
    }
    //구분반환
    public String getGrade() {
        return grade;
    }
    //학과반환
    public String getDepartment() {
        return department;
    }
}
