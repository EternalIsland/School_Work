/**
 * Intermediary class that provides simplified versions of the CourseNode methods.
 */

package crse;

import java.util.ArrayList;

public class Course {
	private ArrayList<CourseNode> classes;
	private String coursesubj, coursenum;
	private CourseNode startCourse;
	private ArrayList<CourseNode> classesFiltered;

	public Course(String subj, String num) {
		this.coursesubj = subj;
		this.coursenum = num;
		this.startCourse = new CourseNode();
		this.classes = startCourse.classes();
	}

	public String getSubj() {
		for (CourseNode cn : classes) {
			if (cn.getSubj().equals(coursesubj)) {
				return coursesubj;
			}
		}
		return "Error. Class does not exist.";
	}

	public String getNum() {
		for (CourseNode cn : classes) {
			if (cn.getNum().equals(coursenum)) {
				return coursenum;
			}
		}
		return "Error. Class does not exist.";
	}

	// XXX Something causing a NullPointerException here, possibly a problem
	// with winter2014.xml file being changed?
	public ArrayList<CourseNode> getClassList() {
		for (CourseNode cn : classes) {
			if (cn.getSubj().equals(this.coursesubj)
					&& cn.getNum().equals(this.coursenum)) {
				classesFiltered.add(cn);
			}
		}
		return classesFiltered;
	}
}
