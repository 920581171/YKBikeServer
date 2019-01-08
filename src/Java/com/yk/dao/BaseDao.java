package com.yk.dao;

import com.yk.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {

    String COLUMN_ID = "_id";

    T selectOne(@Param("column") String column,@Param("property") String property);
    List<T> selectList(@Param("column")String column,@Param("property") String property);
    List<T> selectTable();
    void insert(T t);
    int update(T t);
    int delete(T t);
}
