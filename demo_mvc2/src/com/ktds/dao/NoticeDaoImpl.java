package com.ktds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.mapper.NoticeMapper;
import com.ktds.vo.NoticeVo;

@Repository
public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    NoticeMapper mapper;

    @Override
    public List<NoticeVo> getNotices() {
        // TODO Auto-generated method stub
        return mapper.getNotices();
    }

    @Override
    public NoticeVo getNotice(Long no) {
        // TODO Auto-generated method stub
        return mapper.getNotice(no);
    }

    @Override
    public int addUser(NoticeVo notice) {
        // TODO Auto-generated method stub
        return mapper.addNotice(notice);
    }

    @Override
    public int deleteNotice(Long no) {
        // TODO Auto-generated method stub
        return mapper.deleteNotice(no);
    }

    @Override
    public int updateNotice(NoticeVo notice) {
        // TODO Auto-generated method stub
        return mapper.updateNotice(notice);
    }

    @Override
    public int updateCnt(Long no) {
        // TODO Auto-generated method stub
        return mapper.updateCnt(no);
    }


}
