package org.ecommerce.service;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.dto.seckillExecution;
import org.ecommerce.entity.adminUser;
import org.ecommerce.entity.category;
import org.ecommerce.entity.orders;
import org.ecommerce.entity.product;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface userService {
    //根据id查看用户信息
    adminUser userInfo(Integer auid);
    //显示所有目录
    List<category> selectAllCate();
    //找某一种类的商品：根据目录名查找商品
    List<product> selectProBypname(String pname);
    //查询单个商品
    product selectByPrimaryKey(Integer pid);
    //根据用户查询相应订单
    List<orders> findOrderByUid(Integer userId);
    //基于订单号、订单时间等条件搜索订单
    List< orders > findOrderByOid_State_Time(Integer uid,String oid, short state, Date start_time, Date end_time);
    //减库存
    int reduceProduct(Integer pid);
    //加悲观锁
    product lockProduct(Integer pid);
    //增加订单
    int insert(@Param("oid") String  oid, @Param("userId") Integer userId, @Param("orderPrice") Double orderPrice, @Param("userDiscount") Double userDiscount, @Param("productId") Integer productId, @Param("productPrice") Double productPrice, @Param("productName") String productName,
               @Param("productImage") String productImage, @Param("productDesc") String productDesc, @Param("productCate") String productCate, @Param("orderState")short orderState, @Param("orderTime")Timestamp orderTime);


}
