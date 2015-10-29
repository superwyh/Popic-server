package biz;

import entity.Users;

public interface UsersBiz {
	public Users getInfo();                    //获取全部
	public Users getInfoByid(int uid);        //根据uid查询用户信息
	public int addInfo(Users user);                     //添加用户信息
	public int getLoginByUsers(String name,String pass);      //用户验证
	public int updatePass(String name,String npass);        //修改密码
	public Users getUsersInfo(String name,String pass);        //查询当前用户信息
	public int getUsertoken(String token);		// 检查用户token是否存在
	/**
	 * 绑定用户和自己的手机
	 * @param user 需要知道用户的 uname 和 upass 来绑定
	 * @param iphoneid 手机id
	 * @param iphonetoken 用户授权码
	 */
	public void bindIphone(Users user,String iphoneid, String iphonetoken);
}
