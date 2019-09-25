package com.exam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.exam.model.THobbyModel;
import com.exam.model.User;
import com.exam.model.UserExample;

public interface IHobbyDao {
    
    int add(THobbyModel record);

}