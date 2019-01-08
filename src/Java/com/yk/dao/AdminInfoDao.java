package com.yk.dao;

import com.yk.pojo.AdminInfo;

public interface AdminInfoDao extends BaseDao<AdminInfo>{
    String COLUMN_ADMIN_ID = "admin_id";
    String COLUMN_ADMIN_NAME = "admin_name";
    String COLUMN_ADMIN_PASSWORD = "admin_password";
    String COLUMN_ADMIN_PHONE = "admin_phone";
}
