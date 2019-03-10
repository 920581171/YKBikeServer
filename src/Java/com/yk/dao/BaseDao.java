package com.yk.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BaseDao<T> {

    String COLUMN_ID = "_id";

    T selectOne(@Param("column") String column,@Param("property") String property);
    List<T> selectList(@Param("column")String column,@Param("property") String property);
    List<T> selectDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    List<T> selectLike(@Param("column")String column,@Param("property") String property);
    List<T> selectTable();
    List<T> queryPage(@Param("column")String column,@Param("property") String property,@Param("pageIndex")int pageIndex,@Param("pageSize") int pageSize);
    List<T> queryPageTable(@Param("pageIndex")int pageIndex,@Param("pageSize") int pageSize);
    void insert(T t);
    int update(T t);
    int delete(T t);
}
