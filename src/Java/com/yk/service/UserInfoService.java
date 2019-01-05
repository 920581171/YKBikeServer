package com.yk.service;

import com.yk.pojo.UserInfo;

public interface UserInfoService {
    UserInfo searchUserId(String userId);
    int addUserName(String userName,String userPassword);
    int addUserPhone(String userPhone);
}
