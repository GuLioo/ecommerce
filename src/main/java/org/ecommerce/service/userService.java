package org.ecommerce.service;

import org.ecommerce.dto.seckillExecution;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.category;
import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface userService {
    //用户信息更新
    int updateByPrimaryKey(Integer auid,String name,String email,String phone);//数据库if条件操作
    //根据id查看用户信息
    adminUser userInfo(Integer auid);
    //显示所有目录
    List<category> selectAllCate();
    //找某一种类的商品：根据目录名查找商品
    List<product> selectProBypname(String pname);
    //查询单个商品
    product selectByPrimaryKey(Integer pid);
    //生成订单信息来显示但不进行数据库插入
    orders createOrder(product product, adminUser user);
/*    //增加订单
    int insert(orders orders);
    //减库存
    int reduceProduct(Integer pid);*/
    //执行减库存、增加订单的事务
    seckillExecution executeSeckill(orders orders, Integer pid);
    //根据用户查询相应订单
    List<orders> findOrderByUid(Integer userId);
    //基于订单号、订单状态等条件搜索订单
    List< orders > findOrderByOid_State_Time(Integer uid,String oid, short state, Date start_time, Date end_time);

}
