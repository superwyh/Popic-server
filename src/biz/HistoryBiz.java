package biz;

import java.util.List;

import entity.History;

public interface HistoryBiz {
	
	public int addHistory(History history);       //������ʷ��¼
	public List getHistory(int uid);                //��ѯ��ʷ��¼
}
