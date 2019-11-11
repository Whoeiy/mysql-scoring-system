package tw.yz.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tw.yz.domain.Extra;
import tw.yz.domain.Summary;
import tw.yz.domain.ExtraName;
import tw.yz.domain.Student;
import tw.yz.model.AdminCRUD_DAO;
import tw.yz.model.AdminCheckDAO;
import tw.yz.model.stuDeclareDAO;

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
			Collect();
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
		Map<Integer, Extra> r_extras = null;
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
			if(String.valueOf(j).equals(k)) {
				c = getcls.get(j);
				break;
			}
		}
		
		r_extras = s_getExtrasBy(y,c);		//打印选择年份&班级后的结果
		
		System.out.println("\t* 请选择后续要进行的操作: ");
		System.out.println("\t  1. \t开始审核");
		System.out.println("\t  2. \t返回主菜单");
		System.out.println("\t  3. \t退出系统");
		System.out.print("\t* 请输入序号: ");
		in = new Scanner(System.in);
		k = in.next();
		while(true) {
			if(k.equals("1")) {
				checkOneExtra(r_extras);
				break;
			}
			else if(k.equals("2")) {	//
				break;
			}
			else if(k.equals("3")) {	//退出系统
				System.exit(0);
			}
			else {
				System.out.println("\t* 输入错误! 请重新输入序号:");
				Scanner inn = new Scanner(System.in);
				k = in.next();
				inn.close();
			}
		}
	}
	
	public static void checkOneExtra(Map<Integer, Extra> extras) {
		System.out.println("\n");
		System.out.println("\t** 审核学生申报加分项信息 **");
		for(int i : extras.keySet()) {
			if(extras.get(i).getStatus().equals("0")) {
				System.out.println("\n");
				System.out.println("\t学号: " + extras.get(i).getS_no());
				System.out.println("\t姓名: " + extras.get(i).getYear());
				System.out.println("\t类别: " + j_extraName(extras.get(i).getExtra()));
				System.out.println("\t加分项名称: " + extras.get(i).getDetail());
				System.out.println("\t分值: " + extras.get(i).getS_point());
				System.out.println("\t说明: " + extras.get(i).getRemarks());
				
				System.out.println("\n");
				System.out.println("\t* 该项申请是否通过?(是 -- y; 否 -- n)");
				String pas = "0";
				System.out.print("\t* 请输入序号: ");
				Scanner in = new Scanner(System.in);
				String k = in.next();
				while(true) {
					if(k.equals("y")) {
						pas = "1";
						break;
					}
					else if(k.equals("n")) {	//
						break;
					}
					else {
						System.out.print("\t* 输入错误! 请重新输入序号:");
						Scanner inn = new Scanner(System.in);
						k = in.next();
						inn.close();
					}
				}
				String gp = "null";
				if(pas.equals("1")) {
					System.out.println("\t* 是否需要修改分值?(是 -- y; 否 -- n)");
					System.out.print("\t* 请输入序号: ");
					in = new Scanner(System.in);
					k = in.next();
					while(true) {
						if(k.equals("y")) {
							System.out.print("\t* 请输入审核分数: ");
							in = new Scanner(System.in);
							k = in.next();
							gp = k;
							break;
						}
						else if(k.equals("n")) {	//
							break;
						}
						else {
							System.out.print("\t* 输入错误! 请重新输入序号:");
							Scanner inn = new Scanner(System.in);
							k = in.next();
							inn.close();
						}
					}
				}
				int flag = check.checkOneExtra(extras.get(i).getS_no(),extras.get(i).getExtra(),extras.get(i).getG_point(),pas,gp);
				if(flag == 1) {
					System.out.print("\t* 审核成功!");	
				}
				System.out.println("\n\t*********************");
				f_following_op();
				
				
			}
			
			
			
			
		}
		
		
	}
	
	public static void Collect() {
		System.out.println("\n");
		System.out.println("\t** 汇总学生综合测评成绩信息 **");
		System.out.println("\t* 请选择汇总方式: ");
		System.out.println("\t  1. \t总体综测情况");
		System.out.println("\t  2. \t德育评分情况(未启用)");
		System.out.println("\t  3. \t智育评分情况(未启用)");
		System.out.println("\t  4. \t体育评分情况(未启用)");
		System.out.println("\t  5. \t返回主菜单");
		System.out.println("\t  6. \t退出系统");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		while(true) {
			if(k.equals("1")) {
				Summary();
				break;
			}
			else if(k.equals("2")) {	//
				break;
			}
			else if(k.equals("3")) {	//退出系统
				System.exit(0);
			}
			else {
				System.out.println("\t* 输入错误! 请重新输入序号:");
				Scanner inn = new Scanner(System.in);
				k = in.next();
				inn.close();
			}
		}
		
	}
	public static void Summary() {
		System.out.println("\n");
		System.out.println("\t** 汇总总体综测情况 **");
		System.out.println("\t* 请选择要汇总的年份: ");
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
		
		
		System.out.println("\t* 请选择要汇总的班级: ");
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
			if(String.valueOf(j).equals(k)) {
				c = getcls.get(j);
				break;
			}
		}
		
		s_getSummaryBy(y,c);		//打印选择年份&班级后的结果
		
		System.out.println("\t* 请选择后续要进行的操作: ");
		System.out.println("\t  1. \t返回主菜单");
		System.out.println("\t  2. \t退出系统");
		System.out.print("\t* 请输入序号: ");
		in = new Scanner(System.in);
		k = in.next();
		while(true) {
			if(k.equals("1")) {
				break;
			}
			else if(k.equals("2")) {	//
				break;
			}
			else if(k.equals("3")) {	//退出系统
				System.exit(0);
			}
			else {
				System.out.println("\t* 输入错误! 请重新输入序号:");
				Scanner inn = new Scanner(System.in);
				k = in.next();
				inn.close();
			}
		}
	}
	
	
	
	
	
	public static Map<Integer, Extra> s_getExtrasBy(String y, String c) {
		Map<Integer, Extra> r_extras = new HashMap<Integer, Extra>();
		r_extras = check.queryExtrasBy(y,c);
		System.out.println("\t| 序号 | 学号 | 姓名 | 类别 | 加分项名称 | 申请分数 | 审核分数 | 审核状态 | 是否通过 |");
		for(int i : r_extras.keySet()) {
			if(r_extras.get(i).getStatus().equals("0")) {
				//该条记录还未进行审核
				System.out.println("\t| "+i+" | "+r_extras.get(i).getS_no()+" | "+r_extras.get(i).getYear()+" | "+r_extras.get(i).getExtra()+" | "+r_extras.get(i).getDetail()+" | "+r_extras.get(i).getS_point()+" | -- | "+j_status(r_extras.get(i).getStatus())+" | -- |");
			}
			else {
				//该条记录已被审核
				System.out.println("\t| "+i+" | "+r_extras.get(i).getS_no()+" | "+r_extras.get(i).getYear()+" | "+r_extras.get(i).getExtra()+" | "+r_extras.get(i).getDetail()+" | "+r_extras.get(i).getS_point()+" | "+r_extras.get(i).getG_point()+" | "+j_status(r_extras.get(i).getStatus())+" | "+j_pass(r_extras.get(i).getPass())+" |");
			}
		}
		return r_extras;
	}

	
	public static void s_getSummaryBy(String y, String c) {
		Map<Integer, Summary> r_smry = new HashMap<Integer, Summary>();
		r_smry = check.collectAll(y,c);
		System.out.println("\t| 序号 | 学号 | 姓名 | 德育 | 智育 | 体育 | 总分 | 考试成绩 |");
		for(int i : r_smry.keySet()) {
			System.out.println("\t| "+i+" | "+r_smry.get(i).getS_no()+" | "+r_smry.get(i).getS_name()+" | "+r_smry.get(i).getS_me()+" | "+r_smry.get(i).getS_ie()+" | "+r_smry.get(i).getS_pe()+" | "+r_smry.get(i).getS_total()+" | "+r_smry.get(i).getS_grade()+" | ");
		}
	}
	public static String j_status(String sts){
		if(sts.equals("0")) {
			return "待审核";
		}
		else {
			return "已审核";
		}
	}
	
	// 判断并替换通过状态的中文名称
	public static String j_pass(String pas){
		if(pas.equals("0")) {
			return "不通过";
		}
		else {
			return "通过";
		}
	}

	public static String j_extraName(String etr)
	{
		if(etr.equals("ie_etr")) {
			return "智育加分项";
		}
		else if(etr.equals("pe_etr")) {
			return "体育加分项";
		}
		else if(etr.equals("me_etr")){
			return "德育加分项";
		}
		else {
			return "德育减分项";
		}
		
	}
	public static String j_getExtraName(String k) {
		String r = null;
		while(true) {
			if(k.equals("1")) {
				r = "me_etr";
				break;
			}
			else if(k.equals("2")) {
				r = "me_mns";
				break;
			}
			else if(k.equals("3")) {
				r = "ie_etr" ;
				break;
			}
			else if(k.equals("4")) {
				r = "pe_etr";
				break;
			}
			else {
				System.out.println("\t* 输入错误! 请重新输入序号:");
				Scanner in = new Scanner(System.in);
				k = in.next();
				in.close();
			}
		}
		return r;
	}
	public static void f_following_op() {
		System.out.println("\t* 请选择后续要进行的操作: ");
		System.out.println("\t  1. \t继续审核");
		System.out.println("\t  2. \t返回主菜单");
		System.out.println("\t  3. \t退出系统");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		while(true) {
			if(k.equals("1")) {
				break;
			}
			else if(k.equals("2")) {	//
				break;
			}
			else if(k.equals("3")) {	//退出系统
				System.exit(0);
			}
			else {
				System.out.println("\t* 输入错误! 请重新输入序号:");
				Scanner inn = new Scanner(System.in);
				k = in.next();
			}
		}
	}



}

