package com.exam.controller;

import com.exam.serviceImpl.ExamClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *  Web版本Spring容器查看清单
 *  @author: Gary SU
 *  @Date: 2019/9/22 7:24
 *  @Description: 基于SpringMVC的容器查看
 */ 
@Controller
@RequestMapping("")
public class SpringContainerController {

    @RequestMapping(value = "sc.do")
    public @ResponseBody
    String Index(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        String[] str = context.getBeanDefinitionNames();
        for (String string : str) {
            String info = "实例名：" + string;
            System.out.println(info);
            sb.append(info + "<br/>");
        }

        return sb.toString();
    }

}
