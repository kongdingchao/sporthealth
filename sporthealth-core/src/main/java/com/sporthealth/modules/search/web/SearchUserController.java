package com.sporthealth.modules.search.web;

import com.sporthealth.modules.search.entity.UserInfo;
import com.sporthealth.modules.search.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: kongdingchao
 * @Description: 查询的页面处理
 * @Date: 2018/9/20 22:07
 */
@Controller
@RequestMapping("/")
public class SearchUserController {
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping(value="/sayHello",method = RequestMethod.GET)
    public @ResponseBody
    String sayHello(){
        return "{\"returncode\":0,\"message\":\"hello world\"}";
    }

    //用于搜索框下拉列表的请求
	@ResponseBody
	@RequestMapping(value = "/searchUser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<UserInfo> searchUser(String keyword, Model model){
		System.out.println("-----获取json----1288----");
		List<UserInfo> users = userInfoService.searchUserInfo(keyword);
		System.out.println(users.toString());
		model.addAttribute("message", keyword);
		return users;
	}

	//用户详情
	@RequestMapping(value = "/userInfos", method = RequestMethod.GET)
	public String list(String keyword, Model model)
	{
		List<UserInfo> userInfos = userInfoService.searchUserInfo(keyword);
		model.addAttribute("userInfos", userInfos);
		return "userInfoList";
	}

}
