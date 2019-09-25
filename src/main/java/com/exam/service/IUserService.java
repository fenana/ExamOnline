package com.exam.service;

import org.apache.ibatis.annotations.Param;

import com.exam.model.User;

/**
 * service
 * @author zfh14
 *
 */
public interface IUserService {
	public User getUserById(int userId);
	
	public User getUserByParam(User user);
	
	public boolean insertUser(User user);//注册
	
	public int updateUserById(User user);
	
	User selectByEamailAndNameAndIdentity(String email, String name, Integer identity);
	
	int updatePasswordById(Integer id, String password);
}
