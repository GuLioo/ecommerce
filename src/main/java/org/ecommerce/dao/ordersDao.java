package org.ecommerce.dao;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.entity.orders;

import java.util.Date;
import java.util.List;

public interface ordersDao {
    //增加订单
    int insert(@Param("userId") Integer userId, @Param("money") Double money, @Param("state") short state);
    //查询所有订单
    List<orders> selectAll();
    //根据用户查询相应订单
    List<orders> findOrderByUid(@Param("userId") Integer userId);
    //根据订单编号、状态、时间查询订单
    List< orders > findOrderByOid_State_Time(@Param("oid") Integer oid, @Param("state") Short state, @Param("fromTime")Date fromTime, @Param("toTime")Date toTime);
    //查询订单数量
    int countProducyAll();

}
