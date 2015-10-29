package entity;

public class Users {
	private int uid;
	private String uname;
	private String upass;
	private String email;
	private int utype;
	// 使用facebook登录功能需要
	private String utoken;
	/* 使用消息推送需要的 字段 */
	/**
	 * iphone设备的id
	 */
	private String uiphoneid;
	/**
	 * 设备用户对程序的授权码
	 */
	private String uiphonetoken;
	public String getUiphoneid() {
		return uiphoneid;
	}
	public void setUiphoneid(String uiphoneid) {
		this.uiphoneid = uiphoneid;
	}
	public String getUiphonetoken() {
		return uiphonetoken;
	}
	public void setUiphonetoken(String uiphonetoken) {
		this.uiphonetoken = uiphonetoken;
	}
	public Users() {
		super();
	}
	public Users(int uid, String uname, String upass, String email, int utype) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upass = upass;
		this.email = email;
		this.utype = utype;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUtype() {
		return utype;
	}
	public void setUtype(int utype) {
		this.utype = utype;
	}
	public String getUtoken() {
		return utoken;
	}
	public void setUtoken(String utoken) {
		this.utoken = utoken;
	}
	
}
