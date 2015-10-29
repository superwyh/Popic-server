package biz.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.json.JSONException;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;
import javapns.exceptions.NullIdException;
import javapns.exceptions.UnknownDeviceException;
import dao.impl.UsersDaoImpl;
import entity.Users;
import biz.UsersBiz;

public class UsersBizImpl implements UsersBiz{
private UsersDaoImpl usersdaoimpl=new UsersDaoImpl();
	
	public Users getInfo() {
		return usersdaoimpl.getInfo();
	}

	public int addInfo(Users user) {
		return usersdaoimpl.addInfo(user);
	}

	public int getLoginByUsers(String name, String pass) {
		return usersdaoimpl.getLoginByUsers(name, pass);
	}

	public int updatePass(String name, String npass) {
		return usersdaoimpl.updatePass(name, npass);
	}

	public Users getUsersInfo(String name,String pass) {
		return usersdaoimpl.getUsersInfo(name,pass);
	}
	
	public int getUsertoken(String token){
		return usersdaoimpl.getUsertoken(token);
	}

	public void bindIphone(Users user,String iphoneid, String iphonetoken) {
		usersdaoimpl.bindIphone(user, iphoneid, iphonetoken);
	}

	public Users getInfoByid(int uid) {
		return usersdaoimpl.getInfoByid(uid);
	}

}
