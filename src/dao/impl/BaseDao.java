package dao.impl;

import java.sql.*;

public class BaseDao {
	//MySql���ݿ����ӷ�ʽ
	public static final String DRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/postbar";
	public static final String USER="root";
	public static final String PASS="admin";
	
	//MySql���ݿ����ӷ�ʽ
//	public static final String DRIVER="com.mysql.jdbc.Driver";
//	public static final String URL="jdbc:mysql://localhost:3306/postbar";
//	public static final String USER="root";
//	public static final String PASS="lichen820_xuhui1203";
	
	//SqlServer2005���ݿ����ӷ�ʽ
//	public static final String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	public static final String URL="jdbc:sqlserver://localhost:5566;databaseName=TopicBar";
//	public static final String USER="sa";
//	public static final String PASS="ss";
	
	public Connection conn;           //�������ݿ�
	public PreparedStatement pstmt;   //ִ��SQL���
	public ResultSet rs;              //���ؽ��
	
	/**
	 * ���ݿ�����
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
		//���rs��Ϊ�գ��ر�rs
		if(rs!=null){
	          try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//���pstmt��Ϊ�գ��ر�pstmt
	    if(pstmt!=null){
	    	try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	  //���conn��Ϊ�գ��ر�conn
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
			num=pstmt.executeUpdate();             //ִ��SQL���
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll(conn,pstmt,null);         //�ͷ���Դ
		}
		return 0;
	}
}
