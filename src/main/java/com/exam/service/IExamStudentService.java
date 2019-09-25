package com.exam.service;

import java.util.List;

import com.exam.model.ExamStudent;

public interface IExamStudentService {
	public int saveStudentInList(List<ExamStudent> list);

	public ExamStudent getExamStudentByPara(int examId, String email);

}
