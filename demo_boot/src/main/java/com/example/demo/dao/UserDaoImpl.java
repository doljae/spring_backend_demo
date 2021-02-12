package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.UserVo;
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	UserMapper mapper;
	
	@Override
	public List<UserVo> getUsers() {
		// TODO Auto-generated method stub
		return mapper.getUsers();
	}

	@Override
	public UserVo getUser(String id) {
		// TODO Auto-generated method stub
		return mapper.getUser(id);
	}

	@Override
	public int addUser(UserVo user) {
		// TODO Auto-generated method stub
		return mapper.addUser(user);
	}

	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		return mapper.deleteUser(id);
	}

	@Override
	public int updateUser(UserVo user) {
		// TODO Auto-generated method stub
		return mapper.updateUser(user);
	}

}
