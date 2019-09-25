package com.exam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.exam.model.User;
import com.exam.model.UserExample;

public interface IUserDao {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int updatePasswordById(@Param("id") Integer id, @Param("password") String password);
    
    User selectByEamailAndNameAndIdentity(@Param("email") String email, @Param("name") String name, @Param("identity") Integer identity);
}