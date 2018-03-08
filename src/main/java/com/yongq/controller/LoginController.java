package com.yongq.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yongq.s_dao.SLoginDAO;
import com.yongq.s_dto.StudentVO;

@Controller
public class LoginController {
	
	@RequestMapping(value="/view")
	public ModelAndView View(HttpServletRequest request) throws IOException {
		ModelAndView mv = new ModelAndView("student/Student_Login");
		StudentVO svo = new StudentVO();
		mv.addObject("loginBean", svo);
		
		return mv;
	}
	
	@Autowired
	SLoginDAO dao;
	
	@RequestMapping(value="/login")
	public ModelAndView Login(HttpServletRequest request, @ModelAttribute("loginBean") StudentVO svo) {
		boolean result;
		ModelAndView modelAndView = null;
		String stu_id = svo.getStu_id();
		String stu_pw = svo.getStu_pw();
		
		if(svo != null && stu_id != null && stu_pw != null) {
			result = dao.isValidUser(stu_id, stu_pw);
			if(result == true) {
				modelAndView = new ModelAndView("student/Student_Main");
			} else {
				modelAndView = new ModelAndView("student/Student_Login");
				modelAndView.addObject("error", "잘못 입력했습니다.");
			}
		} else {
			modelAndView = new ModelAndView("student/Student_Login");
			modelAndView.addObject("error", "에러");
		}
		return modelAndView;
	}
}
