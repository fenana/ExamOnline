package com.exam.service;

import java.util.List;

import com.exam.model.Question;

public interface IQuestionService {
	public int saveQuestionInList(List<Question> list);

	public List<Question> getQuestionByClassId(Integer classId);

	public Question selectQuestionById(Integer questionId);
}
