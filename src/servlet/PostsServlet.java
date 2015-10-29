package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Favorite;
import entity.History;
import entity.Posts;

import util.DbTool;

import biz.FavoriteBiz;
import biz.HistoryBiz;
import biz.PostsBiz;
import biz.impl.FavoriteBizImpl;
import biz.impl.HistoryBizImpl;
import biz.impl.PostsBizImpl;

public class PostsServlet extends HttpServlet {

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
		PostsBiz postbiz=new PostsBizImpl();
		HistoryBiz historybiz=new HistoryBizImpl();
		FavoriteBiz favoritebiz=new FavoriteBizImpl();
		if("getinfobyid".equals(m)){          
			History history=new History();
			int uid=Integer.parseInt(request.getParameter("uid"));
			int pid=Integer.parseInt(request.getParameter("pid"));
			history.setUid(uid);
			history.setPid(pid);
			int num=historybiz.addHistory(history);
			if(num>0){
				System.out.print("add success!"); 
			}else{
				System.out.print("add faile!"); 
			}
			Posts posts=postbiz.getInfo(Integer.parseInt(request.getParameter("pid")));
			if(posts!=null){
				try {
					out.write(DbTool.getJsonForVo(posts));
					System.out.println(DbTool.getJsonForVo(posts));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				out.write("No data!");
			}
		}else if("getSavePost".equals(m)){
			List list=postbiz.getSavePost(Integer.parseInt(request.getParameter("uid")));
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
					System.out.println(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				out.write("No data!");
			}
		}else if("getPostById".equals(m)){
			List list=postbiz.getInfoByTid(Integer.parseInt(request.getParameter("tid")));
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
					System.out.println(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				out.write("No data!");
			}
		}else if("getPostByName".equals(m)){
			String pname=request.getParameter("pname");
			List list=postbiz.getInfoByName(pname);
			if(list.size()>0){
				try {
					out.print(DbTool.getJsonForVoList(list));
					System.out.println(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				out.write("No data!");
			}
		}else if("hotpost".equals(m)){
			List list=postbiz.getHotPost();
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
					System.out.println(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				out.write("No data!");
			}
		}else if("changepost".equals(m)){
			List list=postbiz.getChangePost();
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				out.write("No data!");
			}
		}else if("weekhotpost".equals(m)){
			List list=postbiz.getWeekhotPost();
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				out.write("No data!");
			}
		}else if("updatesave".equals(m)){
			int pid=Integer.parseInt(request.getParameter("pid"));
			int uid=Integer.parseInt(request.getParameter("uid"));
			int nn=favoritebiz.postsLogin(uid, pid);
			if(nn>0){
				out.write("Already added!");
			}else{
				Favorite favorite=new Favorite();
				favorite.setPid(pid);
				favorite.setUid(uid);
				int num=favoritebiz.addFaPost(favorite);
				if(num>0){
					out.write("Modify success!");
				}else{
					out.write("Modify fail!");
				}
			}
			
		}else if("history".equals(m)){
			List list=historybiz.getHistory(Integer.parseInt(request.getParameter("uid")));
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				out.write("No data!");
			}
		}
	}

}
