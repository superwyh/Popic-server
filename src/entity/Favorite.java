package entity;

public class Favorite {
	private int fid;
	private int uid;
	private int tid;
	private int pid;
	public Favorite() {
		super();
	}
	public Favorite(int fid, int uid, int tid, int pid) {
		super();
		this.fid = fid;
		this.uid = uid;
		this.tid = tid;
		this.pid = pid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
