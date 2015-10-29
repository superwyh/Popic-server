package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.PostsBiz;
import biz.TopicBiz;
import biz.impl.PostsBizImpl;
import biz.impl.TopicBizImpl;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import entity.Posts;
import entity.Topic;

public class UploadServlet extends HttpServlet {
	HttpServletRequest request = null;
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public static final String SEPARATOR = System.getProperty("file.separator");
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		SmartUpload su=new SmartUpload();
		PostsBiz postsbiz=new PostsBizImpl();
		su.initialize(this.getServletConfig(), request, response);
		try {
			su.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		Request r= su.getRequest();
		String m=r.getParameter("op");
	
		if("addposts".equals(m)){
			Posts posts=new Posts();
			posts.setTid(Integer.parseInt(r.getParameter("tid")));
			posts.setUid(Integer.parseInt(r.getParameter("uid")));
			posts.setPname(r.getParameter("pname"));
			String pcontent1=r.getParameter("pcontent");
			pcontent1=pcontent1.replace("\r\n", "<br />");
			pcontent1=pcontent1.replace("\n", "<br />");
			String pcontent=pcontent1;
			posts.setPcontent(pcontent);
			// 保存文件名
			String fileName = getFileName();
			// UUID
			/* 实验代码：寻找活的提交字段 */
			Files fs = su.getFiles();
			
			System.out.println("count"+fs.getCount());
			for(int i=0;i<fs.getCount();i++){
				System.out.println("下面的这个文件字段名字叫:");
				String fieldName = fs.getFile(i).getFieldName();
				if("pimage".equals(fieldName)){
					String savefile = getAbsFilePath("/images")+fileName+".png";
					System.out.println(savefile);
					String needCreateDir = getFilePath("/images");
					// 创建目录
					new java.io.File(needCreateDir).mkdirs();
//					System.out.println(savefile);
					try {
						fs.getFile(i).saveAs(savefile);
					} catch (SmartUploadException e) {
						out.write("400");
					} catch (Exception e){
						out.write("400");
					}
					posts.setPimage(getFilePath("/images")+fileName+".png");
				}else if("pmusic".equals(fieldName)){
					String savefile = getAbsFilePath("/music")+fileName+".aif";
					String needCreateDir = getFilePath("/music");
					// 创建目录
					new java.io.File(needCreateDir).mkdirs();
//					System.out.println(savefile);
					try {
						fs.getFile(i).saveAs(savefile);
					} catch (SmartUploadException e) {
						out.write("400");
					} catch (Exception e){
						out.write("400");
					}
					posts.setPmusic(getFilePath("/music")+fileName+".aif");
				}
			}
			int aa=postsbiz.addInfo(posts);
			if(aa>0){
				out.write("add success！");
				System.out.println("add success！");
			}else{
				out.write("400");
				System.out.println("400");
			}
//			try {
//				Files files=su.getFiles();
//				for (int i = 0; i < files.getCount(); i++) {
//					File afile=files.getFile(i);
//					if(!afile.isMissing()){
//						if(afile.getFileExt()==".mp3"){
//							afile.saveAs("/music/"+getFilePath()+fileName+"."+afile.getFileExt());
//							posts.setPimage(getFilePath()+fileName+"."+afile.getFileExt());
//						}else{
//							String projectPath = request.getSession().getServletContext().getRealPath("/");
//							String savefile = projectPath+"images/"+getFilePath()+fileName+".png";
//							String needCreateDir = projectPath+"images/"+getFilePath();
//							// 创建目录
//							new java.io.File(needCreateDir).mkdirs();
////							System.out.println(savefile);
//							afile.saveAs(savefile);
//							posts.setPimage(getFilePath()+fileName+".png");
//						}	
//					}
//				}
//			} catch (SmartUploadException e) {
//				e.printStackTrace();
//			}finally{
//				int aa=postsbiz.addInfo(posts);
//				if(aa>0){
//					out.write("add success！");
//					System.out.println("add success！");
//				}else{
//					out.write("400");
//					System.out.println("400");
//				}
//			}
		}
	}
	
	public String getFileName(){
	    String name=this.getDate("yyyyMMddHHmmss");
	    return name+new Random().nextInt(99999);
	}
	/**
	 * 根据传入的文件夹名称 活得真实的路径
	 * @param witch 文件夹名
	 * @return
	 */
	public String getAbsFilePath(String witch){
		String appPath=this.request.getSession().getServletContext().getRealPath(witch);
		String dir=this.getDate("yyyy")+System.getProperty("file.separator")+this.getDate("MM");
		java.io.File afile=new java.io.File(appPath+System.getProperty("file.separator")+dir);
		if(!afile.exists()){
			afile.mkdirs();
		}
		return appPath+System.getProperty("file.separator")+dir+System.getProperty("file.separator");
	}
	/**
	 * 根据传入的文件夹名称 活的相对的路径
	 * @param witch 文件夹名
	 * @return
	 */
	public String getFilePath(String witch){
		String appPath=this.getServletContext().getRealPath(witch);
		String dir=this.getDate("yyyy")+System.getProperty("file.separator")+this.getDate("MM");
		java.io.File afile=new java.io.File(appPath+System.getProperty("file.separator")+dir);
		if(!afile.exists()){
			afile.mkdirs();
		}
		return dir+System.getProperty("file.separator");
	}
	private String getDate(String date){
		SimpleDateFormat sdf=new SimpleDateFormat(date);
		return sdf.format(new Date());
	}

}
