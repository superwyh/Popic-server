package biz;

import java.util.List;

import entity.Posts;

public interface PostsBiz {
	public Posts getInfo(int pid);                 //查询全部
	public List getInfoByTid(int tid);        //根据贴吧id查询相应的帖子
	public List getInfoByName(String pname);    //根据帖子名进行模糊查询
	public int addInfo(Posts posts);                   //增加帖子
	public int updateInfoByid(int pid);     //修改为存储的帖子
	public List getSavePost(int uid);         //查询最受欢迎的帖子
	public List getHotPost();          //查询历史最受欢迎的帖子
	public List getChangePost();       //查询本周最新的帖子
	public List getWeekhotPost();      //查询本周最受欢迎的帖子
}
