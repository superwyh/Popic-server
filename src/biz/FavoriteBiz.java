package biz;

import entity.Favorite;

public interface FavoriteBiz {
	public int addFaTopic(Favorite favorite);     //标记用户存贮的贴吧
	public int addFaPost(Favorite favorite);      //标记用户存储的帖子
	public int topicLogin(int uid, int tid);      //判断贴吧是否已存储
	public int postsLogin(int uid, int pid);      //判断贴子是否已存储
}
