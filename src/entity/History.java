package entity;

public class History {
	private int hid;
	private int pid;
	private int uid;
	private String visittime;
	public History() {
		super();
	}
	public History(int hid, int pid, int uid, String visittime) {
		super();
		this.hid = hid;
		this.pid = pid;
		this.uid = uid;
		this.visittime = visittime;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
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
	public String getVisittime() {
		return visittime;
	}
	public void setVisittime(String visittime) {
		this.visittime = visittime;
	}
	
}
