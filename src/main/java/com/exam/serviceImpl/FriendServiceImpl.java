package com.exam.serviceImpl;

import com.exam.dao.IFriendDao;
import com.exam.model.Friend;
import com.exam.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements IFriendService {

    @Autowired
    IFriendDao dao;

    @Override
    public List<Friend> findAll() {
//调用dao层，完成数据查询
        return  dao.findAll();
    }

    @Override
    public List<Friend> findAll(int pageSize, int pageIndex) {
        int sPos = (pageIndex - 1) * 3;
        int ePos = pageSize;
        return dao.findAllPaging(sPos, ePos);
    }

    @Override
    public Integer add(Friend friend) {
        int result = dao.add(friend);
        return result;
    }

    @Override
    public Integer remove(Integer id) {
        int result =  dao.delete(id);
        return result;
    }

    @Override
    public Friend findById(Integer id) {
        Friend entity =  dao.findById(id);
        return entity;
    }

    @Override
    public Integer modify(Friend friend) {
        return dao.update(friend);
    }
}
