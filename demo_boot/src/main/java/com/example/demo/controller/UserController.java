package com.example.demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.UserService;
import com.example.demo.vo.UserVo;

@Controller
public class UserController {
	@Autowired
	UserService service;

	@GetMapping("/index.do")
	public String getMain() {
		return "index";
	}

	@GetMapping("/userList.do")
	public ModelAndView getUserList() {
		List<UserVo> users = service.getUsers();
		return new ModelAndView("userList", "users", users);
	}

	@GetMapping("/userInsert.do")
	public ModelAndView getUsertInsertForm() {
		List<String> cityList = Arrays.asList("서울", "경기", "부산", "대구", "제주");
		return new ModelAndView("userInsert", "cities", cityList);
	}

	@GetMapping("/userDetail.do")
	public ModelAndView getUserDetail(@RequestParam String userid) {
		UserVo user = service.getUser(userid);
		return new ModelAndView("userDetail", "userOne", user);
	}

	// 포인트
	@PostMapping("/userInsert.do")
	public String postUser(@ModelAttribute UserVo user) {
		int cnt = service.addUser(user);
		if (cnt == 1)
			return "redirect:/userList.do";
		return "redirect:/";
	}

	@GetMapping("userDelete.do/{id}")
	public String deleteUser(@PathVariable int id) {
		int cnt = service.deleteUser(id);
		if (cnt == 1)
			return "redirect:/userList.do";
		return "redirect:/";
	}

	@GetMapping("userUpdate.do")
	public ModelAndView getUserUpdateForm(@RequestParam String userid) {
		UserVo user = service.getUser(userid);
		List<String> cityList = Arrays.asList("서울", "경기", "부산", "대구", "제주");
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("user", user);
		dataMap.put("cities", cityList);
		return new ModelAndView("userUpdate", "map", dataMap);
	}

	@PostMapping("userUpdate.do")
	public String updateUser(UserVo user) {
		int cnt = service.updateUser(user);
		if (cnt == 1)
			return "redirect:/userDetail.do?userid=" + user.getUserid();
		return "redirect:/";

	}
}
