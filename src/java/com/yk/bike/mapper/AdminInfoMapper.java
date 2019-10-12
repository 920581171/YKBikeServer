package com.yk.bike.mapper;

import com.yk.bike.pojo.AdminInfo;

public interface AdminInfoMapper extends BaseMapper<AdminInfo> {
    String COLUMN_ADMIN_ID = "admin_id";
    String COLUMN_ADMIN_ACCOUNT = "admin_account";
    String COLUMN_ADMIN_NAME = "admin_name";
    String COLUMN_ADMIN_PASSWORD = "admin_password";
    String COLUMN_ADMIN_PHONE = "admin_phone";
    String COLUMN_ADMIN_TYPE = "admin_type";
}
