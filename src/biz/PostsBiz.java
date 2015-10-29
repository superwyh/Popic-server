package biz;

import java.util.List;

import entity.Posts;

public interface PostsBiz {
	public Posts getInfo(int pid);                 //��ѯȫ��
	public List getInfoByTid(int tid);        //��������id��ѯ��Ӧ������
	public List getInfoByName(String pname);    //��������������ģ����ѯ
	public int addInfo(Posts posts);                   //��������
	public int updateInfoByid(int pid);     //�޸�Ϊ�洢������
	public List getSavePost(int uid);         //��ѯ���ܻ�ӭ������
	public List getHotPost();          //��ѯ��ʷ���ܻ�ӭ������
	public List getChangePost();       //��ѯ�������µ�����
	public List getWeekhotPost();      //��ѯ�������ܻ�ӭ������
}
