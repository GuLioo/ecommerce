package org.ecommerce.service;

import org.ecommerce.entity.adminUser;

import java.util.List;

//用户管理员接口
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
    //查询用户名返回用户对象
    adminUser selectByName(String username);
    //判断是否已存在uid=1产品销售商,若小于等于1则s.t.
    int judgeSale();
    //根据auid查询用户
    adminUser selectByAuid(Integer auid);
}
