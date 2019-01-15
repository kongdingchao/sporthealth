package com.sporthealth.dao;

import com.sporthealth.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoDao {
    public List<UserInfo> getStudents();
    public UserInfo getStudent(long userId);
    public void insertUserInfo(UserInfo userInfo);
    public void deleteUserInfo(long userId);
    public void updateUserInfo(Map<String, Object> params);
}
