package dao.impl;

import java.sql.SQLException;

import entity.*;

public class UsersDaoImpl extends BaseDao{
	Users users=new Users();
	//获取全部
	public Users getInfo(){
	
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select * from users";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				users.setUid(rs.getInt("Uid"));
				users.setUname(rs.getString("Uname"));
				users.setUpass(rs.getString("Upass"));
				users.setEmail(rs.getString("Email"));
				users.setUtype(rs.getInt("Utype"));
				users.setUiphoneid(rs.getString("uiphoneid"));
				users.setUiphonetoken(rs.getString("uiphonetoken"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return users;
	}
	
	//根据uid查询用户信息
	public Users getInfoByid(int uid){
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select * from users where uid="+uid;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				users.setUid(rs.getInt("Uid"));
				users.setUname(rs.getString("Uname"));
				users.setUpass(rs.getString("Upass"));
				users.setEmail(rs.getString("Email"));
				users.setUtype(rs.getInt("Utype"));
				users.setUiphoneid(rs.getString("uiphoneid"));
				users.setUiphonetoken(rs.getString("uiphonetoken"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return users;
	}
	
	//添加用户信息
	@SuppressWarnings("static-access")
	public int addInfo(Users user){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="insert into users (uname,upass,email,utype,utoken,uiphoneid,uiphonetoken) values (?,?,?,1,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUname());
			pstmt.setString(2, user.getUpass());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUtoken());
			pstmt.setString(5, user.getUiphoneid());
			pstmt.setString(6, user.getUiphonetoken());
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return num;
		// session.save(user);
	}
	
	//用户验证
	public int getLoginByUsers(String name,String pass){
        int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select count(*) from users where uname='"+name+"' and upass='"+pass+"'";
		try {
			pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
            	num=rs.getInt(1);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, rs);
		}
		return num;	
		// session.find(from user where uaneme='' and upass='')
	}
	// 检查用户token是否存在
	public int getUsertoken(String token){
		String sql = "select count(*) from users where utoken='"+token+"'";
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
            	num=rs.getInt(1);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, rs);
		}
		return num;	
	}
	
	//修改密码
	public int updatePass(String name,String npass){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="update users set upass='"+npass+"' where uname='"+name+"'";
		try {
			pstmt=conn.prepareStatement(sql);
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return num;
		
		// session.update(user);
	}
	
	//查询当前用户信息
	public Users getUsersInfo(String name,String pass){
		Users users=new Users();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select * from users where uname='"+name+"' and upass='"+pass+"'";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				users.setUid(rs.getInt("Uid"));
				users.setUname(rs.getString("Uname"));
				users.setUpass(rs.getString("Upass"));
//				users.setEmail(rs.getString("Email"));
//				users.setUtype(rs.getInt("Utype"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return users;
		// session.find(from user where uaneme='' and upass='')
	}
	
	public void bindIphone(Users user,String iphoneid,String iphonetoken){
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "update users set uiphoneid=?,uiphonetoken=? where uid=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, iphoneid);
			pstmt.setString(2, iphonetoken);
			pstmt.setInt(3, user.getUid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
