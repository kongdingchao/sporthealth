package com.sporthealth.dao;

import com.sporthealth.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserInfoDaoTest {
    @Autowired
    UserInfoDao userInfoDao;

    @Test
    public void inserStudent() {
        System.out.println("inserStudent...begin");
        UserInfo userInfo = new UserInfo();
        userInfo.setName("kdc");
        userInfo.setAge(25);
        userInfo.setSex(0);
        Date date = new Date();
        userInfo.setRegTime(date);

        userInfoDao.insertUserInfo(userInfo);
        System.out.println("inserStudent...end," + userInfo.toString());
    }

    @Test
    public void getStudents() {
        System.out.println(userInfoDao.getStudents());
    }
}