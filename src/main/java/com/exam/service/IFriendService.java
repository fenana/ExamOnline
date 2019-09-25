package com.exam.service;

import com.exam.model.Friend;

import java.util.List;

public interface IFriendService {

    List<Friend> findAll();
    List<Friend> findAll(int pageSize, int pageIndex);
    Integer add(Friend friend);
    Integer remove(Integer id);
    Friend findById(Integer id);
    Integer modify(Friend friend);
}
