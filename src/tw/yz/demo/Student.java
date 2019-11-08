package tw.yz.demo;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tw.yz.domain.Extra;
import tw.yz.domain.ExtraName;
import tw.yz.model.stuDeclareDAOImpl;

public class Student {
	public void ExtraPoint() {
		Declare();
	}
	
	public static void Declare() {
		stuDeclareDAOImpl declare = new stuDeclareDAOImpl();
		
		Extra etr = new Extra();
		etr.setYear("2018~2019");
		etr.setS_no("170811008");
		
		/**
		 * 1.1 选择要申请的类别&明细
		 */
		
		// 选择类别
		
		System.out.println("\t请选择加分项所属类别: ");
		System.out.println("\t 1. 德育加分项: ");
		System.out.println("\t 2. 德育减分项: ");
		System.out.println("\t 3. 智育学术成果加分项: ");
		System.out.println("\t 4. 体育加分项: ");
		System.out.print("\t请输入序号: ");
		Scanner in = new Scanner(System.in);
		String k = in.next();
		while(true) {
			if(k.equals("1")) {
				etr.setExtra("me_etr");
				break;
			}
			else if(k.equals("2")) {
				etr.setExtra("me_mns");
				break;
			}
			else if(k.equals("3")) {
				etr.setExtra("ie_etr");
				break;
			}
			else if(k.equals("4")) {
				etr.setExtra("pe_etr");
				break;
			}
			else {
				System.out.println("\t输入错误! 请重新输入序号:");
				in = new Scanner(System.in);
				k = in.next();
			}
		}
		
		// 选择明细
		
		System.out.println("\t请选择要申报的加分项明细: ");
		
		Map<String,String> d_names = declare.queryDetails(etr.getExtra());
		for(String detail : d_names.keySet()) {
			System.out.println("\t "+ detail + ".\t" + d_names.get(detail));
		}
		
		System.out.print("\t请输入序号: ");
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
		System.out.println("\t** 加分项申请 **");
		System.out.println("\t学号: " + etr.getS_no());
		System.out.println("\t类别: " + etr.getExtra());
		System.out.println("\t加分项明细: " + d_names.get(etr.getDetail()));
		
		System.out.print("\t请输入得分: ");
		in = new Scanner(System.in);
		k = in.next();
		
		
		
	}
	
	
}
