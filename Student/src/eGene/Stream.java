package eGene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件操作类
 */
public class Stream {
	//学生信息文件路径
	public static final String STUINFOPATH = "./src/eGene/stuInfo.txt";
	//课程文件路径
	public static final String COURSEINFOPATH = "./src/eGene/course.txt";
	//教师信息文件路径
	public static final String TEACHERPATH = "./src/eGene/teacher.txt";


	//获取系统输入（控制台）
	public static String inputString() {
		String str = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			str = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str;
	}

	/**
	 * 根据文件路径获取字符缓冲输入流BufferedReader
	 * @param filePath
	 * @return
	 */
	public static BufferedReader getInput(String filePath) {

		BufferedReader br = null;

		try {
			File file = new File(filePath);
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return br;
	}

	/**
	 * 根据文件路径获取输出流BufferedWriter
	 * @param filePath
	 * @return
	 */
	public static BufferedWriter getOutput(String filePath) {
		BufferedWriter bw = null;

		File file = new File(filePath);

		try {
			bw = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bw;

	}

	/**
	 * 将BufferedReade，BufferedWriter流进行关闭
	 * @param br
	 * @param bw
	 */
	public static void tryClose(BufferedReader br, BufferedWriter bw) {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			br = null;
		}

		if (bw != null) {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bw = null;
		}
	}

}
