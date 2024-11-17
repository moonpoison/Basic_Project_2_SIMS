package util;

import java.util.ArrayList;

import model.Student;

public class ConvertObject {
	public Object[][] convertStudent(ArrayList<Student> students) {
		Object[][] stuList = new Object[students.size()][5];
		for(int i=0;i<students.size();++i) {
			Student student = students.get(i);//(String name, String num, int year, String grade, String department) {
			stuList[i][0] = student.getName();
			stuList[i][1] = student.getNum();
			stuList[i][2] = student.getYear();
			stuList[i][3] = student.getGrade();
			stuList[i][4] = student.getDepartment();			
		}
		return stuList;
	}
}
