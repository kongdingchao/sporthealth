package com.sporthealth.service;

import com.sporthealth.entity.UserInfo;

import java.util.List;
/**
 * @description: 用户信息服务
 * @author: kongdingchao
 * @create: 2019/1/15-19:03
 **/
public interface UserInfoService {

	List<UserInfo> searchUserInfo(String name);
	List<UserInfo> getUserInfos();
	UserInfo getUserInfo(long userId);
	void insertUserInfo(UserInfo userInfo);
	void deleteUserInfo(long userId);
	void updateUserInfo(long userId, UserInfo userInfo);
}
