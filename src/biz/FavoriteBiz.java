package biz;

import entity.Favorite;

public interface FavoriteBiz {
	public int addFaTopic(Favorite favorite);     //����û�����������
	public int addFaPost(Favorite favorite);      //����û��洢������
	public int topicLogin(int uid, int tid);      //�ж������Ƿ��Ѵ洢
	public int postsLogin(int uid, int pid);      //�ж������Ƿ��Ѵ洢
}
