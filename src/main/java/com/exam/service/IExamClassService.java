package com.exam.service;

import java.util.List;

import com.exam.model.ExamClass;

public interface IExamClassService {
	public List<ExamClass> getExamClassList();
	public int insertClass(ExamClass eClass);
	public int deleteClass(int id);
	public int updateClass(int id, String className);

}