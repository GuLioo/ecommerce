package org.ecommerce.service;

import org.ecommerce.dto.userLoginExecution;
import org.ecommerce.entity.adminUser;

import java.util.List;

public interface adminService {
    //获取全部用户列表
    List<adminUser> getAdminUserList();
    //获取指定uid的用户列表
    List<adminUser> getAdminUserListByUid(short uid);
    //添加用户
    int insertAdminUser(String username, String password, short uid);
    //删除用户
    int deleteByPrimaryKey(Integer auid);
    //更改用户权限
    int setRole(Integer auid,short uid);
    //获得用户总数
    int countUserAll();
    //登陆验证
    adminUser selectByName(String username);//查询用户名返回对象
    //登陆判断
    userLoginExecution executeLogin(String getUserName,String getPassword);
    //设置用户折扣
    int updateDiscount(Integer auid,double discount); //系统管理员操作
}
