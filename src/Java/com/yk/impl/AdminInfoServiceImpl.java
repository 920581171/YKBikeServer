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

import static com.yk.Utils.RandomUtils.randomId;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    @Autowired
    AdminInfoDao adminInfoDao;

    @Override
    public AdminInfo searchAdminId(String adminId) throws Exception {
        return adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_ID, adminId);
    }

    @Override
    public AdminInfo searchAdminAccount(String adminAccount) throws Exception {
        return adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_ACCOUNT, adminAccount);
    }

    @Override
    public AdminInfo searchAdminName(String adminName) throws Exception {
        return adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_NAME, adminName);
    }

    @Override
    public AdminInfo searchAdminPhone(String adminPhone) throws Exception {
        return adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_PHONE, adminPhone);
    }

    @Override
    public List<AdminInfo> searchAllAdminInfo() throws Exception {
        return adminInfoDao.selectTable();
    }

    @Override
    public int addAdminInfo(String adminAccount, String adminName, String adminPassword, String adminPhone) throws Exception {
        String adminId = randomId("admin");
        while (adminInfoDao.selectOne(AdminInfoDao.COLUMN_ADMIN_ID, adminId) != null) {
            adminId = randomId("admin");
        }

        AdminInfo adminInfo = new AdminInfo()
                .setAdminAccount(adminAccount)
                .setAdminId(adminId)
                .setAdminName(adminName)
                .setAdminPassword(adminPassword)
                .setAdminPhone(adminPhone)
                .setAdminType("1");

        adminInfoDao.insert(adminInfo);

        return adminInfo.getId();
    }

    @Override
    public int updateAdminInfo(AdminInfo adminInfo) throws Exception {
        return adminInfoDao.update(adminInfo);
    }

    @Override
    public int deleteAdminInfo(AdminInfo adminInfo) throws Exception {
        return adminInfoDao.delete(adminInfo);
    }
}
