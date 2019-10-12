package com.yk.bike.mapper;

import com.yk.bike.pojo.UserInfo;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    String COLUMN_USER_ID = "user_id";
    String COLUMN_USER_NAME="user_name";
    String COLUMN_USER_PHONE="user_phone";
    String COLUMN_USER_PASSWORD="user_password";
    String COLUMN_DEPOSIT="deposit";
    String COLUMN_BALANCE="balance";

    List<UserInfo> selectDeposit();
}
