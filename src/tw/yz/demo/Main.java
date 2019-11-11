package tw.yz.demo;

import java.util.Scanner;

import tw.yz.domain.StuUser;
import tw.yz.model.stuDeclareDAO;

public class Main {
	public static void main(String[] args)
	{
		
		
		System.out.println("\t** // 欢迎使用综合测评系统 \\ **");
		System.out.println("\t* 请选择用户角色: ");
		System.out.println("\t  1. \t学生");
		System.out.println("\t  2. \t管理员");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		if(k.equals("1")) {
			System.out.print("\t* 请输入学号: ");
			in = new Scanner(System.in);
			k = in.next();
			stuDeclareDAO stud = new stuDeclareDAO();
			int flag = stud.hasStudent(k);
			if(flag == 1) {
				StuUser st = new StuUser(k,"2018~2019");
				Stu stu = new Stu();
				stu.ExtraPoint(st);
			}
			else {
				System.out.println("* 您不在系统中，无法进入!");
				System.exit(0);
			}
		}
		else if(k.equals("2")) {
			System.out.print("\t* 请输入工号: ");
			in = new Scanner(System.in);
			k = in.next();
			if(!k.isEmpty()) {
				Admin admin = new Admin();
				admin.Choose();
			}
			else {
				System.out.println("* 您不在系统中，无法进入!");
				System.exit(0);
			}
		}
		
		
	}
}
