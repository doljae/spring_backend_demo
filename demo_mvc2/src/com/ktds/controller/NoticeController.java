package com.ktds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.service.NoticeService;
import com.ktds.vo.NoticeVo;

@Controller
public class NoticeController {
    @Autowired
    NoticeService service;

    @GetMapping("/")
    public String goList() {
        return "list";
    }

    @GetMapping("/list.do")
    public ModelAndView getNoticeList() {
        List<NoticeVo> notices = service.getNotices();
        return new ModelAndView("list", "notices", notices);
    }

    @GetMapping("/write.do")
    public String getWriteForm() {
        return "writeForm";
    }

    @PostMapping("/write.do")
    public String writeNotice(@ModelAttribute NoticeVo notice) {
        int cnt = service.addNotice(notice);
        if (cnt == 1)
            return "redirect:/list.do";
        return "redirect:/";
    }

    @GetMapping("/retrieve.do")
    public ModelAndView getNoticeDetail(@RequestParam Long no) {
        int cnt = service.updateCnt(no);
        NoticeVo notice = service.getNotice(no);
        System.out.println(notice);
        return new ModelAndView("retrieve", "notice", notice);
    }

    @PostMapping("/retrieve.do")
    public String updateNotice(@RequestParam Long no, @ModelAttribute NoticeVo notice) {
        System.out.println(no);
        notice.setNo(no);
        int cnt = service.updateNotice(notice);
        System.out.println(cnt);
        if (cnt == 1)
            return "redirect:/list.do";
        return "redirect:/";

    }

    @GetMapping("/noticeDelete.do")
    public String deleteNotice(@RequestParam Long no) {
        int cnt = service.deleteNotice(no);
        if (cnt == 1)
            return "redirect:/list.do";
        return "redirect:/";
    }
//	@GetMapping("/userList.do")
//	public ModelAndView getUserList() {
//		List<UserVo> users = service.getUsers();
//		return new ModelAndView("userList", "users", users);
//	}
//
//	@GetMapping("/userInsert.do")
//	public ModelAndView getUsertInsertForm() {
//		List<String> cityList = Arrays.asList("서울", "경기", "부산", "대구", "제주");
//		return new ModelAndView("userInsert", "cities", cityList);
//	}
//
//	@GetMapping("/userDetail.do")
//	public ModelAndView getUserDetail(@RequestParam String userid) {
//		UserVo user = service.getUser(userid);
//		return new ModelAndView("userDetail", "userOne", user);
//	}
//
//	// 포인트
//	@PostMapping("/userInsert.do")
//	public String postUser(@ModelAttribute UserVo user) {
//		int cnt = service.addUser(user);
//		if (cnt == 1)
//			return "redirect:/userList.do";
//		return "redirect:/";
//	}
//
//	@GetMapping("userDelete.do/{id}")
//	public String deleteUser(@PathVariable int id) {
//		int cnt = service.deleteUser(id);
//		if (cnt == 1)
//			return "redirect:/userList.do";
//		return "redirect:/";
//	}
//
//	@GetMapping("userUpdate.do")
//	public ModelAndView getUserUpdateForm(@RequestParam String userid) {
//		UserVo user = service.getUser(userid);
//		List<String> cityList = Arrays.asList("서울", "경기", "부산", "대구", "제주");
//		Map<String, Object> dataMap = new HashMap<>();
//		dataMap.put("user", user);
//		dataMap.put("cities", cityList);
//		return new ModelAndView("userUpdate", "map", dataMap);
//	}
//
//	@PostMapping("userUpdate.do")
//	public String updateUser(UserVo user) {
//		int cnt = service.updateUser(user);
//		if (cnt == 1)
//			return "redirect:/userDetail.do?userid=" + user.getUserid();
//		return "redirect:/";
//
//	}
}
