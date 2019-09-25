package com.exam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.exam.model.ExamScore;
import com.exam.model.ExamScoreExample;

public interface IExamScoreDao {
    int countByExample(ExamScoreExample example);

    int deleteByExample(ExamScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamScore record);

    int insertSelective(ExamScore record);

    List<ExamScore> selectByExample(ExamScoreExample example);

    ExamScore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamScore record, @Param("example") ExamScoreExample example);

    int updateByExample(@Param("record") ExamScore record, @Param("example") ExamScoreExample example);

    int updateByPrimaryKeySelective(ExamScore record);

    int updateByPrimaryKey(ExamScore record);
}