package eGene;

/**
 * 学生类
 */
public class Student extends Person{

	//选课信息
	private String course;



	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}


	//打印学生信息
	@Override
	public String toString() {
		return getId()+"\t"+getName()+"\t"+getSex()+"\t"+getCourse();
	}
}
