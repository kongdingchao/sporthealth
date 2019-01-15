package com.sporthealth.service.impl;

import com.sporthealth.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"
        , "classpath:spring/spring-service.xml"})
public class UserInfoServiceImplTest {
    @Autowired
    UserInfoService userInfoService;

    @Test
    public void getUserInfos() {
        System.out.println("getUserInfos...begin");
        userInfoService.getUserInfos();
        System.out.println("getUserInfos...end");
    }

    //@Test
    public void insertUserInfo() {
    }

    //@Test
    public void deleteUserInfo() {
    }

    //@Test
    public void updateUserInfo() {
    }
}