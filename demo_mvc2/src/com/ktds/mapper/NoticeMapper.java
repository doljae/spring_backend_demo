package com.ktds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktds.vo.NoticeVo;

@Mapper
public interface NoticeMapper {
    public List<NoticeVo> getNotices();

    public NoticeVo getNotice(Long no);

    public int addNotice(NoticeVo notice);

    public int deleteNotice(Long no);

    public int updateNotice(NoticeVo notice);

    public int updateCnt(Long no);
}
