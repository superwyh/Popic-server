package entity;

public class Posts {
	private int pid;
	private int tid;
	private int uid;
	private String uname;
	private String pname;
	private String pcontent;
	private String pimage;
	private String pmusic;
	private String createtime;
	public Posts() {
		super();
	}
	public Posts(int pid, int tid, int uid, String uname, String pname,
			String pcontent, String pimage, String pmusic, String createtime) {
		super();
		this.pid = pid;
		this.tid = tid;
		this.uid = uid;
		this.uname = uname;
		this.pname = pname;
		this.pcontent = pcontent;
		this.pimage = pimage;
		this.pmusic = pmusic;
		this.createtime = createtime;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getPmusic() {
		return pmusic;
	}
	public void setPmusic(String pmusic) {
		this.pmusic = pmusic;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
