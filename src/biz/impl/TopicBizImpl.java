package biz.impl;

import java.util.List;

import dao.impl.TopicDaoImpl;

import entity.Topic;
import biz.TopicBiz;

public class TopicBizImpl implements TopicBiz{
   private TopicDaoImpl topicDaoimpl=new TopicDaoImpl();
   	
	public int addInfo(Topic topic) {
		return topicDaoimpl.addInfo(topic);
	}

	public Topic getTopicbyname(String tname){
		return topicDaoimpl.getTopicbyname(tname);
	}

	public List getInfoByname(String tname) {
		return topicDaoimpl.getInfoByname(tname);
	}

	public int topicLogin(String tname) {
		return topicDaoimpl.topicLogin(tname);
	}

	public List getSaveTopic(int uid) {
		return topicDaoimpl.getSaveTopic(uid);
	}

	public List getHotTopic() {
		return topicDaoimpl.getHotTopic();
	}

	public List getChangeTopic() {
		return topicDaoimpl.getChangeTopic();
	}

	public List getWeekhotTopic() {
		return topicDaoimpl.getWeekhotTopic();
	}
	
}
