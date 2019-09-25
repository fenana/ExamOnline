package com.exam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.exam.model.ExamStudent;
import com.exam.model.ExamStudentExample;

public interface IExamStudentDao {
    int countByExample(ExamStudentExample example);

    int deleteByExample(ExamStudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamStudent record);

    int insertSelective(ExamStudent record);

    List<ExamStudent> selectByExample(ExamStudentExample example);

    ExamStudent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamStudent record, @Param("example") ExamStudentExample example);

    int updateByExample(@Param("record") ExamStudent record, @Param("example") ExamStudentExample example);

    int updateByPrimaryKeySelective(ExamStudent record);

    int updateByPrimaryKey(ExamStudent record);
}