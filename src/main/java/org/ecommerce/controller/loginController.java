package org.ecommerce.controller;

import org.ecommerce.dto.ecommerceResult;
import org.ecommerce.dto.userLoginExecution;
import org.ecommerce.dto.userStateEnum;
import org.ecommerce.exception.userLogin_NoUser_Exception;
import org.ecommerce.exception.userLogin_passwordError_Exception;
import org.ecommerce.service.adminService;
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
    private adminService adminService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    //退出登录
    @RequestMapping(value = "/logOut")
    public String logOut(HttpSession session) throws Exception {
        session.invalidate();
        System.out.println("清除缓存成功");
        return "login";
    }


    @RequestMapping(value = "/loginRequest",method = RequestMethod.POST)
    @ResponseBody
    public ecommerceResult loginRequest(String userName,String password, HttpSession session) {
        System.out.println("进入controller");
        System.out.println("userName="+userName+"  password="+password);
        try {
            userLoginExecution execution = adminService.executeLogin(userName, password);
            System.out.println("验证成功");
            return new ecommerceResult<userLoginExecution>(true, execution);
        } catch (userLogin_passwordError_Exception e1) {
            System.out.println("密码错误");
            userLoginExecution execution = new userLoginExecution(userName, userStateEnum.PASSWORD_ERROR);
            return new ecommerceResult<userLoginExecution>(true, execution);
        } catch (userLogin_NoUser_Exception e1) {
            System.out.println("用户名不存在");
            userLoginExecution execution = new userLoginExecution(userName, userStateEnum.NO_USER);
            return new ecommerceResult<userLoginExecution>(true, execution);
        } catch (Exception e) {
            System.out.println("内部错误");
            userLoginExecution execution = new userLoginExecution(userName, userStateEnum.INNER_ERROR);
            return new ecommerceResult<userLoginExecution>(true, execution);
        }
    }


    }
