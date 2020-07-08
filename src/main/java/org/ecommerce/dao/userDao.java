package org.ecommerce.dao;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.entity.user;

import java.util.List;

public interface userDao {
    //系统管理员操作，增加用户
    int insert(@Param("auid") Integer auid, @Param("username")String username,@Param("password") String password);
    //系统管理员操作设置用户折扣
    int updateDiscount(@Param("auid") Integer auid,@Param("discount") double discount);
    //系统管理员操作，删除用户
    int deleteByPrimaryKey(@Param("auid") Integer auid);
    //普通用户操作，更新用户信息,没有填null即可
    int updateByPrimaryKey(@Param("auid")Integer auid,@Param("name") String name,@Param("email") String email,@Param("phone") String phone);
    //查询单个普通用户
    user selectByPrimaryKey(Integer auid);
    //查询所有普通用户
    List<user> selectAll(@Param("offset")int offset, @Param("limit")int limit);
}
