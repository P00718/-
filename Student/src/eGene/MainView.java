package eGene;

public class MainView {


	public static void main(String[] args) {
		//初始化面板
		MainView mainView = new MainView();
		mainView.init(mainView);
	}

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

			char c = str.charAt(0);
			switch (c) {
				case 'a':
					//主菜单
					mainView.showOption();
					break;
				case 'b':
					//搜索学生信息
					manager.searchStu();
					mainView.showOption();
					break;
				case 'c':
					//搜索课程
					manager.searchCou();
					mainView.showOption();
					break;
				case 'd':
					//新增学生
					manager.addStu();
					mainView.showOption();
					break;
				case 'e':
					//删除学生
					manager.deleteStu();
					mainView.showOption();
					break;
				case 'f':
					//查看学生列表
					manager.listStu();
					mainView.showOption();
					break;
				case 'g':
					//删除课程
					manager.deleteCou();
					mainView.showOption();
					break;
				case 'h':
					//学生选课
					manager.choose();
					mainView.showOption();
					break;
				case 'i':
					//查看教师列表
					manager.ListTeacher();
					mainView.showOption();
					break;
				case 'j':
					//添加教师
					manager.addTeacher();
					mainView.showOption();
					break;
				case 'k':
					//删除教师
					manager.deleteTeacher();
					mainView.showOption();
					break;
				case 'l':
					//教师授课
					manager.teacherChoose();
					mainView.showOption();
					break;
				case 'm':
					System.out.println("退出系统");
					System.exit(0);
					break;

				default:
					System.out.println("Error Input,Select Again !");
					System.out.print("your selection :");
			}
		}

	}

	//主菜单
	private void showOption() {

		System.out
				.println("\n  [a]主菜单 \t [b]查询学生 [c]查询课程       [d]学生注册  \t[e]删除学生\n"
						+ " [f]查看所有学生选课信息  [g]删除课程\t [h]学生选课 \t[i]查看教师\t [j]注册教师 " +
						"\t [k]删除教师 \t [l]教师授课 \t [m]exit \n\t");
		System.out.print("your selection :");

	}
}
