package biz.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;
import javapns.exceptions.DuplicateDeviceException;
import javapns.exceptions.NullDeviceTokenException;
import javapns.exceptions.NullIdException;
import javapns.exceptions.UnknownDeviceException;

import org.json.JSONException;

import dao.impl.RepostDaoImpl;
import entity.Repost;
import entity.Users;

import biz.RepostBiz;

public class RepostBizImpl implements RepostBiz{
private RepostDaoImpl repostdaoimpl=new RepostDaoImpl();

public List getInfoByPid(int pid) {
	return repostdaoimpl.getInfoByPid(pid);
}

public int addRepost(Repost repost) {
	return repostdaoimpl.addRepost(repost);
}
public void pushMessage(Users user, String content,String regAddress) {
	if(user!=null){
		if(user.getUiphonetoken()!=null){
			if(!user.getUiphonetoken().equals("")){
				// json 通知
				PayLoad simplePayLoad = new PayLoad();
				PushNotificationManager manager = null;
				try {
					// 通知 内容
					simplePayLoad.addAlert(content);
					// 通知 提示音
					simplePayLoad.addSound("default");
					// 推送通知管理
					manager = PushNotificationManager.getInstance();
					// 连接苹果 苹果官方、苹果接口，证书文件，证书密码，证书加密类型
					manager.initializeConnection("gateway.push.apple.com", 2195, regAddress, "12030430", SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
					// 发送给哪个设备 id // 获得未知，则添加，已经有了就删除。最后添加
					Device client = manager.getDevice(user.getUiphoneid());
					if(client==null){
						// 添加设备
						manager.addDevice(user.getUiphoneid(), user.getUiphonetoken());
					}
					// 给某个设备发送通知
					manager.sendNotification(client, simplePayLoad);
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (UnknownDeviceException e) {
					// 添加
					try {
						System.out.println("添加");
						manager.addDevice(user.getUiphoneid(), user.getUiphonetoken());
					} catch (DuplicateDeviceException e1) {
						e1.printStackTrace();
					} catch (NullIdException e1) {
						e1.printStackTrace();
					} catch (NullDeviceTokenException e1) {
						e1.printStackTrace();
					}
				} catch (NullIdException e) {
					e.printStackTrace();
				} catch (UnrecoverableKeyException e) {
					e.printStackTrace();
				} catch (KeyManagementException e) {
					e.printStackTrace();
				} catch (KeyStoreException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (CertificateException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (NoSuchProviderException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

public String getPushMessage(String title){
	if(title.length()>50){
		title = title.substring(0, 50);
		title += "...";
	}
	return title;
}
}
