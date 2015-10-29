package test;

import java.util.List;

import dao.impl.HistoryDaoImpl;

import entity.History;
import entity.Posts;
import entity.Topic;
import entity.Users;
import biz.FavoriteBiz;
import biz.HistoryBiz;
import biz.PostsBiz;
import biz.TopicBiz;
import biz.UsersBiz;
import biz.impl.FavoriteBizImpl;
import biz.impl.HistoryBizImpl;
import biz.impl.PostsBizImpl;
import biz.impl.TopicBizImpl;
import biz.impl.UsersBizImpl;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		UsersBiz usersbiz=new UsersBizImpl();
//		Users user=new Users();
////		user.setUname("james");
////		user.setUpass("123456");
////		user.setEmail("gg@qq.com");
//		String uname="eqw";
//		String upass="jijiji";
//		int num=usersbiz.addInfo(user);
//		System.out.println("*********************");
//		System.out.println(num+"222222");
		
//		TopicBiz topicbiz=new TopicBizImpl();
//		List list=topicbiz.getChangeTopic();
//		for(int i=0;i<list.size();i++){
//			Topic t=(Topic)list.get(i);
//			System.out.println(t.getTname());
//		}
		
//		PostsBiz postsbiz=new PostsBizImpl();
//		List list=postsbiz.getHotPost();
//		for(int i=0;i<list.size();i++){
//			Posts t=(Posts)list.get(i);
//			System.out.println(t.getPcontent());
//		}
		
//		PostsBiz postsbiz=new PostsBizImpl();
//		Posts posts=new Posts();
//		posts.setTid(9);
//		posts.setUid(9);
//		posts.setPname("wwwww");
//		posts.setPcontent("wwwwwwwwwwww");
//		posts.setPimage("s");
//		posts.setPmusic("s");
//		int num=postsbiz.addInfo(posts);
//		System.out.print(num);
		
//		HistoryBiz historybiz=new HistoryBizImpl();
//		History history=new History();
//		history.setPid(2);
//		history.setUid(1);
//		int numm=historybiz.addHistory(history);
//		System.out.print(numm+"****");
		
		FavoriteBiz favoritebiz=new FavoriteBizImpl();
		int num=favoritebiz.postsLogin(28, 1);
		System.out.print(num);
	}

}
