# 计181 平逸飞 

## 一、实验目的 
分析学生选课系统的数据结构并且将文件中的数据以字符串形式读出；使用GUI窗体及其组件设计窗体界面让数据可视性更强同时完成读取和写入的功能；完成学生选课过程业务逻辑编程，实现选课退课开课的功能；基于文件保存并读取数据，新建文本文件，将文本文件中的数据在GUI中显示；处理异常，防止文件中出现空白字符。



## 二、实验要求 
1、内容：设计GUI窗体，支持学生注册、课程新加、学生选课、学生退课、打印学生选课列表等操作。
2、基于事件模型对业务逻辑编程，实现在界面上支持上述操作。
3、针对操作过程中可能会出现的各种异常，做异常处理
4、基于输入/输出编程，支持学生、课程、教师等数据的读写操作。
5、基于Github.com提交实验，包括实验SRC源文件夹程序、README.MD实验报告文档。



## 三、实验过程 
调用之前的学生选课系统的源代码和选课系统GUI的代码，在此基础上添加文件读取和写入操作。新建New Exception类用于排除文本文件空字符异常。在Gui类中将需要的文本读取和写入代码加入其中，在实例化Majorattribute类的参数时，通过文本读取的方法，转化为字符串，以“,”分隔后按顺序写入Majorattribute类的实例化中，在Gui类执行时调用，用以替代在代码内实例化的操作。在设置学生姓名、学号以及各个数据后，单击“确定”键，在GUI中的文本框得到数据的实例化体现，同时应用文件写入代码，将字符串写入文本文件中。


## 四、流程图 
![text]()



## 五、核心代码和注释 
```javascript
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

	//初始化
	private void init(MainView mainView) {

		Manager manager = new Manager();
		mainView.showOption();
		while (true) {
			//控制台输入
			String str = Stream.inputString();
			if (str.length() == 0) {
				System.out.println("No keyword entered ! try again...");
				System.out.print("your selection :");
				continue;
			}
      
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
		for (Teacher teacher : teacherList)
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
      
      	//学生信息文件路径
	public static final String STUINFOPATH = "./src/eGene/stuInfo.txt";
	//课程文件路径
	public static final String COURSEINFOPATH = "./src/eGene/course.txt";
	//教师信息文件路径
	public static final String TEACHERPATH = "./src/eGene/teacher.txt";
  
  	//打印学生信息
	@Override
	public String toString() {
		return getId()+"\t"+getName()+"\t"+getSex()+"\t"+getCourse();
```

## 六、运行截图


GUi截图：![text]()






## 七、编程感想
这是本学期最后一次实验，综合了前几次实验的功能，将需要的方法集于一炉，形成了最终的程序，选课系统的功能得到了完善与补充。我经历了老师给的例子打不开、GUI显示异常、无法获取数据等等问题，但我一一解决了，通过不断地尝试，最终终于调试出了合适的GUI界面来承载实验二的选课系统的功能，虽然做的GUI仍不漂亮也还有小bug，但是确实是比曾经的自己有所进步。
我还会继续完善我的Java知识储备，达到学以致用。
