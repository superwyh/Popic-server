package biz;

import java.util.List;

import entity.Repost;
import entity.Users;

public interface RepostBiz {
	public List getInfoByPid(int pid);        //根据帖子id查询回帖信息
	public int addRepost(Repost repost);      //回复帖子
	/**
	 * 给用户手机推送信息
	 * @param user 需要知道用户的 uid,如果uiphonetoken为空，则不推送
	 * @param content 推送的内容
	 * @param regAddress 证书所在硬盘的什么地方
	 */
	public void pushMessage(Users user,String content,String regAddress);
	/**
	 * 截取前50个字符
	 * @param title
	 * @return
	 */
	public String getPushMessage(String title);
}
