package com.exam.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.IExamClassDao;
import com.exam.model.ExamClass;
import com.exam.model.ExamClassExample;
import com.exam.service.IExamClassService;

@Service
public class ExamClassServiceImpl implements IExamClassService{

	@Autowired
	IExamClassDao examClassDao;
	
	@Override
	public List<ExamClass> getExamClassList() {
		// TODO Auto-generated method stub
		ExamClassExample example = new ExamClassExample();
		ExamClassExample.Criteria criteria = example.createCriteria();
		
		return examClassDao.selectByExample(example);
	}
	
	@Override
	public int insertClass(ExamClass eClass) {
		return examClassDao.insert(eClass);
	}

	@Override
	public int deleteClass(int id) {
		// TODO Auto-generated method stub
		return examClassDao.deleteByPrimaryKey(id);
	}

	@Override
	public int updateClass(int id, String className) {
		// TODO Auto-generated method stub
		ExamClass ec = new ExamClass();
		ec.setId(id);
		ec.setClassname(className);
		ec.setCreateTime(new Date());
		return examClassDao.updateByPrimaryKey(ec);
	}

}
