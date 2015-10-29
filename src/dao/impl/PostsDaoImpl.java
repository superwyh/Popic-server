package dao.impl;

import java.sql.SQLException;
import java.util.*;

import entity.*;

public class PostsDaoImpl extends BaseDao{

    
	//根据帖子id查询帖子
	public Posts getInfo(int pid){
		Posts posts=new Posts();
    	try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select users.uname,posts.pid,posts.tid,posts.uid,posts.pname,posts.pimage,posts.pmusic,posts.pcontent from users,posts where posts.uid=users.uid and posts.pid="+pid;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				posts.setPid(rs.getInt("Pid"));
				posts.setTid(rs.getInt("Tid"));
				posts.setUid(rs.getInt("Uid"));
				posts.setUname(rs.getString("Uname"));
				posts.setPname(rs.getString("Pname"));
				posts.setPimage(rs.getString("Pimage"));
				posts.setPmusic(rs.getString("Pmusic"));
				posts.setPcontent(rs.getString("Pcontent"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
    	return posts;
    }
	
	//根据贴吧id查询相应的帖子
	public List getInfoByTid(int tid){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select u.uname,p.pid,p.tid,p.uid,p.pname from users as u,posts as p where p.uid=u.uid and p.tid="+tid;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Posts posts=new Posts();
				posts.setPid(rs.getInt("Pid"));
				posts.setTid(rs.getInt("Tid"));
				posts.setUid(rs.getInt("Uid"));
				posts.setUname(rs.getString("Uname"));
				posts.setPname(rs.getString("Pname"));
				list.add(posts);	    
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;	
	} 
	
	//根据帖子名进行模糊查询
	public List getInfoByName(String pname){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select users.uname,posts.pid,posts.tid,posts.uid,posts.pname,posts.pcontent,posts.pimage,posts.pmusic from users,posts where posts.uid=users.uid and posts.pname like '%"+pname+"%'";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Posts posts=new Posts();
				posts.setPid(rs.getInt("Pid"));
				posts.setTid(rs.getInt("Tid"));
				posts.setUid(rs.getInt("Uid"));
				posts.setUname(rs.getString("Uname"));
				posts.setPname(rs.getString("Pname"));
				list.add(posts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	//增加帖子
	public int addInfo(Posts posts){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="insert into posts(tid,uid,pname,pcontent,pimage,pmusic,createtime) values(?,?,?,?,?,?,NOW())";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, posts.getTid());
			pstmt.setInt(2, posts.getUid());
			pstmt.setString(3, posts.getPname());
			pstmt.setString(4, posts.getPcontent());
			pstmt.setString(5, posts.getPimage());
			pstmt.setString(6, posts.getPmusic());
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return num;
	}
	//查询用户存储的帖子
	public List getSavePost(int uid){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select p.pid,p.uid,p.tid,p.pname from posts as p,favorite as f where f.pid=p.pid and f.uid="+uid;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Posts posts=new Posts();
				posts.setPid(rs.getInt("Pid"));
				posts.setUid(rs.getInt("Uid"));
				posts.setTid(rs.getInt("Tid"));
				posts.setPname(rs.getString("Pname"));
				list.add(posts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	//修改为存储的帖子
	public int updateInfoByid(int pid){
		Posts posts=new Posts();
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="update posts set ptype=2 where pid="+pid;
		try {
			pstmt=conn.prepareStatement(sql);
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return num;	
	}
	
	//查询历史最受欢迎的帖子
	public List getHotPost(){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select a.*,b.num from posts a join(select c.pid,count(c.pid) as num from repost c group by c.pid order by count(c.pid) desc limit 0, 3) b on a.pid=b.pid order by b.num desc";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Posts posts=new Posts();
				posts.setTid(rs.getInt("Tid"));
				posts.setPid(rs.getInt("Pid"));
				posts.setUid(rs.getInt("Uid"));
				posts.setPname(rs.getString("Pname"));
				list.add(posts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	//查询本周最新的帖子
	public List getChangePost(){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select * from posts where TO_DAYS(NOW())-TO_DAYS(createtime)<=40";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Posts posts=new Posts();
				posts.setTid(rs.getInt("Tid"));
				posts.setPid(rs.getInt("Pid"));
				posts.setUid(rs.getInt("Uid"));
				posts.setPname(rs.getString("Pname"));
				list.add(posts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	//查询本周最受欢迎的帖子
	public List getWeekhotPost(){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select A.* from (select a.*,b.num from posts a join(select c.pid,count(c.pid) as num from repost c group by c.pid order by count(c.pid) desc limit 0, 3) b on a.pid=b.pid order by b.num desc) as A where TO_DAYS(NOW())-TO_DAYS(createtime)<=50";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Posts posts=new Posts();
				posts.setTid(rs.getInt("Tid"));
				posts.setPid(rs.getInt("Pid"));
				posts.setUid(rs.getInt("Uid"));
				posts.setPname(rs.getString("Pname"));
				list.add(posts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
}
