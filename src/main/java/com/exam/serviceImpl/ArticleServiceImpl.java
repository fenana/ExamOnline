package com.exam.serviceImpl;

import com.exam.dao.IArticlesDao;
import com.exam.model.Articles;
import com.exam.service.IArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticlesService {

    @Autowired
    IArticlesDao dao;

    @Override
    public List<Articles> findAll() {
        //调用dao层，完成数据读取
        return dao.findAll();
    }

    @Override
    public List<Articles> findAll(int pageSize, int pageIndex) {
        //total 7
        //pageSize = 3
        //pageIndex = 1
        int sPos = (pageIndex - 1) * 3;
        int ePos = pageSize;
        return dao.findAllPaging(sPos, ePos);
    }

    @Override
    public Integer add(Articles model) {
        //调用dao
        int result = dao.add(model);
        return result;
    }

    @Override
    public Integer remove(Integer id){
        //call dao
        int result  = dao.delete(id);
        return result;
    }

    @Override
    public Articles findById(Integer id){
        Articles entity = dao.findById(id);
        return entity;
    }

    @Override
    public Integer modify(Articles model){
        return dao.update(model);
    }
}
