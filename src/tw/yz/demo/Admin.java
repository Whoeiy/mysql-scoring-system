package tw.yz.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tw.yz.domain.ExtraName;
import tw.yz.domain.Student;
import tw.yz.model.AdminCRUD_DAO;
import tw.yz.model.AdminCheckDAO;

public class Admin {
	static AdminCRUD_DAO manage = new AdminCRUD_DAO();
	static AdminCheckDAO check = new AdminCheckDAO();
	public void Choose() {
		System.out.println("\n");
		System.out.println("\t** // 管理员, 欢迎你 \\ **");
		System.out.println("\t* 请选择功能: ");
		System.out.println("\t  1. \t管理学生信息");
		System.out.println("\t  2. \t管理课程信息");
		System.out.println("\t  3. \t管理学生成绩信息");
		System.out.println("\t  4. \t管理综测评审标准信息");
		System.out.println("\t  5. \t管理学生综测成绩信息");
		System.out.println("\t  6. \t退出系统");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		
		switch(k) {
			case "1":{
				Student();
				break;
			}
			case "2":{
//				Course();
				break;
			}
			case "3":{
//				Grade();
				break;
			}
			case "4":{
//				Standard();
				break;
			}
			case "5":{
				OverallQuality();
				break;
			}
			case "6":{
				System.exit(0);
			}
		}
		
	}
	
	
	
	
	public static void Student() {
		addStudent();
//		deleteStudent();
//		updateStudent();
	}
	
	public static  void addStudent(){
		System.out.println("请输入：");
		Scanner in =  new Scanner(System.in);
		Student stu = null;
		while(in.hasNextLine()){    
			String str = in.nextLine();
			String[] stustr = str.split(",");
			stu = new Student(stustr[0],stustr[1],stustr[2],stustr[3],stustr[4]);
            manage.addStudent(stu);
        }
    }
	
	public static void deleteStudent(){
		System.out.println("请输入：");
		Scanner in =  new Scanner(System.in);
		String sno = in.next();
		int flag = manage.deleteStudent(sno);
		if(flag == 1) {
			System.out.println("success");
		}
    }
	
	public static void updateStudent(){
		System.out.println("请输入：");
		Scanner in =  new Scanner(System.in);
		String str = in.next();
		String[] ustr = str.split(",");
		int flag = manage.updateStudent(ustr[0],ustr[1],Integer.parseInt(ustr[2]));
		if(flag == 1) {
			System.out.println("success");
		}
    }

	public static void OverallQuality() {
		//
		System.out.println("\n");
		System.out.println("\t** 管理学生综测成绩信息 **");
		System.out.println("\t* 请选择功能: ");
		System.out.println("\t  1. \t审核学生申报加分项信息");
		System.out.println("\t  2. \t汇总学生综合测评成绩信息");
		System.out.println("\t  3. \t退出系统");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		
		switch(k) {
		case "1":
			Check();
			break;
		case "2":
//			Collect();
			break;
		case "3":
			System.exit(0);
		}
	}

	public static void Check() {
		System.out.println("\n");
		System.out.println("\t** 审核学生申报加分项信息 **");
		System.out.println("\t* 请选择要审核的年份: ");
		Map<Integer,String> years = check.getYears();
		for(int i : years.keySet()) {
			System.out.println("\t "+ i + ".\t" + years.get(i));
		}
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		
		String y = null;	//选择的年份
		for(int i : years.keySet()) {
			if(i == Integer.parseInt(k)) {
				y = years.get(i);
				break;
			}
		}
		
		
		System.out.println("\t* 请选择要审核的班级: ");
		Map<String,String> classes= check.getClasses(y);
		Map<Integer,String> getcls  = new HashMap<Integer,String>();
		int i = 0;
		for(String cls : classes.keySet()) {
			++i;
			System.out.println("\t  "+i+". "+classes.get(cls));
			
			getcls.put(i, cls);
		}
		System.out.print("\t* 请输入序号: ");
		in = new Scanner(System.in);
		k = in.next();
		String c = null;	//选择的班级
		for(int j : getcls.keySet()) {
			if(getcls.get(j).equals(k)) {
				c = getcls.get(j);
				break;
			}
		}
		
	
	}
	
}

