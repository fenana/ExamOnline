package com.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.IQuestionDao;
import com.exam.model.Question;
import com.exam.model.QuestionExample;
import com.exam.service.IQuestionService;

@Service
public class IQuestionServiceImpl implements IQuestionService {

	@Autowired
	IQuestionDao questionDao;
	
	@Override
	public int saveQuestionInList(List<Question> list) {
		// TODO Auto-generated method stub
		for(int i=0;i<list.size();i++) {
			questionDao.insert(list.get(i));
		}
		return 1;
	}

	@Override
	public List<Question> getQuestionByClassId(Integer classId) {
		// TODO Auto-generated method stub
		QuestionExample example = new QuestionExample();
		QuestionExample.Criteria criteria = example.createCriteria();
		if(classId!=null) {
			criteria.andClassIdEqualTo(classId);
		}
		return questionDao.selectByExample(example);
	}

	@Override
	public Question selectQuestionById(Integer questionId) {
		// TODO Auto-generated method stub
		return questionDao.selectByPrimaryKey(questionId);
	}

}
