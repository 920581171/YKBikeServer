package com.yk.bike.dao;

import com.yk.bike.pojo.AdminInfo;

import java.util.List;

public interface AdminInfoDao {
    AdminInfo searchAdminId(String adminId) throws Exception;

    AdminInfo searchAdminAccount(String adminAccount) throws Exception;

    AdminInfo searchAdminName(String adminName) throws Exception;

    AdminInfo searchAdminPhone(String adminPhone) throws Exception;

    List<AdminInfo> searchAllAdminInfo() throws Exception;

    int addAdminInfo(String adminAccount, String adminName, String adminPassword, String adminPhone) throws Exception;

    int updateAdminInfo(AdminInfo adminInfo) throws Exception;

    int deleteAdminInfo(AdminInfo adminInfo) throws Exception;
}
