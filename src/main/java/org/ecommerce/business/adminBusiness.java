package org.ecommerce.business;

import org.ecommerce.dto.pageResult;
import org.ecommerce.dto.ecommerceResult;

import javax.servlet.http.HttpSession;

//用户管理员接口
public interface adminBusiness {
    //返回对应权限的用户列表
    pageResult adminUserGet(short uid, Integer pn);
    //添加用户
    ecommerceResult insertAdminUser(String username, String password, short uid);
    //删除用户
    int deleteByPrimaryKey(Integer auid);
    //更改用户权限
    ecommerceResult setRole(Integer auid, short uid);
    //登陆验证
    ecommerceResult executeLogin(String getUserName, String getPassword, HttpSession session);
    //用户注销
    void logOut(HttpSession session);

}
