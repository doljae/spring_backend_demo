package com.ktds.service;

import java.util.List;

import com.ktds.vo.NoticeVo;

public interface NoticeService {
	public List<NoticeVo> getNotices();

	public NoticeVo getNotice(Long no);

	public int addNotice(NoticeVo notice);

	public int deleteNotice(Long no);

	public int updateNotice(NoticeVo notice);
	
	public int updateCnt(Long no);
}
