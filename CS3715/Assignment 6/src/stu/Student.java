package stu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import crse.CourseNode;

public class Student implements Serializable {
	private String firstname, lastname;
	private double studentno;
	private String email, mailing;
	private ArrayList<CourseNode> courses;

	public Student(String f, String l, double num, String em, String ma) {
		firstname = f;
		lastname = l;
		studentno = num;
		email = em;
		mailing = ma;
		courses = new ArrayList<CourseNode>();
	}

	public String getFirstName() {
		return firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public double getStudentNo() {
		return studentno;
	}

	public String getEmail() {
		return email;
	}

	public String getMailing() {
		return mailing;
	}

	public void addCourse(CourseNode c) {
		courses.add(c);
	}

	public ArrayList<CourseNode> getCourses(){
		return courses;
	}

}
