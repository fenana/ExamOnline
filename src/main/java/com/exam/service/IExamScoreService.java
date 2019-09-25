package com.exam.service;

import com.exam.model.ExamScore;

import java.util.List;


public interface IExamScoreService {
	public int insertExamScore(ExamScore examScore);
	
	public ExamScore getExamScore(int examId, String studentEmail);
	
	public List<ExamScore> getExamScoreList(String studentEmail);

	public List<ExamScore> getExamScoreByExamId(int examId);

}
