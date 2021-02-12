package com.ktds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ktds.dao.UserDao;
import com.ktds.vo.UserVo;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;
	@Override
	public List<UserVo> getUsers() {
		// TODO Auto-generated method stub
		return dao.getUsers();
	}
	@Override
	public UserVo getUser(String id) {
		// TODO Auto-generated method stub
		return dao.getUser(id);
	}
	@Override
	public int addUser(UserVo user) {
		// TODO Auto-generated method stub
		return dao.addUser(user);
	}
	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		return dao.deleteUser(id);
	}
	@Override
	public int updateUser(UserVo user) {
		// TODO Auto-generated method stub
		return dao.updateUser(user);
	}

}
