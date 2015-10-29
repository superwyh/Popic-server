package dao.impl;

import java.sql.SQLException;
import java.util.*;

import entity.History;
import entity.Posts;

public class HistoryDaoImpl extends BaseDao{
	//创建历史记录
	public int addHistory(History history){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="insert into history(pid,uid,visittime) values (?,?,NOW())";
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, history.getPid());
			pstmt.setInt(2, history.getUid());
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return num;
	}
	
	//查询历史记录
	public List getHistory(int uid){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select p.pid,p.tid,p.pname,h.uid from (select pid,uid,visittime from history where uid="+uid+" order by visittime desc) as h,posts as p where p.pid=h.pid group by p.pid order by h.visittime desc limit 0,10";
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
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
}
