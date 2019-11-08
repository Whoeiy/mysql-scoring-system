package tw.yz.demo;

import java.util.Scanner;

import tw.yz.domain.Student;
import tw.yz.model.adminManageDAOImpl;

public class Admin {
	static adminManageDAOImpl manage = new adminManageDAOImpl();
	
	public void Student() {
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
}

