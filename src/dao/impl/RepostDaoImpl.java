package dao.impl;

import java.sql.SQLException;
import java.util.*;

import entity.*;

public class RepostDaoImpl extends BaseDao{
	
	
	//回复帖子
	public int addRepost(Repost repost){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="insert into repost(pid,uid,recontent,retime) values(?,?,?,NOW())";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, repost.getPid());
			pstmt.setInt(2, repost.getUid());
			pstmt.setString(3, repost.getRecontent());
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return num;
	}
	//根据帖子id查询回帖信息
	public List<Repost> getInfoByPid(int pid){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select r.*,p.pname,u.uname from repost as r,posts as p,users as u where r.pid=p.pid and r.uid=u.uid and p.pid="+pid;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Repost repost=new Repost();
				repost.setRid(rs.getInt("rid"));
				repost.setPid(rs.getInt("pid"));
				repost.setUid(rs.getInt("uid"));
				repost.setPname(rs.getString("pname"));
				repost.setUname(rs.getString("uname"));
				repost.setRecontent(rs.getString("recontent"));
				repost.setRetime(rs.getString("retime"));
				list.add(repost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;	
	}
	
	//查询回复量最多的帖子
	public List getCount(int pid){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select pid,count(pid) as pnum from repost group by pid order by pnum desc";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Repost repost=new Repost();
				repost.setRid(rs.getInt("rid"));
				repost.setPid(rs.getInt("pid"));
				repost.setUid(rs.getInt("uid"));
				repost.setRecontent(rs.getString("recontent"));
				repost.setRetime(rs.getString("retime"));
				list.add(repost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;	
	}
}
