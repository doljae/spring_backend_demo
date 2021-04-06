package com.ktds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.dao.NoticeDao;
import com.ktds.vo.NoticeVo;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao dao;

    @Override
    public List<NoticeVo> getNotices() {
        return dao.getNotices();
    }

    @Override
    public NoticeVo getNotice(Long no) {
        // TODO Auto-generated method stub
        return dao.getNotice(no);
    }

    @Override
    public int addNotice(NoticeVo notice) {
        // TODO Auto-generated method stub
        return dao.addUser(notice);
    }

    @Override
    public int deleteNotice(Long no) {
        // TODO Auto-generated method stub
        return dao.deleteNotice(no);
    }

    @Override
    public int updateNotice(NoticeVo notice) {
        // TODO Auto-generated method stub
        return dao.updateNotice(notice);
    }

    @Override
    public int updateCnt(Long no) {
        // TODO Auto-generated method stub
        return dao.updateCnt(no);
    }


}
