package biz.impl;

import dao.impl.FavoriteDaoImpl;
import entity.Favorite;
import biz.FavoriteBiz;

public class FavoriteBizImpl implements FavoriteBiz{

	private FavoriteDaoImpl favoritedaoimpl =new FavoriteDaoImpl();
	public int addFaPost(Favorite favorite) {
		return favoritedaoimpl.addFaPost(favorite);
	}

	public int addFaTopic(Favorite favorite) {
		return favoritedaoimpl.addFaTopic(favorite);
	}

	public int topicLogin(int uid, int tid) {
		return favoritedaoimpl.topicLogin(uid, tid);
	}

	public int postsLogin(int uid, int pid) {
		return favoritedaoimpl.postsLogin(uid, pid);
	}

}
