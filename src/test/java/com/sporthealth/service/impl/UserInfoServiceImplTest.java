package com.sporthealth.service.impl;

import com.sporthealth.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"
        , "classpath:spring/spring-service.xml"})
public class UserInfoServiceImplTest {
    //日志对象
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserInfoService userInfoService;

    @Test
    public void getUserInfos() {
        System.out.println("getUserInfos...begin");
        logger.info(userInfoService.getUserInfos().toString());
        System.out.println("getUserInfos...end");
    }

    @Test
    public void getUserInfo() {
        System.out.println("getUserInfos...begin");
        logger.info(userInfoService.getUserInfo(6).toString());
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