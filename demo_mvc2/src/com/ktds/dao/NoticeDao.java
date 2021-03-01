package com.ktds.dao;

import java.util.List;

import com.ktds.vo.NoticeVo;

public interface NoticeDao {
	public List<NoticeVo> getNotices();

	public NoticeVo getNotice(Long no);

	public int addUser(NoticeVo notice);

	public int deleteNotice(Long no);

	public int updateNotice(NoticeVo notice);
	
	public int updateCnt(Long no);
}
