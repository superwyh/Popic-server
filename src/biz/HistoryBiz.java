package biz;

import java.util.List;

import entity.History;

public interface HistoryBiz {
	
	public int addHistory(History history);       //创建历史记录
	public List getHistory(int uid);                //查询历史记录
}
