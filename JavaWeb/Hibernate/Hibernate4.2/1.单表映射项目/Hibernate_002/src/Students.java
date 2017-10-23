import java.util.Date;

/**
 * 学生类 一个JavaBean的设计必须遵循四点 1.必须是个公有的类 2.必须提供公有的不带参数的构造方法 3.属性私有
 * 4.属性setter/getter封装
 */
public class Students {
	private int sid;
	private String sname;
	private String genfer;
	private Date birthday;
	private String address;

	public Students() {

	}

	public Students(int sid, String sname, String genfer, Date birthday, String address) {
		// super();
		this.sid = sid;
		this.sname = sname;
		this.genfer = genfer;
		this.birthday = birthday;
		this.address = address;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGenfer() {
		return genfer;
	}

	public void setGenfer(String genfer) {
		this.genfer = genfer;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Students [sid=" + sid + ", sname=" + sname + ", genfer=" + genfer + ", birthday=" + birthday
				+ ", address=" + address + "]";
	}

}
