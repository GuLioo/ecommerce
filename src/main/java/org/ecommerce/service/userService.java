package org.ecommerce.service;

import org.ecommerce.entity.category;
import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;

import java.util.Date;
import java.util.List;

public interface userService {
    //用户信息更新
    int updateByPrimaryKey(Integer auid,String name,String email,String phone);//数据库if条件操作
    //显示所有目录
    List<category> selectAllCate();
    //找某一种类的商品：根据目录名查找商品
    List<product> selectProBypname(String pname);
    //查询单个商品
    product selectByPrimaryKey(Integer pid);
    //增加订单
    int insert(Integer userId,Double money,short state);
    //根据用户查询相应订单
    List<orders> findOrderByUid(Integer userId);
    //基于订单号、订单状态等条件搜索订单
    List< orders > findOrderByOid_State_Time(Integer oid, short state, Date start_time, Date end_time);

}
