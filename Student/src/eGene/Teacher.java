package eGene;

import java.util.List;

/**
 * 教师类
 * @author 86130
 *
 */
public class Teacher extends Person{

	//所授课程
	private String  courses;

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return getId()+"\t"+getName()+"\t"+getSex()+"\t"+getCourses();
	}
}
