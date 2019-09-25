package com.exam.service;

import com.exam.model.Articles;

import java.util.List;

public interface IArticlesService {

    /**
     *  资讯列表页
     *  @author: Gary SU
     *  @Date: 2019/9/23 11:13
     *  @Description:
     */
    List<Articles> findAll();
    List<Articles> findAll(int pageSize, int pageIndex);
    Integer add(Articles model);
    Integer remove(Integer id);
    Articles findById(Integer id);
    Integer modify(Articles model);
}
