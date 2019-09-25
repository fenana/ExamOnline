package com.exam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.exam.model.ExamHistory;
import com.exam.model.ExamHistoryExample;

public interface IExamHistoryDao {
    int countByExample(ExamHistoryExample example);

    int deleteByExample(ExamHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamHistory record);

    int insertSelective(ExamHistory record);

    List<ExamHistory> selectByExample(ExamHistoryExample example);

    ExamHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamHistory record, @Param("example") ExamHistoryExample example);

    int updateByExample(@Param("record") ExamHistory record, @Param("example") ExamHistoryExample example);

    int updateByPrimaryKeySelective(ExamHistory record);

    int updateByPrimaryKey(ExamHistory record);
}