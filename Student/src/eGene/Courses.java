package eGene;

/**
 * 课程类
 * 
 * @author 86130
 * 
 */
public class Courses {

	// 课程编号
	private String id;
	// 课程名称
	private String name;
	// 课程地点
	private String address;
	// 课程时间
	private String time;
	// 授课教师
	private String teacherId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Courses(String id, String name, String address, String time,
				   String teacherId) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.time = time;
		this.teacherId = teacherId;
	}

	public Courses(){}

	//打印课程信息
	@Override
	public String toString() {
		return getId()+"\t"+getName()+"\t"+getAddress()+"\t"+getTime()+"\t"+getTeacherId();
	}

}
