package dao.impl;

import java.sql.*;

public class BaseDao {
	//MySql数据库连接方式
	public static final String DRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/postbar";
	public static final String USER="root";
	public static final String PASS="admin";
	
	//MySql数据库连接方式
//	public static final String DRIVER="com.mysql.jdbc.Driver";
//	public static final String URL="jdbc:mysql://localhost:3306/postbar";
//	public static final String USER="root";
//	public static final String PASS="lichen820_xuhui1203";
	
	//SqlServer2005数据库连接方式
//	public static final String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	public static final String URL="jdbc:sqlserver://localhost:5566;databaseName=TopicBar";
//	public static final String USER="sa";
//	public static final String PASS="ss";
	
	public Connection conn;           //连接数据库
	public PreparedStatement pstmt;   //执行SQL语句
	public ResultSet rs;              //返回结果
	
	/**
	 * 数据库连接
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConn() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
		Connection conn=DriverManager.getConnection(URL, USER, PASS);
		return conn;
	}
	
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		//如果rs不为空，关闭rs
		if(rs!=null){
	          try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//如果pstmt不为空，关闭pstmt
	    if(pstmt!=null){
	    	try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	  //如果conn不为空，关闭conn
	    if(conn!=null){
	    	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public int executeSQL(String executeSql,String[] param){
		int num=0;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(executeSql);
			if(param!=null){
				for(int i=0;i<param.length;i++){
					pstmt.setString(i+1, param[1]);
				}
			}
			num=pstmt.executeUpdate();             //执行SQL语句
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll(conn,pstmt,null);         //释放资源
		}
		return 0;
	}
}
