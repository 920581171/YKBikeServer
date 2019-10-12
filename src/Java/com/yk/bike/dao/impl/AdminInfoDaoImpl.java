package com.yk.bike.dao.impl;

import com.yk.bike.mapper.AdminInfoMapper;
import com.yk.bike.pojo.AdminInfo;
import com.yk.bike.dao.AdminInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.bike.utils.RandomUtils.randomId;

@Service
public class AdminInfoDaoImpl implements AdminInfoDao {
    @Autowired
    AdminInfoMapper adminInfoMapper;

    @Override
    public AdminInfo searchAdminId(String adminId) throws Exception {
        return adminInfoMapper.selectOne(AdminInfoMapper.COLUMN_ADMIN_ID, adminId);
    }

    @Override
    public AdminInfo searchAdminAccount(String adminAccount) throws Exception {
        return adminInfoMapper.selectOne(AdminInfoMapper.COLUMN_ADMIN_ACCOUNT, adminAccount);
    }

    @Override
    public AdminInfo searchAdminName(String adminName) throws Exception {
        return adminInfoMapper.selectOne(AdminInfoMapper.COLUMN_ADMIN_NAME, adminName);
    }

    @Override
    public AdminInfo searchAdminPhone(String adminPhone) throws Exception {
        return adminInfoMapper.selectOne(AdminInfoMapper.COLUMN_ADMIN_PHONE, adminPhone);
    }

    @Override
    public List<AdminInfo> searchAllAdminInfo() throws Exception {
        return adminInfoMapper.selectTable();
    }

    @Override
    public int addAdminInfo(String adminAccount, String adminName, String adminPassword, String adminPhone) throws Exception {
        String adminId = randomId("ADMIN");
        while (adminInfoMapper.selectOne(AdminInfoMapper.COLUMN_ADMIN_ID, adminId) != null) {
            adminId = randomId("ADMIN");
        }

        AdminInfo adminInfo = new AdminInfo()
                .setAdminAccount(adminAccount)
                .setAdminId(adminId)
                .setAdminName(adminName)
                .setAdminPassword(adminPassword)
                .setAdminPhone(adminPhone)
                .setAdminType("1");

        adminInfoMapper.insert(adminInfo);

        return adminInfo.getId();
    }

    @Override
    public int updateAdminInfo(AdminInfo adminInfo) throws Exception {
        return adminInfoMapper.update(adminInfo);
    }

    @Override
    public int deleteAdminInfo(AdminInfo adminInfo) throws Exception {
        return adminInfoMapper.delete(adminInfo);
    }
}
