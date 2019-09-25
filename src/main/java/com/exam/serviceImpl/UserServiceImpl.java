package com.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.IUserDao;
import com.exam.model.User;
import com.exam.model.UserExample;
import com.exam.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {  
    @Autowired
    IUserDao userDao; 
    
    @Override  
    public User getUserById(int userId) {  
        // TODO Auto-generated method stub  
        return userDao.selectByPrimaryKey(userId);  
    }

    /**
     * 获得与输入User匹配的User
     * 注意：输入的user仅包含了不全的参数
     */
	@Override
	public User getUserByParam(User user) {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		if(user.getId()!=null) {
			criteria.andIdEqualTo(user.getId());
		}
		if(user.getName()!=null && user.getName()!="") {
			criteria.andNameEqualTo(user.getName());
		}
		if(user.getEmail()!=null && user.getEmail()!="") {
			criteria.andEmailEqualTo(user.getEmail());
		}
		if(user.getPassword()!=null && user.getPassword()!="") {
			criteria.andPasswordEqualTo(user.getPassword());
		}
		if(user.getIdentity()!=null) {
			criteria.andIdentityEqualTo(user.getIdentity());
		}
		if(user.getCreateTime()!=null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(user.getCreateTime());
		}
		
		List<User> userList  = userDao.selectByExample(example);
		if(userList.size()>=1) {
			return userList.get(0);
		}
		return null;
	}

	
	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		boolean res=true;
		User iUser = new User();
		iUser.setEmail(user.getEmail());
		User daUser = getUserByParam(iUser);
		if(daUser!=null) {
			res=false;
		}else {
			userDao.insert(user);
		}
		return res;
	}

	@Override
	public int updateUserById(User user) {
		// TODO Auto-generated method stub
		return userDao.updateByPrimaryKey(user);
	}

	@Override
	public User selectByEamailAndNameAndIdentity(String email, String name, Integer identity) {
		return userDao.selectByEamailAndNameAndIdentity(email, name, identity);
	}

	@Override
	public int updatePasswordById(Integer id, String password) {
		return userDao.updatePasswordById(id, password);
	}

  
}  
