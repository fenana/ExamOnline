package com.exam.dao;

import com.exam.model.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFriendDao {
    Integer add(@Param("friend") Friend friend);
    Integer delete(@Param("id")int id);
    Integer update(@Param("friend") Friend friend);
    List<Friend> findAll();
    List<Friend> findAllPaging(@Param("sPos")Integer sPos, @Param("ePos")Integer ePos);
    Friend findById(int id);
}
