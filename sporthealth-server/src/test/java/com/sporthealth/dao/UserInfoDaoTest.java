package com.sporthealth.dao;

import com.sporthealth.entity.UserInfo;
import com.sporthealth.enums.SexEnum;
import com.sporthealth.enums.handler.EnumKeyTypeHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserInfoDaoTest {
    @Autowired
    UserInfoDao userInfoDao;

    //@Test
    public void inserUserInfo() {
        System.out.println("inserUserInfo...begin");
        UserInfo userInfo = new UserInfo();
        userInfo.setName("houyao");
        userInfo.setAge(23);
        userInfo.setSex(SexEnum.WOMAN);
        Date date = new Date();
        userInfo.setRegTime(date);

        userInfoDao.insertUserInfo(userInfo);
        System.out.println("inserUserInfo...end," + userInfo.toString());
    }

    @Test
    public void searchUserInfos() {
        System.out.println(userInfoDao.searchUserInfo("kdc"));
    }


    //@Test
    public void getUserInfos() {
        System.out.println(userInfoDao.getUserInfos());
    }
}