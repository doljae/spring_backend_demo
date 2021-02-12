package com.ktds.dao;

import java.util.List;

import com.ktds.vo.UserVo;

public interface UserDao {
	public List<UserVo> getUsers();

	public UserVo getUser(String id);

	public int addUser(UserVo user);

	public int deleteUser(int id);

	public int updateUser(UserVo user);
}
