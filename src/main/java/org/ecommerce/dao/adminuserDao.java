package org.ecommerce.dao;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.entity.adminUser;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface adminuserDao {
    //删除用户
    int deleteByPrimaryKey(@Param("auid") Integer auid);
    //添加用户，成功输出1
    int insertAdminUser(@Param("username") String username, @Param("password") String password, @Param("uid") short uid);
    //根据姓名查询用户,没有返回null
    adminUser selectByName(@Param("username") String username);
    //根据auid查询用户
    adminUser selectByAuid(Integer auid);
    //根据偏移量查询用户列表
    List<adminUser> queryAll(@Param("offset") int offset, @Param("limit")int limit);
    //获取指定uid的用户列表
    List<adminUser> getAdminUserListByUid(short uid);
    //更改用户权限
    int setRole(@Param("auid") Integer auid,@Param("uid") short uid);
    //获取全部用户数目
    int countUserAll();
    //判断是否已存在uid=1产品销售商,若小于等于1则s.t.
    int judgeSale();
}
