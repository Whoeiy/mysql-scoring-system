package tw.yz.domain;

/**
 * 学生综测总评
 */

public class Summary {
	private String s_no;
	private String s_name;
	private String s_me;
	private String s_ie;
	private String s_pe;
	private String s_total;
	private String s_grade;
	
	public Summary(String sno, String sname, String sme, String sie, String spe, String stotal, String sg) {
		this.s_no = sno;
		this.s_name = sname;
		this.s_me = sme;
		this.s_ie = sie;
		this.s_pe = spe;
		this.s_total = stotal;
		this.s_grade = sg;
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
	public String getS_me() {
		return s_me;
	}
	public void setS_me(String s_me) {
		this.s_me = s_me;
	}
	public String getS_ie() {
		return s_ie;
	}
	public void setS_ie(String s_ie) {
		this.s_ie = s_ie;
	}
	public String getS_pe() {
		return s_pe;
	}
	public void setS_pe(String s_pe) {
		this.s_pe = s_pe;
	}
	public String getS_total() {
		return s_total;
	}
	public void setS_total(String s_total) {
		this.s_total = s_total;
	}
	public String getS_grade() {
		return s_grade;
	}
	public void setS_grade(String s_grade) {
		this.s_grade = s_grade;
	}
	
	
}
