package entity;

public class Repost {
	private int rid;
	private int pid;
	private int uid;
	private String pname;
	private String uname;
	private String recontent;
	private String retime;
	public Repost() {
		super();
	}
	public Repost(int rid, int pid, int uid, String pname, String uname,
			String recontent, String retime) {
		super();
		this.rid = rid;
		this.pid = pid;
		this.uid = uid;
		this.pname = pname;
		this.uname = uname;
		this.recontent = recontent;
		this.retime = retime;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getRecontent() {
		return recontent;
	}
	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}
	public String getRetime() {
		return retime;
	}
	public void setRetime(String retime) {
		this.retime = retime;
	}

	
}
