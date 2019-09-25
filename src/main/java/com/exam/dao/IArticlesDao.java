package com.exam.dao;

import java.util.List;

import com.exam.model.Articles;
import org.apache.ibatis.annotations.Param;

public interface IArticlesDao {

	//DDL 手工操作示例
	int alterTableName(@Param("Articles") String originalTableName, @Param("newArticles") String newTableName);
	int truncateTable(@Param("Articles") String tableName);
	void createNewTableAndInsertData(@Param("newArticles") String newTableName, @Param("Articles") String originalTableName);
	void createNewTable();


	//DML 操作示例
	Integer add(Articles model);
	Integer delete(int id);
	Integer update(Articles model);
	List<Articles> findAll();
	List<Articles> findAllPaging(@Param("sPos") Integer sPos, @Param("ePos") Integer ePos);
	Articles findById(int id);

	//DML 操作示例扩展
	int getRecordCount(@Param("Articles") String tableName);
	String getCurDataBaseName();
	String isTargetTableExistInDB(@Param("online_exam") String dataBaseName, @Param("Articles") String tableName);

}
