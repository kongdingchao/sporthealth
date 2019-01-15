package com.sporthealth.service.impl;

import com.sporthealth.dao.UserInfoDao;
import com.sporthealth.dao.cache.RedisDao;
import com.sporthealth.entity.UserInfo;
import com.sporthealth.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户信息服务
 * @author: kongdingchao
 * @create: 2019/1/15-19:03
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {
    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserInfoDao userInfoDao;

//    @Autowired
//    RedisDao redisDao;

    @Override
    public List<UserInfo> getUserInfos() {
        List<UserInfo> lt = userInfoDao.getStudents();
        logger.debug("getUserInfos:{}", lt);
        return lt;
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        logger.debug("insertUserInfo:{}", userInfo);
        userInfoDao.insertUserInfo(userInfo);
    }

    @Override
    public void deleteUserInfo(long userId) {
        logger.debug("deleteUserInfo:userId={}", userId);
        userInfoDao.deleteUserInfo(userId);
    }

    @Override
    public void updateUserInfo(long userId, UserInfo userInfo) {
        logger.debug("updateUserInfo:userId={},userInfo={}", userId, userInfo);
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("userId", userInfo.getUserId());
        objectMap.put("name", userInfo.getName());
        objectMap.put("sex", userInfo.getSex());
        objectMap.put("age", userInfo.getAge());
        objectMap.put("regTime", userInfo.getRegTime());
        userInfoDao.updateUserInfo(objectMap);
    }
}
