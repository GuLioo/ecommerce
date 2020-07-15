package org.ecommerce.dao;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.entity.orders;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface ordersDao {
    //增加订单
    int insert(@Param("oid") String  oid, @Param("userId") Integer userId, @Param("orderPrice") Double orderPrice,@Param("userDiscount") Double userDiscount,@Param("productId") Integer productId,@Param("productPrice") Double productPrice, @Param("productName") String productName,
               @Param("productImage") String productImage,@Param("productDesc") String productDesc,@Param("productCate") String productCate,@Param("orderState")short orderState,@Param("orderTime")Timestamp orderTime);
    //查询所有订单
    List<orders> selectAll();
    //根据用户查询相应订单
    List<orders> findOrderByUid(@Param("userId") Integer userId);
    //根据订单编号、状态、时间查询订单
    List< orders > findOrderByOid_State_Time(@Param("oid") String oid, @Param("state") Short state, @Param("fromTime")Date fromTime, @Param("toTime")Date toTime);
    //查询订单数量
    int countProductAll();

}
