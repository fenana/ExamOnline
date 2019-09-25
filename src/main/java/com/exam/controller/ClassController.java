package com.exam.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exam.model.ExamClass;
import com.exam.service.IExamClassService;

@Controller
@RequestMapping(value="/class")
public class ClassController extends BaseController{
	
	@Autowired
	IExamClassService examClassService;
	
	/**
	 * 返回课程管理界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/")
	public ModelAndView getClassView(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView("root/class");
		List<ExamClass> classList = examClassService.getExamClassList();
		view.addObject("classList", classList);
		return view;
	}
	
	@RequestMapping(value="/addClass")
	@ResponseBody
	public Object addClass(HttpServletRequest request,HttpServletResponse response) {
		String className = request.getParameter("className");
		if(className!=null && className!="") {
			ExamClass examClass = new ExamClass();
			examClass.setClassname(className);
			examClass.setCreateTime(new Date());
			examClassService.insertClass(examClass);
		}
		
		return updateClassList(request);
	}
	
	@RequestMapping(value="/deleteClass")
	@ResponseBody
	public Object deleteClass(HttpServletRequest request,HttpServletResponse response) {
		
		String classId = request.getParameter("classId");
		if(classId!=null) {
			int id = Integer.parseInt(classId);
			examClassService.deleteClass(id);
		}
		
		return updateClassList(request);
	}
	
	@RequestMapping(value="/editClass")
	@ResponseBody
	public Object editClass(HttpServletRequest request,HttpServletResponse response) {
		
		String classId = request.getParameter("classId");
		String className = request.getParameter("className");
		if(className!=null && className!="" && classId!=null) {
			int id = Integer.parseInt(classId);
			examClassService.updateClass(id,className);
		}
		
		return updateClassList(request);
	} 
	
	/**
	 * 更新session中的classList
	 * 返回包含了classList的map
	 * @param request
	 * @return
	 */
	private Map updateClassList(HttpServletRequest request) {
		List<ExamClass> classList = examClassService.getExamClassList();
		
		HttpSession session = request.getSession();
		session.setAttribute("classList", classList);
		
	    HashMap map = new HashMap();
	    map.put("classList", classList);
		return map;
	}

}
