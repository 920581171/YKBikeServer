package com.yk.dao;

import com.yk.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;

public interface BaseDao<T> {

    String COLUMN_ID = "_id";

    T selectOne(@Param("column") String column,@Param("property") String property);
    List<T> selectList(@Param("column")String column,@Param("property") String property);
    List<T> selectTable();
    List<T> queryPage(@Param("column")String column,@Param("property") String property,@Param("pageIndex")int pageIndex,@Param("pageSize") int pageSize);
    List<T> queryPageTable(@Param("pageIndex")int pageIndex,@Param("pageSize") int pageSize);
    void insert(T t);
    int update(T t);
    int delete(T t);
}
