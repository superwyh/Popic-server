package biz;

import entity.Users;

public interface UsersBiz {
	public Users getInfo();                    //��ȡȫ��
	public Users getInfoByid(int uid);        //����uid��ѯ�û���Ϣ
	public int addInfo(Users user);                     //����û���Ϣ
	public int getLoginByUsers(String name,String pass);      //�û���֤
	public int updatePass(String name,String npass);        //�޸�����
	public Users getUsersInfo(String name,String pass);        //��ѯ��ǰ�û���Ϣ
	public int getUsertoken(String token);		// ����û�token�Ƿ����
	/**
	 * ���û����Լ����ֻ�
	 * @param user ��Ҫ֪���û��� uname �� upass ����
	 * @param iphoneid �ֻ�id
	 * @param iphonetoken �û���Ȩ��
	 */
	public void bindIphone(Users user,String iphoneid, String iphonetoken);
}
