package biz;

import java.util.*;

import entity.Topic;

public interface TopicBiz {
	public Topic getTopicbyname(String tname);            //�����������Ʋ�ѯ������Ϣ
	public int addInfo(Topic topic);                    //��������
	public List getInfoByname(String tname);      //�����������Ʋ�ѯ
	public int topicLogin(String tname);       //��������֤
	public List getSaveTopic(int uid);            //�û��洢������
	public List getHotTopic();          //��ʷ���ܻ�ӭ������
	public List getChangeTopic();        //�������µ�����
	public List getWeekhotTopic();       //�������ܻ�ӭ������
}
