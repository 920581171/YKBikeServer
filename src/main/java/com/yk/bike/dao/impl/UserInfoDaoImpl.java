package com.yk.bike.dao.impl;

import com.yk.bike.mapper.UserInfoMapper;
import com.yk.bike.pojo.UserInfo;
import com.yk.bike.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yk.bike.utils.RandomUtils.randomUserId;

@Service
public class UserInfoDaoImpl implements UserInfoDao {
    @Autowired
    UserInfoMapper userInfoDao;

    /**
     * 根据userId查找用户
     */
    @Override
    public UserInfo searchUserId(String userId) throws Exception {
        return userInfoDao.selectOne(UserInfoMapper.COLUMN_USER_ID, userId);
    }

    /**
     * 根据userName查找用户
     */
    @Override
    public UserInfo searchUserName(String userName) throws Exception {
        return userInfoDao.selectOne(UserInfoMapper.COLUMN_USER_NAME, userName);
    }

    /**
     * 根据userPhone查找用户
     */
    @Override
    public UserInfo searchUserPhone(String userPhone) throws Exception {
        return userInfoDao.selectOne(UserInfoMapper.COLUMN_USER_PHONE, userPhone);
    }

    @Override
    public List<UserInfo> searchAllUserInfo() throws Exception {
        return userInfoDao.selectTable();
    }

    @Override
    public List<UserInfo> searchDeposit() throws Exception {
        return userInfoDao.selectDeposit();
    }

    /**
     * 根据用户名添加用户
     *
     * @return 自增的id
     */
    @Override
    public int addUserByName(String userName, String userPassword) throws Exception {
        String userId = randomUserId();
        while (userInfoDao.selectOne(UserInfoMapper.COLUMN_USER_ID, userId) != null) {
            userId = randomUserId();
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId)
                .setUserName(userName)
                .setUserPassword(userPassword)
                .setScore(0)
                .setBalance(0f)
                .setDeposit(0f);
        userInfoDao.insert(userInfo);

        return userInfo.getId();
    }

    /**
     * 根据手机号码添加用户
     *
     * @return 自增的id
     */
    @Override
    public int addUserByPhone(String userPhone) throws Exception {
        String userId = randomUserId();
        while (userInfoDao.selectOne(UserInfoMapper.COLUMN_USER_ID, userId) != null) {
            userId = randomUserId();
        }

        UserInfo userInfo = new UserInfo()
                .setUserId(userId)
                .setUserPhone(userPhone);
        userInfoDao.insert(userInfo);

        return userInfo.getId();
    }

    /**
     * 根据userId 更新用户信息
     *
     * @return 更改的条数
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) throws Exception {
        return userInfoDao.update(userInfo);
    }

    /**
     * 根据userId删除数据
     *
     * @return 更改的条数
     */
    @Override
    public int deleteUserInfo(UserInfo userInfo) throws Exception {
        return userInfoDao.delete(userInfo);
    }
}
