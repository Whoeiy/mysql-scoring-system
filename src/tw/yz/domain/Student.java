package tw.yz.domain;

/**
 * 学生信息
 */

public class Student {
	private String s_no;
	private String s_name;
	private String s_class;
	private String s_pro;
	private String s_feature;
	
	public Student() {}
	
	public Student(String sno, String sname, String sclass, String spro, String sfeature) {
		this.s_no  = sno;
		this.s_name = sname;
		this.s_class = sclass;
		this.s_pro = spro;
		this.s_feature = sfeature;
	}
	
	
	public String getS_no() {
		return s_no;
	}

	public void setS_no(String s_no) {
		this.s_no = s_no;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_class() {
		return s_class;
	}

	public void setS_class(String s_class) {
		this.s_class = s_class;
	}

	public String getS_pro() {
		return s_pro;
	}

	public void setS_pro(String s_pro) {
		this.s_pro = s_pro;
	}

	public String getS_feature() {
		return s_feature;
	}

	public void setS_feature(String s_feature) {
		this.s_feature = s_feature;
	}
}
