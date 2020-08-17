package com.yk.bike.service.impl;

import com.yk.bike.websocket.WebSocketHandler;
import com.yk.bike.dao.AdminInfoDao;
import com.yk.bike.pojo.AdminInfo;
import com.yk.bike.service.AdminInfoService;
import com.yk.bike.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoDao adminInfoDao;

    @Override
    public String appAdminLogin(String adminAccountOrPhone, String adminPassword) throws Exception {
        AdminInfo adminInfo = (adminInfoDao.searchAdminAccount(adminAccountOrPhone) == null) ?
                adminInfoDao.searchAdminPhone(adminAccountOrPhone) :
                adminInfoDao.searchAdminAccount(adminAccountOrPhone);

        if (adminInfo == null) {
            return GsonUtils.responseErrorMsgJson("管理员不存在");
        } else if (!adminInfo.getAdminPassword().equals(adminPassword)) {
            return GsonUtils.responseErrorMsgJson("密码错误");
        } else {
            return GsonUtils.responseSuccessObjJson(adminInfo);
        }
    }

    @Override
    public String findAdminByAdminId(String adminId) throws Exception {
        AdminInfo adminInfo = adminInfoDao.searchAdminId(adminId);
        return GsonUtils.responseObjectJson(adminInfo != null, adminInfo);
    }

    @Override
    public String findAdminByAdminAccount(String adminAccount) throws Exception {
        AdminInfo adminInfo = adminInfoDao.searchAdminAccount(adminAccount);
        return GsonUtils.responseObjectJson(adminInfo != null, adminInfo);
    }

    @Override
    public String findAllAdminInfo() throws Exception {
        List<AdminInfo> adminInfos = adminInfoDao.searchAllAdminInfo();
        Map<String, WebSocketHandler> clients = WebSocketHandler.clients;
        boolean b = adminInfos != null;
        if (!b) {
            return GsonUtils.responseErrorJson();
        }
        for (AdminInfo info : adminInfos) {
            info.setAdminOnline(clients.get(info.getAdminId()) == null ? "0" : "1");
        }
        return GsonUtils.responseSuccessObjJson(adminInfos);
    }

    @Override
    public String findAdminByAdminName(String adminName) throws Exception {
        AdminInfo adminInfo = adminInfoDao.searchAdminName(adminName);
        return GsonUtils.responseObjectJson(adminInfo != null, adminInfo);
    }

    @Override
    public String findUserByAdminPhone(String adminPhone) throws Exception {
        AdminInfo adminInfo = adminInfoDao.searchAdminPhone(adminPhone);
        return GsonUtils.responseObjectJson(adminInfo != null, adminInfo);
    }

    @Override
    public String registerAdminInfo(String adminAccount, String adminName, String adminPassword, String adminPhone) throws Exception {
        if (adminInfoDao.searchAdminAccount(adminAccount) != null) {
            return GsonUtils.responseErrorMsgJson("账号已存在");
        }
        if (adminInfoDao.searchAdminPhone(adminPhone) != null) {
            return GsonUtils.responseErrorMsgJson("手机号已存在");
        }
        return GsonUtils.responseSimpleJson(adminInfoDao.addAdminInfo(adminAccount, adminName, adminPassword, adminPhone) > 0);
    }

    @Override
    public String updateAdminInfo(String adminId, String adminAccount, String adminName, String adminPhone, String adminPassword) throws Exception {
        AdminInfo adminInfo = new AdminInfo()
                .setAdminAccount(adminAccount)
                .setAdminId(adminId)
                .setAdminName(adminName)
                .setAdminPhone(adminPhone)
                .setAdminPassword(adminPassword);
        return GsonUtils.responseSimpleJson(adminInfoDao.updateAdminInfo(adminInfo) > 0);
    }

    @Override
    public String deleteUserInfo(String adminId) throws Exception {
        AdminInfo adminInfo = new AdminInfo().setAdminId(adminId);
        return GsonUtils.responseSimpleJson(adminInfoDao.deleteAdminInfo(adminInfo) > 0);
    }
}
