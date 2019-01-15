package com.sporthealth.service;

import com.sporthealth.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
	
	public List<UserInfo> getUserInfos();
	void insertUserInfo(UserInfo userInfo);
	void deleteUserInfo(long userId);
	void updateUserInfo(long userId, UserInfo userInfo);
}
