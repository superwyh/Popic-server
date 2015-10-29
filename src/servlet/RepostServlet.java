package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Repost;
import entity.Users;

import util.DbTool;

import biz.RepostBiz;
import biz.UsersBiz;
import biz.impl.RepostBizImpl;
import biz.impl.UsersBizImpl;

public class RepostServlet extends HttpServlet {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String m=request.getParameter("op");
		RepostBiz repostbiz=new RepostBizImpl();
		UsersBiz usersbiz=new UsersBizImpl();
		if("getinfobypid".equals(m)){
			int pid=Integer.parseInt(request.getParameter("pid"));
			List list=repostbiz.getInfoByPid(pid);
			if(list!=null){
				try {
					out.write(DbTool.getJsonForVoList(list));
					System.out.println(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				out.write("No data!");
			}
		}else if("addrepost".equals(m)){
			Repost repost=new Repost();
			int pid=Integer.parseInt(request.getParameter("pid"));
			int puid=Integer.parseInt(request.getParameter("puid"));
			String title = request.getParameter("pname");
			int uid=Integer.parseInt(request.getParameter("uid"));
			String recontent1=request.getParameter("recontent");
			recontent1=recontent1.replace("\r\n", "<br />");
			recontent1=recontent1.replace("\n", "<br />");
			String recontent=recontent1;
			repost.setPid(pid);
			repost.setUid(uid);
			repost.setRecontent(recontent);
			int num=repostbiz.addRepost(repost);
			if(num>0){
				// 回帖成功，则向楼主手机推送提示信息
				// 证书地址
				String regAddress = getAbsFilePath("/");
				Users user = new Users();
//				user.setUid(puid);
				user = usersbiz.getInfoByid(puid);
				System.out.print("");
				// 推送
				repostbiz.pushMessage(user, repostbiz.getPushMessage(title)+" have a new respost.", regAddress);
				out.write("Reply to success!");
			}else{
				out.write("Reply to fail!");
			}
		}
	}

	/**
	 * 根据传入的文件夹名称 活得真实的路径
	 * @param witch 文件夹名
	 * @return
	 */
	public String getAbsFilePath(String witch){
		String appPath=this.request.getSession().getServletContext().getRealPath(witch);
		return appPath+"push.p12";
	}
}
