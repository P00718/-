package eGene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Manager extends Person{



	/**
	 * 教师授课
	 */
	public void teacherChoose(){
		//控制台输入教师编号
		System.out.print("请输入教师编号：");
		String stuNo = Stream.inputString().trim();
		String courName = "";
		boolean flag = false;
		//获取所有教师信息
		List<Teacher> teacherList = this.getAllTeacher();
		// 用于记录选课学生的记录
		Teacher tempInfo = new Teacher();
		//循环遍历，查询教师是存在
		for (Teacher teacher : teacherList) {
			if (teacher.getId().equals(stuNo)) {
				tempInfo.setCourses(teacher.getCourses());
				tempInfo.setId(teacher.getId());
				//打印该教师信息
				System.out.println("编号：" + stuNo + "\t姓名："
						+ teacher.getName() + "\t性别："+teacher.getSex()+"\t授课:" + teacher.getCourses());
				courName=teacher.getCourses();
				flag = true;
			}
		}

		if (!flag) {
			System.out.println("该教师不存在!");

			//若授课信息不是未选，则不允许再授课
		}else if(!"未选".equals(courName)){
			System.out.println("该教师已授课!");
		} else {
			//打印课程信息，并获取课程信息
			System.out.println("----课程信息---");
			List<Courses> coursesList = listCor();


			// 操作选课信息
			System.out.print("请输入要选的课程编号:");
			String newCourse = Stream.inputString();

			flag = false;
			//循环判断该课程是否存在，并且是否已经有授课老师，有则不允许再进行授课
			for(int i=0;i<coursesList.size();i++){
				Courses courses = coursesList.get(i);
				if(courses.getId().equals(newCourse)){
					String teacherId = courses.getTeacherId();
					if(!"未选".equals(teacherId)){
						System.out.print("该课程已有授课老师");
						return;
					}
					//将该教师id保存至课程的对象中
					coursesList.get(i).setTeacherId(stuNo);
					flag = true;

				}
			}
			if(!flag){
				System.out.println("该课程不存在!");
				return;
			}

			//将教师选课后的教师信息保存至教师文件中
			String info = "";
			for (Teacher teacher : teacherList) {
				if (teacher.getId().equals(tempInfo.getId())) {
					info += teacher.getId() + "," + teacher.getName()+","+teacher.getSex()
							+ "," + newCourse + "\n";// 修改"未选"为选课名称

				} else {
					info += teacher.getId() + "," + teacher.getName()+","+teacher.getSex()
							+ "," + teacher.getCourses() + "\n";
				}

			}
			this.updateTxtInfo(info, Stream.TEACHERPATH);
			info = "";
			//将教师选课后的信息课程信息保存至课程文件中
			for (Courses courses : coursesList) {
				info += courses.getId() + "," + courses.getName() + ","
						+ courses.getAddress() +","+courses.getTime()+","+courses.getTeacherId()+ "\n";
			}
			this.updateTxtInfo(info, Stream.COURSEINFOPATH);

			System.out.println("选课成功！！");


		}
	}


	/***
	 * 学生选课
	 */
	public void choose() {
		//控制台输入学生编号
		System.out.print("请输入学生学号：");
		String stuNo = Stream.inputString().trim();
		String courName = "";
		boolean flag = false;
		//获取所有学生信息
		List<Student> stuList = this.getAllStu();
		// 用于记录选课学生的记录
		Student tempInfo = new Student();
		//循环遍历，查询学生是存在
		for (Student stuInfo : stuList) {
			if (stuInfo.getId().equals(stuNo)) {
				tempInfo.setCourse(stuInfo.getCourse());
				tempInfo.setId(stuInfo.getId());
				//打印学生信息
				System.out.println("学号：" + stuNo + "\t姓名："
						+ stuInfo.getName() + "\t性别："+stuInfo.getSex()+"\t选课:" + stuInfo.getCourse());
				courName=stuInfo.getCourse();
				flag = true;
			}
		}
		//学号不存在，不允许选课
		if (!flag) {
			System.out.println("该学号不存在!");
		//若选课信息不是未选，则不允许再选课
		}else if(!"未选".equals(courName)){
			System.out.println("该学生已选课!");
		} else {
			//打印所有课程信息
			System.out.println("----课程信息---");
			listCor();

			// 操作选课信息
			System.out.print("请输入要选的课程编号:");
			String newCourse = Stream.inputString();

			//将该学生的选课信息重新保存至文件中
			String info = "";
			for (Student stuInfo : stuList) {
				if (stuInfo.getId().equals(tempInfo.getId())) {
					// 修改"未选"为选课名称
					info += stuInfo.getId() + "," + stuInfo.getName()+","+stuInfo.getSex()
							+ "," + newCourse + "\n";
				} else {
					info += stuInfo.getId() + "," + stuInfo.getName()+","+stuInfo.getSex()
							+ "," + stuInfo.getCourse() + "\n";
				}

			}
			this.updateTxtInfo(info, Stream.STUINFOPATH);
			System.out.println("选课成功！！");
		}



	}

	/**
	 * 删除学生信息
	 */
	public void deleteStu() {
		//控制台输入学生学号
		System.out.print("请输入要删除的学号：");
		String stuNo = Stream.inputString().trim();

		boolean flag = false;
		//获取所有学生信息
		List<Student> stuList = this.getAllStu();
		//循环遍历，查询学生是存在
		for (Student stuInfo : stuList) {
			if (stuInfo.getId().equals(stuNo)) {
				flag = true;
				break;
			}
		}
		//学号不存在，不允许删除
		if (!flag) {
			System.out.println("该学号不存在!");
		} else {
			// 操作学生信息，在迭代器中将其移除
			Iterator iterator = stuList.iterator();
			while (iterator.hasNext()) {
				Student stuInfo = (Student) iterator.next();
				//学号和集合中的学号一致
				if (stuInfo.getId().equals(stuNo)) {
					//移除该学生
					iterator.remove();
					//打印学生信息
					System.out.println("学号：" + stuNo + "\t姓名："
							+ stuInfo.getName() +"\t性别："+stuInfo.getSex()+ "\t选课:"
							+ stuInfo.getCourse());
					break;
				}
			}
			String info = "";
			//将移除后的学生信息保存至文件中
			for (Student stuInfo : stuList) {
				info += stuInfo.getId() + "," + stuInfo.getName() + ","+stuInfo.getSex()+","
						+ stuInfo.getCourse() + "\n";
			}
			this.updateTxtInfo(info, Stream.STUINFOPATH);

			System.out.println("删除成功！！");

		}

	}

	/**
	 * 删除教师信息
	 */
	public void deleteTeacher(){
		//控制台输入教师编号
		System.out.print("请输入要删除的教师编号：");
		String stuNo = Stream.inputString().trim();

		boolean flag = false;
		//获取所有教师信息
		List<Teacher> teacherList = this.getAllTeacher();
		//循环遍历，查询教师是存在
		for (Teacher teacher : teacherList) {
			if (teacher.getId().equals(stuNo)) {
				flag = true;
				break;
			}
		}
		//教师编号不存在，不允许删除
		if (!flag) {
			System.out.println("该教师不存在!");
		} else {
			String  name = "";
			// 操作教师信息，在迭代器中将其移除
			Iterator iterator = teacherList.iterator();
			while (iterator.hasNext()) {
				Teacher teacher = (Teacher) iterator.next();
				//教师编号和集合中的教师编号一致
				if (teacher.getId().equals(stuNo)) {
					//移除该教师
					iterator.remove();
					name = teacher.getName();
					//打印教师信息
					System.out.println("学号：" + stuNo + "\t姓名："
							+ teacher.getName() +"\t性别："+teacher.getSex()+ "\t授课:"
							+ teacher.getCourses());
					break;
				}
			}

			String info = "";
			//将移除后的教师信息保存至文件中
			for (Teacher teacher : teacherList) {
				info += teacher.getId() + "," + teacher.getName() + ","+teacher.getSex()+","
						+ teacher.getCourses() + "\n";
			}

			this.updateTxtInfo(info, Stream.TEACHERPATH);

			System.out.println("删除成功！！");

		}
	}


	/**
	 * 删除课程信息
	 */
	public void deleteCou(){
		//控制台输入课程编号
		System.out.print("请输入要删除的课程编号：");
		String stuNo = Stream.inputString().trim();
		//获取所有课程信息
		boolean flag = false;
		//循环遍历，查询课程是存在
		List<Courses> coursesList = this.getAllCourse();
		for (Courses courses : coursesList) {
			if (courses.getId().equals(stuNo)) {
				flag = true;
				break;
			}
		}
		//课程编号不存在，不允许删除
		if (!flag) {
			System.out.println("该课程不存在!");
		} else {
			String  name = "";
			// 操作学生信息
			Iterator iterator = coursesList.iterator();// 在迭代器中将其移除
			while (iterator.hasNext()) {
				Courses courses = (Courses) iterator.next();
				//课程编号和集合中的课程编号一致
				if (courses.getId().equals(stuNo)) {
					iterator.remove();
					name = courses.getName();
					//打印要移除的课程信息
					System.out.println("编号：" + stuNo + "\t课程"+courses.getName());
					break;
				}
			}
			//获取所有学生
			List<Student> stuList = this.getAllStu();
			String info = "";
			String info2 = "";
			//将移除后的课程信息保存至文件中
			for (Courses courses : coursesList) {
				info += courses.getId() + "," + courses.getName() + ","
						+ courses.getAddress() +","+courses.getTime()+","+courses.getTeacherId()+ "\n";
			}
			//将学生选了该门课程的改成未选，并保存至文件中
			for (Student stuInfo : stuList) {
				if(stuInfo.getCourse().equals(name)){
					info2 += stuInfo.getId() + "," + stuInfo.getName() + ","+stuInfo.getSex()+","
							+ "未选" + "\n";
				}else{
					info2 += stuInfo.getId() + "," + stuInfo.getName() + ","+stuInfo.getSex()+","
							+ stuInfo.getCourse() + "\n";
				}
			}



			this.updateTxtInfo(info, Stream.COURSEINFOPATH);

			this.updateTxtInfo(info2, Stream.STUINFOPATH);

			System.out.println("删除成功！！");

		}
	}

	/**
	 * 注册教师
	 */
	public void addTeacher(){
		while (true) {
			//控制台获取教师编号
			System.out.print("请输入要增加的教师编号：");
			String stuNo = Stream.inputString().trim();
			//循环遍历教师信息，判断该教师是否已存在。
			boolean flag = false;
			List<Teacher> teacherList = this.getAllTeacher();
			for (Teacher teacher : teacherList) {
				if (teacher.getId().equals(stuNo)) {
					flag = true;
					break;
				}
			}

			if (flag) {
				System.out.println("该教师编号已经存在");
			} else {
				//教师不存在，则输入教师姓名，性别等
				System.out.print("请输入姓名：");
				String name = Stream.inputString().trim();
				System.out.print("请输入性别：");
				String sex = Stream.inputString().trim();

				//将新注册的教师，添加至文件中
				String info = "";
				for (Teacher teacher : teacherList) {
					info += teacher.getId() + "," + teacher.getName()+","+teacher.getSex()
							+ "," + teacher.getCourses() + "\n";

				}
				info += stuNo + "," + name +"," +sex+ ",未选" + "\n";

				// 将新增加的信息增加到文本文件中
				this.updateTxtInfo(info, Stream.TEACHERPATH);
				System.out.println("增加成功！");
				System.out.print("是否继续增加? (Y/N):");
				String contiun = Stream.inputString();
				//判断是否要继续添加教师信息,不是Y则退出
				if (!contiun.equalsIgnoreCase("Y")){

					return;
				}
			}

		}
	}




	/**
	 * 注册学生信息
	 */
	public void addStu() {

		while (true) {
			//控制台输入学号
			System.out.print("请输入要增加的学号：");
			String stuNo = Stream.inputString().trim();

			boolean flag = false;
			//获取所有学生信息
			List<Student> stuList = this.getAllStu();
			//判断学号是否存在
			for (Student stuInfo : stuList) {
				if (stuInfo.getId().equals(stuNo)) {
					flag = true;
					break;
				}
			}
			//学号存在，不允许添加
			if (flag) {
				System.out.println("该学号已经存在");
			} else {
				//学号不存在，则输入学生姓名、性别
				System.out.print("请输入姓名：");
				String stuName = Stream.inputString().trim();
				System.out.print("请输入性别：");
				String sex = Stream.inputString().trim();

				String info = "";
				//将所有学生信息转成字符串，以逗号隔开，每个学生占一行
				for (Student stuInfo : stuList) {
					info += stuInfo.getId() + "," + stuInfo.getName()+","+stuInfo.getSex()
							+ "," + stuInfo.getCourse() + "\n";

				}
				info += stuNo + "," + stuName +"," +sex+ ",未选" + "\n";

				// 将新增加的信息增加到文本文件中
				this.updateTxtInfo(info, Stream.STUINFOPATH);
				System.out.println("增加成功！");
				System.out.print("是否继续增加? (Y/N):");
				String contiun = Stream.inputString();
				//输入Y继续添加，否则退出循环
				if (!contiun.equalsIgnoreCase("Y")){

					return;
				}
			}

		}

	}

	/**
	 * 更新信息
	 * @param info
	 * @param filePath
	 */
	private void updateTxtInfo(String info, String filePath) {
		//根据文件路径获取文件流
		BufferedWriter bw = Stream.getOutput(filePath);
		try {
			//将info内容保存至文件中
			bw.write(info);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Stream.tryClose(null, bw);
		}

	}


	/**
	 * 查询学生
	 */
	public void searchStu() {
		System.out.print("请输入学号：");
		String stuNo = Stream.inputString();
		//获取所有学生信息
		List<Student> stuList = this.getAllStu();
		//循环遍历，根据输入的学号判断学生是否存在
		for (Student stuInfo : stuList) {
			if (stuInfo.getId().equals(stuNo)) {
				//打印学生信息
				System.out.println("学号：" + stuNo + "\t姓名："
						+ stuInfo.getName() +"\t姓别："+ stuInfo.getSex() + "\t选课:" + stuInfo.getCourse());
				return;
			}
		}
		System.out.println("该学号对应的学生不存在！");

	}

	/**
	 * 查询课程
	 */
	public void searchCou(){
		System.out.print("请输入课程编码：");
		String stuNo = Stream.inputString();
		//获取所有课程信息
		List<Courses> couList = this.getAllCourse();
		//循环遍历，根据输入的课程编号判断课程是否存在
		for (Courses courses : couList) {
			if (courses.getId().equals(stuNo)) {
				//打印课程信息
				System.out.println("课程编号：" + stuNo + "\t课程名称："
						+ courses.getName() + "\t课程地点:" + courses.getAddress()
						+ "\t课程时间:" + courses.getAddress()+ "\t教师id:" + courses.getTeacherId());
				return;
			}
		}
		System.out.println("该学号对应的课程不存在！");

	}


	/**
	 * 显示所有学生信息
	 */
	public void listStu() {
		//获取所有学生信息
		List<Student> stuList = getAllStu();
		//循环遍历，打印学生信息
		System.out.println("学号" + "\t姓名" +"\t性别"+ "\t选课");
		for (Student stuInfo : stuList) {
			System.out.println(stuInfo);
		}
	}

	/**
	 * 显示教师学生信息
	 */
	public void ListTeacher(){
		//获取所有教师信息
		List<Teacher> teacherList = getAllTeacher();
		//循环遍历，打印教师信息
		System.out.println("编号" + "\t姓名" +"\t性别"+ "\t授课");
		for (Teacher teacher : teacherList) {
			System.out.println(teacher);
		}

	}



	/**
	 * 显示学生选课信息
	 */
	public List<Courses> listCor() {

		List<Courses> coursesList = getAllCourse();

		System.out.println("编号" + "\t课程名称" +"\t课程地址"+ "\t课程时间"+"\t教师id");
		for (Courses courses : coursesList) {
			System.out.println(courses);
		}
		return  coursesList;
	}





	/**
	 * 获取所有课程
	 * @return
	 */
	private List<Courses> getAllCourse(){
		List<Courses> coursesList = new ArrayList<Courses>();
		//根据文件路径获取文件缓冲输入流BufferedReader
		BufferedReader br = Stream.getInput(Stream.COURSEINFOPATH);
		try {

			String stuRecord = null;
			//读取输入流直至结束（按行读取），并且将每一行内容赋值给变量stuRecord
			while ((stuRecord = br.readLine()) != null && !stuRecord.equals("")) {

				Courses courses = new Courses();
				//根据逗号将字符串stuRecord分割，分别保存在Courses对象中，并添加到Courses集合中
				String[] stuArr = stuRecord.split(",");
				courses.setId(stuArr[0]);
				courses.setName(stuArr[1]);
				courses.setAddress(stuArr[2]);
				courses.setTime(stuArr[3]);
				courses.setTeacherId(stuArr[4]);
				coursesList.add(courses);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Stream.tryClose(br, null);
		}

		return coursesList;
	}


	/**
	 * 获取学生信息，从文件中获取
	 * @return
	 */
	private List<Student> getAllStu() {// 组装学生信息
		List<Student> stuList = new ArrayList<Student>();
		//根据文件路径获取文件缓冲输入流BufferedReader
		BufferedReader br = Stream.getInput(Stream.STUINFOPATH);
		try {

			String stuRecord = null;
			//读取输入流直至结束（按行读取），并且将每一行内容赋值给变量stuRecord
			while ((stuRecord = br.readLine()) != null && !stuRecord.equals("")) {

				Student stuInfo = new Student();
				//根据逗号将字符串stuRecord分割，分别保存在Student对象中，并添加到Student集合中
				String[] stuArr = stuRecord.split(",");
				stuInfo.setId(stuArr[0]);
				stuInfo.setName(stuArr[1]);
				stuInfo.setSex(stuArr[2]);
				stuInfo.setCourse(stuArr[3]);
				stuList.add(stuInfo);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Stream.tryClose(br, null);
		}

		return stuList;
	}


	/**
	 * 从文件中获取教师信息
	 * @return
	 */
	private List<Teacher> getAllTeacher(){
		List<Teacher> teacherList = new ArrayList<Teacher>();
		//根据文件路径获取文件缓冲输入流BufferedReader
		BufferedReader br = Stream.getInput(Stream.TEACHERPATH);

		try {

			String stuRecord = null;
			//读取输入流直至结束（按行读取），并且将每一行内容赋值给变量stuRecord
			while ((stuRecord = br.readLine()) != null && !stuRecord.equals("")) {

				Teacher stuInfo = new Teacher();
				//根据逗号将字符串stuRecord分割，分别保存在Teacher对象中，并添加到Teacher集合中
				String[] stuArr = stuRecord.split(",");
				stuInfo.setId(stuArr[0]);
				stuInfo.setName(stuArr[1]);
				stuInfo.setSex(stuArr[2]);
				stuInfo.setCourses(stuArr[3]);

				teacherList.add(stuInfo);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Stream.tryClose(br, null);
		}

		return teacherList;
	}

}
