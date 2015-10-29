package util;

import java.io.File;
import java.text.*;
import java.util.*;


//�ַ���������
public class StringTool {
	/**
	 * <font color="red"> ��־��¼����������־���</font>
	 */
	
	/**
	 * <font color="red"> ���ڸ�ʽ�����ڵ���</font>
	 */
	static SimpleDateFormat ss=new SimpleDateFormat();
	
	/**
	 * <font color="red"> ���ڸ�ʽ�����ֵ���</font>
	 */
	static DecimalFormat dd=new DecimalFormat();
	
	/**
	 * <font color="red"> ��Դ����</font>
	 */
	public static ResourceBundle tt=ResourceBundle.getBundle("config");
	
	/**
	 * ��ȡ�����ļ��е���Ϣ
	 * @param key
	 * @return
	 */
	public static String getConfig(String key){
		String value=null;
		       return value=tt.getString(key);
	}
	
	/**
	 * ��UUID�ķ�ʽ�����ļ���
	 * @return
	 */
	public static String getFileName(){
		UUID id=UUID.randomUUID();
		return id.toString().toString();
	}
	
	/**
	 * �Ը����ĸ�ʽ���ص�ǰ��ʱ���ַ�
	 * @param pattern
	 * @return
	 */
	public static String getDate(String pattern){
		ss.applyLocalizedPattern(pattern);
		return ss.format(new Date());	
	}
	
	public static String getUpDir(String appPath){
		 String result="userFiles/"+getDate("yyyy/MMdd/");
		File afile =new File(appPath+"/"+result);
		if(!afile.exists()){
			afile.mkdirs();
		}
		return result;
	}
	
	/**
	 * ��ʽ������
	 * @param pattern
	 * @param number
	 * @return
	 */
	public static String getNumber(String pattern,double number){
		dd.applyPattern(pattern);
		return dd.format(number);
	}
	
	public static void main(String[] args) {	
		String user = StringTool.getFileName();
		
		System.out.println((int)(Math.random()*100));
	}
}
