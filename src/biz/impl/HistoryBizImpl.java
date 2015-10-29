package biz.impl;

import java.util.List;

import biz.HistoryBiz;
import dao.impl.HistoryDaoImpl;
import entity.History;

public class HistoryBizImpl implements HistoryBiz{
	private HistoryDaoImpl historydaoimpl=new HistoryDaoImpl();

	public int addHistory(History history) {
		return historydaoimpl.addHistory(history);
	}

	public List getHistory(int uid) {
		return historydaoimpl.getHistory(uid);
	}
	
}
