package com.sporthealth.common.modules.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


/**
 * @program: sporthealth
 * @description: 测试shiro验证
 * @author: kongdingchao
 * @create: 2019-01-27 06:55
 **/
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    //@Before
    public void initSimpleAccountRealm() {
        this.simpleAccountRealm.addAccount("kdc", "123456", "admin");
    }

    //@Test
    public void testAuthentication(){
        //1.构建SercurityMannager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2.主体提交
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        System.out.println("testAuthentication before login:" + subject.isAuthenticated());
        UsernamePasswordToken token = new UsernamePasswordToken("kdc", "123456");
        subject.login(token);
        System.out.println("testAuthentication after login:" + subject.isAuthenticated());

        //验证授权
        subject.checkRole("admin");
    }
}
