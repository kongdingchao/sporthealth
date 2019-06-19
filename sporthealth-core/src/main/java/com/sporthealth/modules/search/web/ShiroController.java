package com.sporthealth.modules.search.web;

import com.sporthealth.modules.search.entity.UserPassword;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-01-28 21:30
 **/
@Controller
@RequestMapping("/")
public class ShiroController {

    @RequestMapping(value="/subLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String subLogin(UserPassword userPassword) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userPassword.getUser(), userPassword.getPassword());

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        return "redirect:/index.jsp";
    }
}
