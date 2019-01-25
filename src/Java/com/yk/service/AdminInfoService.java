package com.yk.service;

import com.yk.pojo.AdminInfo;
import com.yk.pojo.BikeInfo;

import java.util.List;

public interface AdminInfoService {
    AdminInfo searchAdminId(String adminId);
    AdminInfo searchAdminAccount(String adminAccount);
    AdminInfo searchAdminName(String adminName);
    AdminInfo searchAdminPhone(String adminPhone);
    List<AdminInfo> searchAllAdminInfo();
    int addAdminInfo(String adminAccount,String adminName,String adminPassword,String adminPhone);
    int updateAdminInfo(AdminInfo adminInfo);
    int deleteAdminInfo(AdminInfo adminInfo);
}
