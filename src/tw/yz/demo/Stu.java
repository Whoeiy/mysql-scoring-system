package tw.yz.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import tw.yz.domain.Extra;
import tw.yz.domain.ExtraName;
import tw.yz.domain.StuUser;
import tw.yz.model.stuDeclareDAO;

public class Stu {
//	static StuUser u_stu = new StuUser("170101008","2018~2019");
	public static void ExtraPoint(StuUser u_stu) {
		
		System.out.println("\n");
		System.out.println("\t** // 同学, 欢迎你 \\ **");
		System.out.println("\t* 请选择功能: ");
		System.out.println("\t  1. \t申请加(减)分项");
		System.out.println("\t  2. \t查看已申请的加(减)分项");
		System.out.println("\t  3. \t修改已申请的加(减)分项(未启用)");
		System.out.println("\t  4. \t删除已申请的加(减)分项(未启用)");
		System.out.println("\t  5. \t退出系统");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		if(k.equals("1")) {
			Declare(u_stu);
		}
		else if(k.equals("2")) {
			Search(u_stu);
		}
		else if(k.equals("3")) {
			
		}
		else if(k.equals("4")) {
			
		}
		else {
			System.exit(0);
		}
	}
	
	
	/**
	 * 1. 申请加分项
	 */
	
	public static void Declare(StuUser u_stu) {
		stuDeclareDAO declare = new stuDeclareDAO();
		
		Extra etr = new Extra();
		etr.setYear(u_stu.getYear());
		etr.setS_no(u_stu.getS_no());
		
		/**
		 * 1.1 选择要申请的类别&明细
		 */
		
		// 选择类别
		
		System.out.println("\n");
		System.out.println("\t** 加(减)分项申请 **");
		System.out.println("\t* 请选择加分项所属类别: ");
		System.out.println("\t  1. \t德育加分项");
		System.out.println("\t  2. \t德育减分项");
		System.out.println("\t  3. \t智育学术成果加分项");
		System.out.println("\t  4. \t体育加分项");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		etr.setExtra(j_getExtraName(k));
		
		// 选择明细
		
		System.out.println("\t* 请选择要申报的加分项明细: ");
		
		Map<String,ExtraName> d_names = declare.queryDetails(etr.getExtra());
		for(String detail : d_names.keySet()) {
			System.out.println("\t "+ detail + ".\t" + d_names.get(detail).getName());
		}
		
		System.out.print("\t* 请输入序号: ");
		in = new Scanner(System.in);
		k = in.next();
		
		for(String detail : d_names.keySet()) {
			if(detail.equals(k)) {
				etr.setDetail(detail);
				break;
			}
		}
		
		/**
		 *  1.2 提交分数与说明
		 */
		
		System.out.println("\n");
		System.out.println("\t** 加(减)分项申请 **");
		System.out.println("\t学号: " + etr.getS_no());
		System.out.println("\t类别: " + j_extraName(etr.getExtra()));
		System.out.println("\t加分项名称: " + d_names.get(etr.getDetail()).getName());
		System.out.println("\t分值: " + d_names.get(etr.getDetail()).getMin() + " ~ " + d_names.get(etr.getDetail()).getMax());
		
		System.out.print("\t* 请输入得分: ");
		in = new Scanner(System.in);
		k = in.next();
		int max = Integer.parseInt(d_names.get(etr.getDetail()).getMax());
		int min = Integer.parseInt(d_names.get(etr.getDetail()).getMin());
		j_whetherCross(max, min, k);
		etr.setS_point(k);
		etr.setG_point(k);
		
		System.out.print("\t* 请输入详细说明(如果没有请填入null): ");
		in = new Scanner(System.in);
		k = in.next();
		etr.setRemarks(k);
		
		
		etr.setStatus("0");
		etr.setPass("0");
		int flag = declare.insertExtra(etr);
		if(flag == 1) {
			System.out.println("\t* 提交成功!");
		}
		System.out.println("\t*********************");
		f_following_op(u_stu);

	}
	
	
	
	/**
	 * 2. 管理加分项
	 * 		2.1. 查看加分项
	 * 		2.2. 修改加分项(未启用)
	 * 		2.3. 删除加分项(未启用)
	 */
	
	/*
	 *  2.1 查看加分项
	 */
	
	public static void Search(StuUser u_stu) {
		
		System.out.println("\n");
		System.out.println("\t** 查看已申请的加分项 **");
		System.out.println("\t* 请选择想要继续的操作: ");
		System.out.println("\t  1. \t查看所有已申请的加分项");
		System.out.println("\t  2. \t按年份查看已申请的加分项(未启用)");
		System.out.println("\t  3. \t按类别查看已申请的加分项");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		while(true) {
			if(k.equals("1")) {	// 查看所有已申请的加分项
				// me_etr
				System.out.println("\n\t* 德育加分项");
				s_getExtrasBy(u_stu,"me_etr");
				
				// me_mns
				System.out.println("\n\t* 德育减分项");
				s_getExtrasBy(u_stu,"me_mns");
				 
				// ie_mtr
				System.out.println("\n\t* 智育加分项");
				s_getExtrasBy(u_stu,"ie_etr");
				
				// pe_mtr
				System.out.println("\n\t* 体育加分项");
				s_getExtrasBy(u_stu,"pe_etr");
				break;
			}
			else if(k.equals("2")) {	// 按年份查看已申请的加分项
				f_end(u_stu);
				break;
			}
			else if(k.equals("3")) {	// 按类别查看已申请的加分项
				System.out.println("\n");
				System.out.println("\t* 请选择要查看的类别: ");
				System.out.println("\t  1. \t德育加分项");
				System.out.println("\t  2. \t德育减分项");
				System.out.println("\t  3. \t智育学术成果加分项");
				System.out.println("\t  4. \t体育加分项");
				System.out.print("\t* 请输入序号: ");
				in = new Scanner(System.in);
				k = in.next();
				s_getExtrasBy(u_stu,j_getExtraName(k));
				break;
			}
			else {
				System.out.println("\t* 输入错误! 请重新输入序号:");
				in = new Scanner(System.in);
				k = in.next();
			}
		}
		System.out.println("\n\t*********************");
		f_following_op(u_stu);
	}
		
	
	/**
	 * 0. 函数工具
	 */
	//j_: 判断替换
	//s_: 查询
	//f_: 实现其他功能
	
	// 按类别打印某同学的加分项
	public static void s_getExtrasBy(StuUser u_stu,String etr) {
		Map<Integer, Extra> r_extras = new HashMap<Integer, Extra>();
		stuDeclareDAO search = new stuDeclareDAO();
		
		System.out.println("\t| 序号 | 年份 | 加分项名称 | 申请分数 | 审核分数 | 审核状态 | 是否通过 |");
		r_extras = search.queryExtrasBy(u_stu.getS_no(),etr);
		for(int i : r_extras.keySet()) {
			if(r_extras.get(i).getStatus().equals("0")) {
				//该条记录还未进行审核
				System.out.println("\t| "+i+" | "+r_extras.get(i).getYear()+" | "+r_extras.get(i).getDetail()+" | "+r_extras.get(i).getS_point()+" | -- | "+j_status(r_extras.get(i).getStatus())+" | -- |");
			}
			else {
				//该条记录已被审核
				System.out.println("\t| "+i+" | "+r_extras.get(i).getYear()+" | "+r_extras.get(i).getDetail()+" | "+r_extras.get(i).getS_point()+" | "+r_extras.get(i).getG_point()+" -- | "+j_status(r_extras.get(i).getStatus())+" | "+j_pass(r_extras.get(i).getPass())+" |");
			}
		}
	}
	
	// 判断并替换审核状态的中文名称
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
	
	//判断申请的分值是否超出该项范围
	public static void j_whetherCross(int max, int min, String k) {
		while(true) {
			int int_k = Integer.parseInt(k);
			if(int_k > max || int_k < min) {
				System.out.println("\t* 分值超出范围! 请重新输入:");
				Scanner in = new Scanner(System.in);
				k = in.next();
			}
			else {
				break;
			}
		}
	}
	
	// 获得加分项所属【类别】对应的【中文名称】
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
	
	// 获得输入值所对应的加分项所属【类别】【代号】
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
	
	// 后续操作指示函数
	public static void f_following_op(StuUser u_stu) {
		System.out.println("\t* 请选择后续要进行的操作: ");
		System.out.println("\t  1. \t继续申报加分项");
		System.out.println("\t  2. \t返回主菜单");
		System.out.println("\t  3. \t退出系统");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		while(true) {
			if(k.equals("1")) {
				Declare(u_stu);
				break;
			}
			else if(k.equals("2")) {	//
				ExtraPoint(u_stu);
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

	// 功能未启用后续操作指示函数
	public static void f_end(StuUser u_stu) {
		System.out.println("\t* 该功能未启用,请选择后续操作: ");
		System.out.println("\t  1. \t返回主菜单");
		System.out.println("\t  2. \t退出系统");
		System.out.print("\t* 请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		while(true) {
			if(k.equals("1")) {
				ExtraPoint(u_stu);
				break;
			}
			else if(k.equals("2")) {	//退出系统
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
