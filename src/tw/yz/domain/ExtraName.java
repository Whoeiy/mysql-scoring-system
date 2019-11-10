package tw.yz.domain;

public class ExtraName {
	private String extra;
	private String detail;
	private String name;
	private String max;
	private String min;
	
	public ExtraName() {}
	
	public ExtraName(String extra, String detail, String name, String max, String min) {
		this.extra = extra;
		this.detail = detail;
		this.name = name;
		this.max = max;
		this.min = min;	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}
}
