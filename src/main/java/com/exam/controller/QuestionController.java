package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController{
	
	/**
	 * 查看试题库
	 * 返回应该是试题库的列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/query")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("question/list");
		
		return view;
	}
	
	
	/**
	 * 添加试题
	 * 注意：此处应该是Excel文件上传，然后解析、验证、存储
	 * 返回的可能是试题库的所有题目
	 * @return
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("question/add");
		
		return view;
	}

}
