package com.exam.controller;

import com.exam.model.Articles;
import com.exam.service.IArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArtitcleController {

    @Autowired
    IArticlesService articlesService;

    @RequestMapping("index")
    public ModelAndView list(ModelAndView mav, Integer pageIndex){

        int pageSize = 5;
        if(pageIndex ==null){
            pageIndex = 1;
        }

        //此页需要，需要调用Service层
        List<Articles> items = articlesService.findAll(pageSize, pageIndex);

        mav.addObject("items", items);
        mav.setViewName("articles/list");

        ///WEB-INF/jsp/ articles/list .jsp
        return mav;
    }

    //显示界面
    @RequestMapping(value = "add", method= RequestMethod.GET)
    public String add(){
        return "articles/add";
    }

    //采取数据
    @RequestMapping(value = "add", method= RequestMethod.POST)
    public String add(Articles article, Model model){

        //调用service
        int result = articlesService.add(article);
        if(result > 0){
            return "redirect:index";
        }else{
            //错误提示
            model.addAttribute("info", "添加失败，请检查数据");
            return "articles/add";
        }
    }


    @RequestMapping("remove")
    public String remove(Integer id, Model model){
        //调用Service完成删除
        Integer result = articlesService.remove(id);
        if(result>0){
            return "redirect:index";
        }else{
            model.addAttribute("msg", "删除失败，请联系...");
            model.addAttribute("url", "index");
            return "message";
        }

    }

    @RequestMapping("modify")
    public ModelAndView modify(Integer id, ModelAndView mav){
        //读取数据库，加载本条数据，to view完成显示

        Articles entity = articlesService.findById(id);

        mav.addObject("sss", entity);
        mav.setViewName("articles/modify");
        return mav;
    }

    @RequestMapping(value="modify", method = RequestMethod.POST)
    public String modify(Articles entity, Model model){

        //service 修改数据
        int result = articlesService.modify(entity);

        if(result>0){
            return "redirect:index";
        }else{
            model.addAttribute("msg", "修改失败，请联系...");
            model.addAttribute("url", "modify?id=" + entity.getArticleId());
            return "message";
        }
    }
}
