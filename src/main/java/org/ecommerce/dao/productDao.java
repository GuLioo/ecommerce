package org.ecommerce.dao;

import org.apache.ibatis.annotations.Param;
import org.ecommerce.entity.product;

import java.util.List;

public interface productDao {
    //增加商品
    int insert(@Param("pname") String pname, @Param("market_Price") double market_Price, @Param("image")String image, @Param("pdesc") String pdesc,@Param("pnum") Integer pnum);
    //删除商品
    int deleteByPrimaryKey(Integer pid);
    //修改商品，成功返回1
    int updateByPrimaryKey(@Param("pid") Integer pid, @Param("pname") String pname,@Param("market_Price") double market_Price,@Param("image") String image ,@Param("pdesc") String pdesc,@Param("pnum") Integer pnum);
    //查询单个商品
    product selectByPrimaryKey(Integer pid);
    //查询所有商品
    List< product > selectAll(@Param("offset") int offset,@Param("limit") int limit);
    //查询所有商品数量
    int countProducyAll();
    //根据pname查询商品数量
    int countProducyByname( @Param("pname") String pname);

}
