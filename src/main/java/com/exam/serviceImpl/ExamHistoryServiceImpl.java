package com.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.IExamHistoryDao;
import com.exam.model.ExamHistory;
import com.exam.model.ExamHistoryExample;
import com.exam.service.IExamHistoryService;

@Service
public class ExamHistoryServiceImpl implements IExamHistoryService{

	@Autowired
	IExamHistoryDao examHistoryDao;
	
	@Override
	public void saveExamHistoryList(List<ExamHistory> examHistoryList) {
		// TODO Auto-generated method stub
		for(int i=0;i<examHistoryList.size();i++) {
			examHistoryDao.insert(examHistoryList.get(i));
		}
	}

	@Override
	public List<ExamHistory> getExamHistoryByPara(int examId, String studentEmail) {
		// TODO Auto-generated method stub
		ExamHistoryExample example = new ExamHistoryExample();
		ExamHistoryExample.Criteria criteria = example.createCriteria();
		if(examId>0 && studentEmail!=null) {
			criteria.andExamIdEqualTo(examId);
			criteria.andStudentEmailEqualTo(studentEmail);
		}
		return examHistoryDao.selectByExample(example);
	}

	@Override
	public List<ExamHistory> getExamHistoryByExamId(int examId) {
		// TODO Auto-generated method stub
		ExamHistoryExample example = new ExamHistoryExample();
		ExamHistoryExample.Criteria criteria = example.createCriteria();
		if(examId>0) {
			criteria.andExamIdEqualTo(examId);
		}
		return examHistoryDao.selectByExample(example);
	}

}
