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
			// �����ļ���
			String fileName = getFileName();
			// UUID
			/* ʵ����룺Ѱ�һ���ύ�ֶ� */
			Files fs = su.getFiles();
			
			System.out.println("count"+fs.getCount());
			for(int i=0;i<fs.getCount();i++){
				System.out.println("���������ļ��ֶ����ֽ�:");
				String fieldName = fs.getFile(i).getFieldName();
				if("pimage".equals(fieldName)){
					String savefile = getAbsFilePath("/images")+fileName+".png";
					System.out.println(savefile);
					String needCreateDir = getFilePath("/images");
					// ����Ŀ¼
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
					// ����Ŀ¼
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
				out.write("add success��");
				System.out.println("add success��");
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
//							// ����Ŀ¼
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
//					out.write("add success��");
//					System.out.println("add success��");
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
	 * ���ݴ�����ļ������� �����ʵ��·��
	 * @param witch �ļ�����
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
	 * ���ݴ�����ļ������� �����Ե�·��
	 * @param witch �ļ�����
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
