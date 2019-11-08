package tw.yz.domain;

public class Extra {
	private String year;
	private String s_no;
	private String extra;
	private String detail;
	private String remarks;
	private String s_point;
	private String g_point;
	private String status;
	private String pass;
	
	public Extra() {}
	
	public Extra(String year, String s_no, String extra, String detail, String remarks, String s_point, String g_point, String status, String pass) {
		this.year = year;
		this.s_no = s_no;
		this.extra = extra;
		this.detail = detail;
		this.remarks = remarks;
		this.s_point = s_point;
		this.g_point = g_point;
		this.status = status;
		this.pass = pass;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getS_point() {
		return s_point;
	}
	public void setS_point(String s_point) {
		this.s_point = s_point;
	}
	public String getG_point() {
		return g_point;
	}
	public void setG_point(String g_point) {
		this.g_point = g_point;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
