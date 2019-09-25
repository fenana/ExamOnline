package com.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.IExamStudentDao;
import com.exam.model.ExamStudent;
import com.exam.model.ExamStudentExample;
import com.exam.service.IExamStudentService;

@Service
public class ExamStudentServiceImpl implements IExamStudentService{

	@Autowired
	IExamStudentDao examStudentDao;
	
	@Override
	public int saveStudentInList(List<ExamStudent> list) {
		// TODO Auto-generated method stub
		for(int i=0;i<list.size();i++) {
			examStudentDao.insert(list.get(i));
		}
		return 1;
	}

	@Override
	public ExamStudent getExamStudentByPara(int examId, String email) {
		// TODO Auto-generated method stub
		ExamStudentExample example = new ExamStudentExample();
		ExamStudentExample.Criteria criteria = example.createCriteria();
		if(examId>0) {
			criteria.andExamIdEqualTo(examId);
		}
		if(email!=null && email!="") {
			criteria.andStudentEmailEqualTo(email);
		}
		
		List<ExamStudent> list = examStudentDao.selectByExample(example);
		
		if(list.size()>=1) {
			return list.get(0);
		}
		return null;
	}

}
