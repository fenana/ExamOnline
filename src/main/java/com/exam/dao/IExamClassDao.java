package com.exam.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.exam.model.ExamClass;
import com.exam.model.ExamClassExample;

public interface IExamClassDao {
    int countByExample(ExamClassExample example);

    int deleteByExample(ExamClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamClass record);

    int insertSelective(ExamClass record);

    List<ExamClass> selectByExample(ExamClassExample example);

    ExamClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamClass record, @Param("example") ExamClassExample example);

    int updateByExample(@Param("record") ExamClass record, @Param("example") ExamClassExample example);

    int updateByPrimaryKeySelective(ExamClass record);

    int updateByPrimaryKey(ExamClass record);
}