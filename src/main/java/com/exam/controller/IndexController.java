package com.exam.controller;

import com.exam.service.IExamClassService;
import com.exam.service.IUserService;
import com.exam.serviceImpl.ExamClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping(value = "index.do")
    public @ResponseBody String Index(HttpServletRequest request){

        return "哈哈李厚霖df";
    }

    @RequestMapping(value = "index")
    public String Index(){
        //加载指定位置之外的视图
        return "index";
    }

}
