package biz;

import java.util.List;

import entity.Repost;
import entity.Users;

public interface RepostBiz {
	public List getInfoByPid(int pid);        //��������id��ѯ������Ϣ
	public int addRepost(Repost repost);      //�ظ�����
	/**
	 * ���û��ֻ�������Ϣ
	 * @param user ��Ҫ֪���û��� uid,���uiphonetokenΪ�գ�������
	 * @param content ���͵�����
	 * @param regAddress ֤������Ӳ�̵�ʲô�ط�
	 */
	public void pushMessage(Users user,String content,String regAddress);
	/**
	 * ��ȡǰ50���ַ�
	 * @param title
	 * @return
	 */
	public String getPushMessage(String title);
}
