package com.exam.service;

import java.util.List;

import com.exam.model.Exam;

/**
 * exam service
 * @author zfh14
 *
 */
public interface IExamService {
	public int addExam(Exam exam);
	public Exam getExamById(int id);
	public List<Exam> getExamList();
	public Exam getMatchExam(Exam exam);
}
