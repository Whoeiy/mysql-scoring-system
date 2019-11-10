package tw.yz.domain;

public class StuUser {
	private String s_no;
	private String year;
	
	public StuUser(String sno, String year){
		this.s_no = sno;
		this.year = year;
	}
	public String getS_no() {
		return s_no;
	}
	public void setS_no(String s_no) {
		this.s_no = s_no;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
}
