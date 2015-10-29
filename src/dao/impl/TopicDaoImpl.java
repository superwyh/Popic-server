package dao.impl;

import java.sql.SQLException;
import java.util.*;

import entity.*;

public class TopicDaoImpl extends BaseDao{
	
	//�����������ƻ�ȡ������Ϣ
	public Topic getTopicbyname(String tname){
		Topic topic=new Topic();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select * from topic where tname='"+tname+"'";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				topic.setTid(rs.getInt("tid"));
				topic.setUid(rs.getInt("uid"));
				topic.setTname(rs.getString("tname"));
				topic.setCreatetime(rs.getString("createtime"));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return topic;	
	}
	
	//��������
	public int addInfo(Topic topic){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="insert into topic (uid,tname,createtime) values (?,?,NOW())";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, topic.getUid());
			pstmt.setString(2, topic.getTname()); 
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return num;
	}
	
	//�����������Ʋ�ѯ
	public List getInfoByname(String tname){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select * from topic where tname like '%"+tname+"%'";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Topic topic=new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setUid(rs.getInt("uid"));
				topic.setTname(rs.getString("tname"));
				topic.setCreatetime(rs.getString("createtime"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;	
	}
	
	
	//��������֤
	public int topicLogin(String tname){
		int num=0;
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select count(*) from topic where tname='"+tname+"'";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		return num;
	}
	
	//��ʷ���ܻ�ӭ������
	public List getHotTopic(){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select a.*,b.num from topic a join (select c.tid, count(c.tid) as num from posts c group by c.tid order by count(c.tid) desc limit 0, 3) b on a.tid=b.tid order by b.num desc";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Topic topic =new Topic();
				topic.setTid(rs.getInt("Tid"));
				topic.setUid(rs.getInt("Uid"));
				topic.setTname(rs.getString("Tname"));
				topic.setCreatetime(rs.getString("Createtime"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	//һ�������µ�����
	public List getChangeTopic(){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// select * from topic where datediff(day,createtime,NOW())<30
		// select * from topic where TO_DAYS(NOW())-TO_DAYS(createtime)<=60    60�����ڵ�
		String sql="select * from topic where TO_DAYS(NOW())-TO_DAYS(createtime)<=60";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Topic topic =new Topic();
				topic.setTid(rs.getInt("Tid"));
				topic.setUid(rs.getInt("Uid"));
				topic.setTname(rs.getString("Tname"));
				topic.setCreatetime(rs.getString("Createtime"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	//��ѯ�û��洢������
	public List getSaveTopic(int uid){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select t.tid,t.uid,t.tname from topic as t,favorite as f where f.tid=t.tid and f.uid="+uid;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Topic topic =new Topic();
				topic.setTid(rs.getInt("Tid"));
				topic.setUid(rs.getInt("Uid"));
				topic.setTname(rs.getString("Tname"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	//�������ܻ�ӭ������
	public List getWeekhotTopic(){
		List list=new ArrayList();
		try {
			conn=this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql="select A.* from (select a.*,b.num from topic a join(select c.tid,count(c.tid) as num from posts c group by c.tid order by count(c.tid) desc limit 0, 3) b on a.tid=b.tid order by b.num desc) as A where TO_DAYS(NOW())-TO_DAYS(createtime)<=50";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Topic topic =new Topic();
				topic.setTid(rs.getInt("Tid"));
				topic.setUid(rs.getInt("Uid"));
				topic.setTname(rs.getString("Tname"));
				topic.setCreatetime(rs.getString("Createtime"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeAll(conn, pstmt, rs);
		}
		return list;
	}
}
