package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Favorite;
import entity.Topic;

import util.DbTool;

import biz.FavoriteBiz;
import biz.TopicBiz;
import biz.impl.FavoriteBizImpl;
import biz.impl.TopicBizImpl;

public class TopicServlet extends HttpServlet {

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
		TopicBiz topicbiz=new TopicBizImpl();
		FavoriteBiz favoritebiz=new FavoriteBizImpl();
		if("getInfoByname".equals(m)){
			String tname=request.getParameter("tname");
			List list=topicbiz.getInfoByname(tname);
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				out.write("no data!");
			}
		}else if("savetopic".equals(m)){
			List list=topicbiz.getSaveTopic(Integer.parseInt(request.getParameter("uid")));
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}else{
				out.write("no data!");
			}
		}else if("hottopic".equals(m)){
			List list=topicbiz.getHotTopic();
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				out.write("no data!");
			}
		}else if("changetopic".equals(m)){
			List list=topicbiz.getChangeTopic();
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				out.write("no data!");
			}
		}else if("weekhottopic".equals(m)){
			List list=topicbiz.getWeekhotTopic();
			if(list.size()>0){
				try {
					out.write(DbTool.getJsonForVoList(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				out.write("no data!");
			}
		}else if("addTopic".equals(m)){
			Topic topic=new Topic();
			int uid=Integer.parseInt(request.getParameter("uid"));
			String tname=request.getParameter("tname");
			topic.setUid(uid);
			topic.setTname(tname);
			int num=topicbiz.topicLogin(request.getParameter("tname"));
			if(num>0){
				out.write("Let CiTie already exists!");
			}else {
				int nn=topicbiz.addInfo(topic);
				if(nn>0){
					try {
						out.write(DbTool.getJsonForVo(topicbiz.getTopicbyname(tname)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					out.write("400");
				}
			}
		}else if("updatesave".equals(m)){
			int uid=Integer.parseInt(request.getParameter("uid"));
			int tid=Integer.parseInt(request.getParameter("tid"));
			int nn=favoritebiz.topicLogin(uid, tid);
			if(nn>0){
				out.write("Already added!");
			}else{
				Favorite favorite=new Favorite();
				favorite.setUid(uid);
				favorite.setTid(tid);
				int num=favoritebiz.addFaTopic(favorite);
				if(num>0){
					out.write("success!");
				}else{
					out.write("failed!");
				}
			}
			
		}
	}

}
