package dao.impl;

import java.sql.SQLException;

import entity.Favorite;

public class FavoriteDaoImpl extends BaseDao{
	//����û�����������
	public int addFaTopic(Favorite favorite){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="insert into favorite (uid,tid) values(?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, favorite.getUid());
			pstmt.setInt(2, favorite.getTid());
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return num;
	}
	//����û��洢������
	public int addFaPost(Favorite favorite){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="insert into favorite (uid,pid) values(?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, favorite.getUid());
			pstmt.setInt(2, favorite.getPid());
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return num;
	}
	//�ж������Ƿ��Ѵ洢
	public int topicLogin(int uid, int tid){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select count(*) from favorite where uid="+uid+" and tid="+tid;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return num;
	}
	//�ж������Ƿ��Ѵ洢
	public int postsLogin(int uid, int pid){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select count(*) from favorite where uid="+uid+" and pid="+pid;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return num;
	}
}
