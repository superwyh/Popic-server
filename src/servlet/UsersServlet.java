package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DbTool;

import entity.Users;

import biz.UsersBiz;
import biz.impl.UsersBizImpl;

public class UsersServlet extends HttpServlet {

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String m=request.getParameter("op");
		UsersBiz usersbiz=new UsersBizImpl();
		/* ע���û� */
		if("addInfo".equals(m)){
			Users user=new Users();
			String name=request.getParameter("name");
			String pass=request.getParameter("pass");
			String email=request.getParameter("email");
			user.setUname(name);
			user.setUpass(pass);
			user.setEmail(email);
			int num=usersbiz.getLoginByUsers(name, pass);
			if(num>0){
				out.write("Login failed!");
				System.out.println("Login failed!");
			}else{
				int num1=usersbiz.addInfo(user);
				if(num1>0){
					out.write("Add success!");
				}else{
					out.write("Add fail!");
				}
			}	
		}else if("Login".equals(m)){
			/**
			 * �豸����
			 */
			String iphoneid = request.getParameter("iphoneid");
			String iphonetoken = request.getParameter("iphonetoken");
			/**
			 * ��������:
			 * 	logintype:[facebook|local]
			 * 	id:xxx
			 * 	name:xxx
			 */
			String logintype = request.getParameter("logintype");
			System.out.println("logintype:"+logintype);
			String id = request.getParameter("id");
			System.out.println("id:"+id);
			System.out.println("name:"+request.getParameter("name"));
			
			// ���ʹ��facebook�˺ŵ�¼
			if("facebook".equals(logintype)){
				if(id!=null){
					if(id.length()>0){
						// ��� token�����ݿ���û��
						int tempNum = usersbiz.getUsertoken(id);
						System.out.println("��ľ�а�:"+tempNum);
						if(tempNum==0){
							// ע�����˺�
							Users facebookuser=new Users();
							String name=request.getParameter("name");facebookuser.setUname(name);
							String pass=request.getParameter("id");facebookuser.setUpass(pass);
							facebookuser.setUtoken(id);
							usersbiz.getLoginByUsers(name, pass);
							// ��¼
							int num=usersbiz.addInfo(facebookuser);
							if(num>0){
								Users users=usersbiz.getUsersInfo(name, pass);
								try {
									out.write(DbTool.getJsonForVo(users));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}else{
								out.write("400");
								System.out.println("400");
							}
						}else{
							// ��� token�����ݿ�����
							System.out.println("���ݿ���ѽ");
							// ��¼
							String name=request.getParameter("name");
							String pass=request.getParameter("id");
							int num=usersbiz.getLoginByUsers(name, pass);
							if(num>0){
								Users users=usersbiz.getUsersInfo(name, pass);
								try {
									// ��½�ɹ� ���û����豸id
									usersbiz.bindIphone(users, iphoneid, iphonetoken);
									out.write(DbTool.getJsonForVo(users));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}else{
								out.write("400");
								System.out.println("400");
							}
						}
					}
				}// ʹ�ñ����˺ŵ�½
			}else{
				String name=request.getParameter("name");
				String pass=request.getParameter("pass");
				int num=usersbiz.getLoginByUsers(name, pass);
				if(num>0){
					Users users=usersbiz.getUsersInfo(name, pass);
					try {
						// ��½�ɹ� ���û����豸id
						usersbiz.bindIphone(users, iphoneid, iphonetoken);
						out.write(DbTool.getJsonForVo(users));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					out.write("400");
					System.out.println("400");
				}
			}
		}else if("updatepass".equals(m)){
			String name=request.getParameter("name");
			String pass=request.getParameter("pass");
			String npass=request.getParameter("npass");
			int num=usersbiz.getLoginByUsers(name, pass);
			if(num>0){
				int num1=usersbiz.updatePass(name, npass);
				if(num1>0){
					out.write("Modify success!");
				}else {
					out.write("Modify fail!");
				}
			}else {
				out.write("Account information does not exist!");
			}
			
		}
	}

}
