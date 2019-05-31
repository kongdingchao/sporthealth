package com.kdc.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-01-28 20:04
 **/
public class IniRealmTest {
    @Test
    public void testAuthentication(){
        IniRealm iniRealm = new IniRealm("classpath:user.ini");
        //1.构建SercurityMannager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        iniRealm.setCredentialsMatcher(matcher);

        //2.主体提交
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        System.out.println("testAuthentication before login:" + subject.isAuthenticated());
        UsernamePasswordToken token = new UsernamePasswordToken("kdc", "123456");
        subject.login(token);
        System.out.println("testAuthentication after login:" + subject.isAuthenticated());

        //验证授权
        subject.checkRole("admin");

        subject.checkPermission("sql:delete");
    }

    public static void main(String []args) {
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println("md5Hash:" + md5Hash.toString());
    }
}
