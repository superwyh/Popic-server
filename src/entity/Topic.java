package entity;

public class Topic {
	private int tid;
	private int uid;
	private String tname;
	private String createtime;
	public Topic() {
		super();
	}
	public Topic(int tid, int uid, String tname, String createtime) {
		super();
		this.tid = tid;
		this.uid = uid;
		this.tname = tname;
		this.createtime = createtime;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
