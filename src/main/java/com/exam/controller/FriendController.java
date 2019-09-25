package com.exam.controller;

import com.exam.model.Friend;
import com.exam.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    IFriendService friendService;

//显示页面，get请求
    @RequestMapping(value = "add", method= RequestMethod.GET)
    public String add(){
        return "friend/add";
    }
//    执行业务，添加数据。
    @RequestMapping(value = "add", method= RequestMethod.POST)
    public String add(Friend friend, Model model){
        //调用service
        int result = friendService.add(friend);
        if(result > 0){
            return "redirect:index";
        }else{
            //错误提示
            model.addAttribute("info", "添加失败，请检查数据");
            return "friend/add";
        }
    }

    //分页
    @RequestMapping("index")
    public ModelAndView list(ModelAndView mav, Integer pageIndex){

        int pageSize = 5;
        if(pageIndex ==null){
            pageIndex = 1;
        }

        //此页需要，需要调用Service层
        List<Friend> items = friendService.findAll(pageSize, pageIndex);

        mav.addObject("items", items);
        mav.setViewName("friend/list");

        ///WEB-INF/jsp/ articles/list .jsp
        return mav;
    }
//删除数据
    @RequestMapping("remove")
    public String remove(Integer id, Model model){
        //调用Service完成删除
        Integer result = friendService.remove(id);
        if(result>0){
            return "redirect:index";
        }else{
            model.addAttribute("msg", "删除失败，请联系...");
            model.addAttribute("url", "index");
            return "message";
        }

    }
}
