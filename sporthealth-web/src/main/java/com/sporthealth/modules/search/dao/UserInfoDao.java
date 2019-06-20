package com.sporthealth.modules.search.dao;

import com.sporthealth.modules.search.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoDao {
    List<UserInfo> getUserInfos();
    List<UserInfo> searchUserInfo(String name);
    UserInfo getUserInfo(long userId);
    void insertUserInfo(UserInfo userInfo);
    void deleteUserInfo(long userId);
    void updateUserInfo(Map<String, Object> params);
}
