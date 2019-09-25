package com.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.IExamDao;
import com.exam.model.Exam;
import com.exam.model.ExamExample;
import com.exam.service.IExamService;

@Service
public class ExamServiceImpl implements IExamService{
	
	@Autowired
	IExamDao examDao;

	@Override
	public int addExam(Exam exam) {
		// TODO Auto-generated method stub
		return examDao.insert(exam);
	}
	
	@Override
	public Exam getExamById(int id) {
		// TODO Auto-generated method stub
		return examDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Exam> getExamList() {
		// TODO Auto-generated method stub
		ExamExample example = new ExamExample();
		//ExamExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("create_time DESC");//排序方式
		return examDao.selectByExample(example);
	}

	@Override
	public Exam getMatchExam(Exam exam) {
		// TODO Auto-generated method stub
		ExamExample example = new ExamExample();
		ExamExample.Criteria criteria = example.createCriteria();
		if(exam.getClassId()!=null) {
			criteria.andClassIdEqualTo(exam.getClassId());
		}
//		if(exam.getCreateTime()!=null) {
//			criteria.andCreateTimeEqualTo(exam.getCreateTime());
//		}
//		if(exam.getEndTime()!=null) {
//			criteria.andEndTimeEqualTo(exam.getEndTime());
//		}
		if(exam.getLength()!=null) {
			criteria.andLengthEqualTo(exam.getLength());
		}
		if(exam.getName()!=null) {
			criteria.andNameEqualTo(exam.getName());
		}
		if(exam.getNumber()!=null) {			
			criteria.andNumberEqualTo(exam.getNumber());
		}		
		if(exam.getPassword()!=null) {
			criteria.andPasswordEqualTo(exam.getPassword());
		}
		if(exam.getStartTime()!=null) {
			criteria.andStartTimeEqualTo(exam.getStartTime());
		}
		List<Exam> examList = examDao.selectByExample(example);
		if(examList.size()>=1) {
			return examList.get(0);
		}else {
			return null;
		}
	}

	

}
