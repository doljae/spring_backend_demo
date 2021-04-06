package com.ktds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktds.vo.UserVo;

@Mapper
public interface UserMapper {
    public List<UserVo> getUsers();

    public UserVo getUser(String id);

    public int addUser(UserVo user);

    public int deleteUser(int id);

    public int updateUser(UserVo user);
}
