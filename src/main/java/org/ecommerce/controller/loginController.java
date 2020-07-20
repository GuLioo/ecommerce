package org.ecommerce.controller;

import org.ecommerce.dto.ecommerceResult;
import org.ecommerce.dto.userExecution;
import org.ecommerce.dto.userStateEnum;
import org.ecommerce.entity.adminUser;
import org.ecommerce.exception.userLogin_NoUser_Exception;
import org.ecommerce.exception.userLogin_passwordError_Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Component
@RequestMapping("/entrance")
public class loginController {
    @Autowired
    private org.ecommerce.business.adminBusiness adminBusiness;

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    /**
     * 注销用户，返回登陆页
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logOut")
    public String logOut(HttpSession session) throws Exception {
        adminBusiness.logOut(session);
        return "login";
    }

    /**
     * 登陆验证
     * 成功跳转页面并保存用户id至session
     * 失败返回错误信息
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/loginRequest",method = RequestMethod.POST)
    @ResponseBody
    public ecommerceResult loginRequest(String userName,String password, HttpSession session) {
        return adminBusiness.executeLogin(userName,password,session);
    }
    }
