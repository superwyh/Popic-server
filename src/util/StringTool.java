package util;

import java.io.File;
import java.text.*;
import java.util.*;


//字符串工具类
public class StringTool {
	/**
	 * <font color="red"> 日志记录器，用于日志输出</font>
	 */
	
	/**
	 * <font color="red"> 用于格式化日期的类</font>
	 */
	static SimpleDateFormat ss=new SimpleDateFormat();
	
	/**
	 * <font color="red"> 用于格式化数字的类</font>
	 */
	static DecimalFormat dd=new DecimalFormat();
	
	/**
	 * <font color="red"> 资源对象</font>
	 */
	public static ResourceBundle tt=ResourceBundle.getBundle("config");
	
	/**
	 * 获取配置文件中的信息
	 * @param key
	 * @return
	 */
	public static String getConfig(String key){
		String value=null;
		       return value=tt.getString(key);
	}
	
	/**
	 * 以UUID的方式产生文件名
	 * @return
	 */
	public static String getFileName(){
		UUID id=UUID.randomUUID();
		return id.toString().toString();
	}
	
	/**
	 * 以给定的格式返回当前的时间字符
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
	 * 格式化数字
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
