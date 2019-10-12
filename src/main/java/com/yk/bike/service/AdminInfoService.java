package com.yk.bike.service;

public interface AdminInfoService {

    String appAdminLogin(String adminAccountOrPhone, String adminPassword) throws Exception;

    String findAdminByAdminId(String adminId) throws Exception;

    String findAdminByAdminAccount(String adminAccount) throws Exception;

    String findAllAdminInfo() throws Exception;

    String findAdminByAdminName(String adminName) throws Exception;

    String findUserByAdminPhone(String adminPhone) throws Exception;

    String registerAdminInfo(String adminAccount, String adminName, String adminPassword, String adminPhone) throws Exception;

    String updateAdminInfo(String adminId, String adminAccount, String adminName, String adminPhone, String adminPassword) throws Exception;

    String deleteUserInfo(String adminId) throws Exception;
}
