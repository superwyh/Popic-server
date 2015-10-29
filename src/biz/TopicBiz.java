package biz;

import java.util.*;

import entity.Topic;

public interface TopicBiz {
	public Topic getTopicbyname(String tname);            //根据贴吧名称查询贴吧信息
	public int addInfo(Topic topic);                    //创建贴吧
	public List getInfoByname(String tname);      //根据贴吧名称查询
	public int topicLogin(String tname);       //贴吧名验证
	public List getSaveTopic(int uid);            //用户存储的贴吧
	public List getHotTopic();          //历史最受欢迎的贴吧
	public List getChangeTopic();        //本周最新的贴吧
	public List getWeekhotTopic();       //上周最受欢迎的贴吧
}
