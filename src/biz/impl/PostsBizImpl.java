package biz.impl;

import java.util.List;

import dao.impl.PostsDaoImpl;
import entity.Posts;

import biz.PostsBiz;

public class PostsBizImpl implements PostsBiz{
private PostsDaoImpl postsdaoimpl=new PostsDaoImpl();
	
	public Posts getInfo(int pid) {	
		return postsdaoimpl.getInfo(pid);
	}

	public List getInfoByTid(int tid) {
		return postsdaoimpl.getInfoByTid(tid);
	}

	public List getSavePost(int uid) {
		return postsdaoimpl.getSavePost(uid);
	}

	public int addInfo(Posts posts) {
		return postsdaoimpl.addInfo(posts);
	}

	public List getInfoByName(String pname) {
		return postsdaoimpl.getInfoByName(pname);
	}

	public int updateInfoByid(int pid) {
		return postsdaoimpl.updateInfoByid(pid);
	}

	public List getHotPost() {
		return postsdaoimpl.getHotPost();
	}

	public List getChangePost() {
		return postsdaoimpl.getChangePost();
	}

	public List getWeekhotPost() {
		return postsdaoimpl.getWeekhotPost();
	}

}
