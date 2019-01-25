package com.yk.impl;

import com.yk.dao.AdminInfoDao;
import com.yk.pojo.AdminInfo;
import com.yk.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    @Autowired
    AdminInfoDao adminInfoDao;

    @Override
    public AdminInfo searchAdminId(String adminId) {
        return adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_ID,adminId) ;
    }

    @Override
    public AdminInfo searchAdminAccount(String adminAccount) {
        return adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_ACCOUNT,adminAccount) ;
    }

    @Override
    public AdminInfo searchAdminName(String adminName) {
        return adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_NAME,adminName) ;
    }

    @Override
    public AdminInfo searchAdminPhone(String adminPhone) {
        return adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_PHONE,adminPhone) ;
    }

    @Override
    public List<AdminInfo> searchAllAdminInfo() {
        return adminInfoDao.selectTable();
    }

    @Override
    public int addAdminInfo(String adminAccount,String adminName,String adminPassword,String adminPhone) {
        String adminId = randomAdminId();
        while (adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_ID,adminId) != null) {
            adminId = randomAdminId();
        }

        AdminInfo adminInfo = new AdminInfo()
                .setAdminAccount(adminAccount)
                .setAdminId(adminId)
                .setAdminName(adminName)
                .setAdminPassword(adminPassword)
                .setAdminPhone(adminPhone);

        adminInfoDao.insert(adminInfo);

        return adminInfo.getId();
    }

    @Override
    public int updateAdminInfo(AdminInfo adminInfo) {
        return adminInfoDao.update(adminInfo);
    }

    @Override
    public int deleteAdminInfo(AdminInfo adminInfo) {
        return adminInfoDao.delete(adminInfo);
    }

    /**
     * 创建随机userId
     */
    private String randomAdminId() {
        StringBuilder stringBuilder = new StringBuilder();
        int random = (int) (Math.random() * 100000);
        stringBuilder.append("admin").append(random);

        return stringBuilder.toString();
    }
}
