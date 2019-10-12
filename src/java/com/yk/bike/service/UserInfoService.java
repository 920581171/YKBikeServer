package com.yk.bike.service;

public interface UserInfoService {
    String appLogin(String userName, String userPassword) throws Exception;

    String findUserByUserId(String userId) throws Exception;

    String findDeposit() throws Exception;

    String findAllUserInfo() throws Exception;

    String findUserByUserName(String userName) throws Exception;

    String findUserByUserPhone(String userPhone) throws Exception;

    String registerUserByPhone(String userPhone) throws Exception;

    String registerUserByName(String userName, String userPassword) throws Exception;

    String updateUserInfo(String userId, String userName, String userPhone, String userPassword, int score, float deposit, float balance) throws Exception;

    String resetUserPassword(String userId) throws Exception;

    String deleteUserInfo(String userId) throws Exception;
}
